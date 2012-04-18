package ie.smartcommuter.models;

import java.util.List;

/**
 * This class creates instance of Transport Companies.
 * @author Shane Bryan Doyle
 */
public class TransportCompany {
	
	private String name;
	private ContactDetails details;
	private List<Station> stations;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ContactDetails getDetails() {
		return details;
	}
	public void setDetails(ContactDetails details) {
		this.details = details;
	}
	public List<Station> getStations() {
		return stations;
	}
	public void setStations(List<Station> stations) {
		this.stations = stations;
	} 
	
}
