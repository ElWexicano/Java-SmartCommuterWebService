package ie.smartcommuter.main;

import ie.smartcommuter.beans.StationData;
import ie.smartcommuter.service.RealTime;

public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		try {
			//RealTime.getLuasStationData("Dundrum");
			//RealTime.getDublinBusStationData("4477");
			//RealTime.getBusEireannStationData("Wexford+%28O%27Hanrahan+Stn%29");
			//RealTime.getIrishRailStationData("mhide");
			//RealTime.getJJKavanaghStationData("9");
			
			for(StationData e : RealTime.getBusEireannStationData("Wexford+%28O%27Hanrahan+Stn%29")){
				e.printStationData();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
