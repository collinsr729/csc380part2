package main;

import static org.junit.Assert.*;
import org.junit.Test;
import csc380.Map;

public class mapTest {

	@Test
	public void testConvertMetersToMiles() 
	{
		Map map = new Map();
		double returnedResult, expectedResult;
		
		returnedResult = map.convertMetersToMiles(1609);
		expectedResult = 0.999786;
		
		assertEquals(expectedResult, returnedResult);
	}
	
	@Test
	public void testCheckIfInBoundsTrue()
	{
		Map map = new Map();
		
		assertTrue(map.checkIfInBounds(1.0));
	}

	@Test
	public void testCheckIfInBoundsFalse()
	{
		Map map = new Map();
		
		assertFalse(map.checkIfInBounds(3.0));
	}
}
