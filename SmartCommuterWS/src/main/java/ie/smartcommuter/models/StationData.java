package ie.smartcommuter.models;



public class StationData implements Comparable<StationData> {
	
	private String route;
	private String destination;
	private String expectedTime;
	private String isArrivalOrDeparture;
	
	public StationData(){}
	
	public StationData(String route, String destination, String expectedTime){
		this.route = route;
		this.destination = destination;
		this.expectedTime = expectedTime;
	}
	
	public StationData(String route, String destination, String expectedTime, String arrOrDep){
		this.route = route;
		this.destination = destination;
		this.expectedTime = expectedTime;
		this.setIsArrivalOrDeparture(arrOrDep);
	}
	
	public String getExpectedTime() {
		return expectedTime;
	}
	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getIsArrivalOrDeparture() {
		return isArrivalOrDeparture;
	}
	public void setIsArrivalOrDeparture(String isArrivalOrDeparture) {
		this.isArrivalOrDeparture = isArrivalOrDeparture;
	}
	
	public void printStationData(){
		System.out.print("Route: "+getRoute());
		System.out.print(", Destination: "+getDestination());
		System.out.print(", Expected Time: "+getExpectedTime());
		System.out.print(", Type: "+getIsArrivalOrDeparture()+"\n");
	}

	public int compareTo(StationData stationData) {

		long time1 = BeanUtilities.getTimestampForStringTime(stationData.getExpectedTime());
		long time2 = BeanUtilities.getTimestampForStringTime(this.getExpectedTime());
		
		return time1>time2 ? 0 : 1;
	}

}
