package ie.smartcommuter.client;


import ie.smartcommuter.models.StationData;
import ie.smartcommuter.service.RealTime;

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
