package main;

import static org.junit.Assert.*;

import org.junit.Test;

import csc380.Item;

public class itemTest {

	@Test
	public void testPrepTime() {
		Item i = new Item("steak");
		assertEquals(20, i.getPrepTime());
	}

}
