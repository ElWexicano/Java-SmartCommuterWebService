package ie.smartcommuter.service;

import ie.smartcommuter.models.BeanUtilities;
import ie.smartcommuter.models.StationData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class RealTime {
	
	private static List<StationData> stationDataList;
	
	/**
	 * This method is used to get station data for a
	 * transport station. It requires a station type
	 * and the API code for the station.
	 * 
	 * @param stationType The type of Station. Eg. BusEireann, DublinBus, IrishRail, Luas.
	 * @param stationApiCode The api code used to identify the station.
	 * @return stationDataList A list of StationData Objects.
	 * @throws Exception
	 */
	public static List<StationData> getStationData(String stationType,String stationApiCode) {
		
		stationDataList = new ArrayList<StationData>();
		
		if(stationType.equals("BusEireann")){
			stationDataList = getBusEireannStationData(stationApiCode);
		} else if(stationType.equals("DublinBus")){
			stationDataList = getDublinBusStationData(stationApiCode);
		} else if(stationType.equals("IrishRail")){
			stationDataList = getIrishRailStationData(stationApiCode);
		} else if(stationType.equals("Luas")){
			stationDataList = getLuasStationData(stationApiCode);
		} else if(stationType.equals("JJKavanagh")){
			stationDataList = getJJKavanaghStationData(stationApiCode);
		}
		
		return stationDataList;
	}
	

	
	/**
	 * This method is used to return the real time data for a
	 * Bus Éireann Station. A Bus Éireann Station API Code is
	 * passed in and it returns a list of StationData objects.
	 * 
	 * @param stationApiCode The api code used to identify the station.
	 * @return stationDataList A list of StationData Objects.
	 * @throws Exception
	 */
	public static List<StationData> getBusEireannStationData(String apiCode) {
		
		Elements htmlElements = BeanUtilities.getHtmlElementsForUrl(buildBusEireannUrl(apiCode, "y"),"#left tbody tr:gt(0) > td > span.dfifahrten");
		
		stationDataList = new ArrayList<StationData>();
		
		List<StationData> arrivals = new ArrayList<StationData>();
		List<StationData> departures = new ArrayList<StationData>();
		StationData tempStationData = null;
		
		int i = 0;
		for(Element e : htmlElements){
			
			String tempString = new String(e.text());
			
			if((tempString.length()==1&&tempString.matches("-?\\d+(.\\d+)?"))||tempString.length()>1){
				
				switch(i) {
				case(0) :
					tempStationData = new StationData();
					tempStationData.setRoute(tempString);
					tempStationData.setIsArrivalOrDeparture("Departure");
					i++;
					break;
				case(1) :
					tempStationData.setDestination(tempString);
					i++;
					break;
				case(2) :
					tempStationData.setExpectedTime(BeanUtilities.formatGivenDateString(tempString));
					departures.add(tempStationData);
					i = 0;
					break;
				}
			}
		}
		
		// Get Arrivals //
		htmlElements = BeanUtilities.getHtmlElementsForUrl(buildBusEireannUrl(apiCode, "n"),"#left tbody tr:gt(0) > td > span.dfifahrten");
		
		i = 0;
		for(Element e : htmlElements){
			
			String tempString = new String(e.text());
			
			if((tempString.length()==1&&tempString.matches("-?\\d+(.\\d+)?"))||tempString.length()>1){
				
				switch(i) {
				case(0) :
					tempStationData = new StationData();
					tempStationData.setRoute(tempString);
					
					tempStationData.setIsArrivalOrDeparture("Arrival");
					i++;
					break;
				case(1) :
					tempStationData.setDestination(tempString);
					i++;
					break;
				case(2) :
					tempStationData.setExpectedTime(BeanUtilities.formatGivenDateString(tempString));
					arrivals.add(tempStationData);
					i = 0;
					break;
				}
			}
		}
		
		int x = 0;
		for(StationData data : departures) {
			
			if(x < arrivals.size()) {
				
				if((arrivals.get(x).getRoute().equals(data.getRoute())) && 
						(arrivals.get(x).getExpectedTime().equals(data.getExpectedTime()))) {
					
					arrivals.get(x).setDestination(data.getDestination());
				}
				stationDataList.add(arrivals.get(x));
			}
			stationDataList.add(data);
			x++;
		}
		
		return stationDataList;
	}



	/**
	 * This method is used to return the real time data for a
	 * Dublin Bus Station. A Dublin Bus Station API Code is
	 * passed in and it returns a list of StationData objects.
	 * 
	 * @param stationApiCode The api code used to identify the station.
	 * @return stationDataList A list of StationData Objects.
	 * @throws Exception
	 */
	public static List<StationData> getDublinBusStationData(String apiCode){

		StringBuffer url = new StringBuffer("http://www.dublinbus.ie/en/RTPI/Sources-of-Real-Time-Information/?searchtype=view&searchquery=");
		url.append(apiCode);
		
		Elements htmlElements = BeanUtilities.getHtmlElementsForUrl(url.toString(),"#rtpi-results tr.even, #rtpi-results tr.odd");
		
		stationDataList = new ArrayList<StationData>();
		
		if(htmlElements != null) {
		
			for(Element e : htmlElements){
				String temp = (String)e.select("td:eq(2)").text();
				
				stationDataList.add(new StationData(e.select("td:eq(0)").text(),
						e.select("td:eq(1)").text(),BeanUtilities.formatGivenDateString(temp),"Arrival"));
				stationDataList.add(new StationData(e.select("td:eq(0)").text(),
						e.select("td:eq(1)").text(),BeanUtilities.formatGivenDateString(temp),"Departure"));
			}
			
		}
		
		return stationDataList;
	}
	

	
	/**
	 * This method is used to return the real time data for a
	 * Irish Rail Station. A Irish Rail Station API Code is 
	 * passed in and it returns a list of StationData objects.
	 * 
	 * @param stationApiCode The api code used to identify the station.
	 * @return stationDataList A list of StationData Objects.
	 * @throws Exception
	 */
	public static List<StationData> getIrishRailStationData(String apiCode) {

		stationDataList = new ArrayList<StationData>();
		
	    String url = "http://api.irishrail.ie/realtime/realtime.asmx/getStationDataByCodeXML?StationCode="+apiCode;
	    Elements htmlElements = BeanUtilities.getHtmlElementsForUrl(url,"objStationData");
	    
	    if(htmlElements != null) {
	    	
		    for(Element e : htmlElements){
		    	
		    	String route = e.select("Traincode").text();
		    	String locationType = e.select("Locationtype").text();
		    	String destination = e.select("Destination").text();
		    	
		    	if(locationType.equals("O")) {
		    		stationDataList.add(new StationData(route,destination,e.select("Expdepart").text(),"Departure"));
		    	} else if(locationType.equals("D")) {
		    		stationDataList.add(new StationData(route,destination,e.select("Exparrival").text(),"Arrival"));
		    	} else {
		    		stationDataList.add(new StationData(route,destination,e.select("Exparrival").text(),"Arrival"));
		    		stationDataList.add(new StationData(route,destination,e.select("Expdepart").text(),"Departure"));
		    	}
		    }
		    
	    }
	    
		return stationDataList;
	}
	
	
	
	/**
	 * 
	 * This method is used to return the real time data for a
	 * JJ Kavanagh Station. A JJ Kavanagh Station API Code is 
	 * passed in and it returns a list of StationData objects.
	 * 
	 * @param stationApiCode The api code used to identify the station.
	 * @return stationDataList A list of StationData Objects.
	 * @throws Exception
	 */
	public static List<StationData> getJJKavanaghStationData(String apiCode) {
		
		StringBuffer url = new StringBuffer("http://www.avego.com/db/querypt.action?ts=1327954659894&request_locale=en_US&from=");
		url.append(apiCode);

		stationDataList = new ArrayList<StationData>();
		
		JSONObject json;
		
		try {
			json = new JSONObject(BeanUtilities.readFromUrl(url.toString()));
			json = json.getJSONObject("rs");
			JSONArray jsonArray = json.getJSONArray("s");
			
			if(jsonArray.length()>0) {
				
				json = jsonArray.getJSONObject(0);
				jsonArray = json.getJSONArray("i");
				
				for (int i = 0; i < jsonArray.length(); ++i) {
				    JSONObject jsonObj = jsonArray.getJSONObject(i);
				    String[] route = jsonObj.get("r").toString().split(" \\(");
				    
				    if(route.length>0) {
					    route = route[0].split("-");
					    
					    String tempRoute = route[0].replace(" ","").substring(0, 3)+
					    		" - "+
					    		route[route.length-1].replace(" ","").substring(0, 3);

					    stationDataList.add(new StationData(tempRoute, jsonObj.get("a").toString(), 
					    		jsonObj.get("t").toString(), "Arrival"));
					    
					    stationDataList.add(new StationData(tempRoute, jsonObj.get("a").toString(), 
					    		jsonObj.get("t").toString(), "Departure"));
				    }
				}
			}
			

			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return stationDataList;
	}

	
	
	
	

	
	/**
	 * This method is used to return the real time data for a
	 * Luas Station. A Luas Station API Code is passed in and
	 * it returns a list of StationData objects.
	 * 
	 * @param stationApiCode The api code used to identify the station.
	 * @return stationDataList A list of StationData Objects.
	 * @throws Exception
	 */
	public static List<StationData> getLuasStationData(String apiCode) {
		
		StringBuffer url = new StringBuffer("http://www.luas.ie/luaspid.html?get=");
		url.append(apiCode);

		stationDataList = new ArrayList<StationData>();
		
		Elements htmlElements = BeanUtilities.getHtmlElementsForUrl(url.toString(),"div.outbound > div, div.inbound > div");
		
		if(htmlElements != null){
			
			int i = 0;
			StationData tempArrival = null;
			StationData tempDeparture = null;
			
			for(Element e : htmlElements) {

				if(!e.text().equals("No trams forecast")&&!e.text().isEmpty()){

					if(i%2==0){
						tempArrival = new StationData();
						tempArrival.setDestination(e.text());
						tempArrival.setRoute(e.parent().className());
						tempArrival.setIsArrivalOrDeparture("Arrival");
					} else {
						tempArrival.setExpectedTime(BeanUtilities.formatGivenDateString(e.text()));
						stationDataList.add(tempArrival);
						
						tempDeparture = new StationData();
						tempDeparture.setDestination(tempArrival.getDestination());
						tempDeparture.setRoute(tempArrival.getRoute());
						tempDeparture.setIsArrivalOrDeparture("Departure");
						tempDeparture.setExpectedTime(tempArrival.getExpectedTime());
						stationDataList.add(tempDeparture);
					}
					
				}
				
				i++;
			}
			
		}
		
		Collections.sort(stationDataList);
		
		return stationDataList;
	}




	
	/**
	 * This method is used to build the Bus Éireann
	 * URL that is used to get the live data.
	 * 
	 * @param apiCode
	 * @param showArrivals
	 * @return
	 */
	private static String buildBusEireannUrl(String apiCode, String showArrivals) {
		
		StringBuffer url = new StringBuffer("http://194.106.151.190/smartinfo/service/jsp/main.jsp?stName=");
		url.append(apiCode).append("&olifServerId=203&autorefresh=180&default_autorefresh=180")
		.append("&routeId=-1").append("&stopId=").append(apiCode).append("&allLines=y")
		.append("&nRows=20&showArrivals=").append(showArrivals);
		
		return url.toString();
	}


}
