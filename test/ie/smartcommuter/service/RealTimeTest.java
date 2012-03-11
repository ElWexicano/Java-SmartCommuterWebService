package ie.smartcommuter.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import ie.smartcommuter.models.StationData;

import org.junit.Test;

import com.sun.org.apache.regexp.internal.recompile;

public class RealTimeTest {

	@Test
	public void testGetStationData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBusEireannStationData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDublinBusStationData() {
		
		List<StationData> emptyData = new ArrayList<StationData>();
		
		List<StationData> data = RealTime.getDublinBusStationData("Dundrum");
		
		assertNotNull("The Station Data is not null", data);
		
		assertNotSame(emptyData, data);
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetIrishRailStationData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetJJKavanaghStationData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLuasStationData() {
		fail("Not yet implemented");
	}

}
