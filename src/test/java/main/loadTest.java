package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import csc380.Load;
import csc380.Order;

public class loadTest {

	@Test
	public void testGetAddresses() {
		Load ld = new Load();
		ld.addOrder(new Order("7093 ny104 13126"));
		ld.addOrder(new Order("7200 ny104 13126"));
		ArrayList<String> alist = new ArrayList<String>();
		alist.add("7093 ny104 13126");
		alist.add("7200 ny104 13126");
		assertEquals(alist, ld.getAddresses());
	}
	@Test
	public void testGetSize() {
		Load ld = new Load();
		ld.addOrder(new Order("7093 ny104 13126"));
		ld.addOrder(new Order("7200 ny104 13126"));
		assertEquals(2, ld.getSize());
	}
	@Test
	public void testGetOrder() {
		Load ld = new Load();
		ld.addOrder(new Order("7093 ny104 13126"));
		ld.addOrder(new Order("7200 ny104 13126"));
		Order o = ld.getOrder(1);
		assertEquals(o.getAddress(),"7200 ny104 13126");
	}

}
