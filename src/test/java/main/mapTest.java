package main;

import static org.junit.Assert.*;
import org.junit.Test;
import csc380.Map;

public class mapTest {

	@Test
	public void test() {
		Map m = new Map();
		String[] addresses = new String[2];
		addresses[0] = "7093 ny104";
		addresses[1] = "20 rudolph rd oswego";
		System.out.println(m.calculateRoute("7060 ny104", addresses));
	}

}
