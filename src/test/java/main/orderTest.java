package main;

import static org.junit.Assert.*;

import org.junit.Test;

import csc380.Item;
import csc380.Order;

public class orderTest {

	@Test
	public void testTotalPrep() {
		Order o = new Order("7093 ny104 13126");
		o.addItem(new Item("steak"));
		o.addItem(new Item("burger"));
		assertEquals(new Item("steak").getPrepTime(), o.getTotalPrepTime());
	}
	@Test
	public void testNumItems() {
		Order o = new Order("7093 ny104 13126");
		o.addItem(new Item("steak"));
		o.addItem(new Item("burger"));
		assertEquals(2, o.getNumberOfItems());
	}

}
