package ie.smartcommuter.client;


import ie.smartcommuter.models.StationData;
import ie.smartcommuter.service.RealTime;

/**
 * This class is used as a client that can
 * connect to the webservice.
 * @author Shane Bryan Doyle
 */
public class SmartCommuterClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for(StationData data: RealTime.getLuasStationData("Dundrum")){
			
			data.printStationData();
		}
		
	}

}
