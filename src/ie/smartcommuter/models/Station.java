package ie.smartcommuter.models;

import java.util.List;

public class Station {
	
	private String name;
	private String address;
	private String apiCode;
	private CoOrdinates coOrdinates;
	private List<String> arrivals;
	private List<String> departures;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getApiCode() {
		return apiCode;
	}
	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}
	public CoOrdinates getCoOrdinates() {
		return coOrdinates;
	}
	public void setCoOrdinates(CoOrdinates coOrdinates) {
		this.coOrdinates = coOrdinates;
	}
	public List<String> getDepartures() {
		return departures;
	}
	public void setDepartures(List<String> departures) {
		this.departures = departures;
	}
	public List<String> getArrivals() {
		return arrivals;
	}
	public void setArrivals(List<String> arrivals) {
		this.arrivals = arrivals;
	}
	
}
