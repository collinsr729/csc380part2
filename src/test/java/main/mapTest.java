package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import csc380.Map;

public class mapTest {

	//@Test
	//public void testConvertMetersToMiles() 
	//{
	//	Map map = new Map();
	//	assertEquals();
	//}
	
	@Test
	public void testCheckIfInBoundsTrue()
	{
		Map map = new Map();
		
		assertTrue(map.checkIfInBounds(1609)); //1 mile in meters
	}

	@Test
	public void testCheckIfInBoundsFalse()
	{
		Map map = new Map();
		
		assertFalse(map.checkIfInBounds(5*1610)); //5+ miles
	}
	
	@Test
	public void testDistanceCall()
	{
		Map map = new Map();
	
		assertEquals(61897, map.DistanceCall("233 Slawson Drive"));
	}
	
	@Test
	public void testFindClosest()
	{
		Map map = new Map();
		ArrayList<String> testAddresses = new ArrayList();
		
		testAddresses.add("233 Slawson Drive");
		testAddresses.add("302 Second Street, Solvay");
		
		assertEquals("233 Slawson Drive", map.findClosest("7060 NY104, 13126", testAddresses));
	}
	
	@Test
	public void testGetDistanceFromJSON()
	{
		Map map = new Map();
		
		assertEquals(61897, map.getDistanceFromJSON("\"inMeters\"  61897,"));
	}
	
	@Test
	public void testCalculateRoute()
	{
		Map map = new Map();
		ArrayList<String> testList = new ArrayList();
		ArrayList<String> addressList = new ArrayList();
		
		testList.add("233 Slawson Drive");
		testList.add("302 Second Street, Solvay");
		
		addressList.add("302 Second Street, Solvay");
		addressList.add("233 Slawson Drive");
		
		assertEquals(testList, map.calculateRoute(addressList));
	}
}
