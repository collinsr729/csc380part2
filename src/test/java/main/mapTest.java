package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import csc380.Map;

public class mapTest {

	@Test
	public void test() {
		Map m = new Map();
		ArrayList<String> addresses = new ArrayList<String>();
		addresses.add("7093 ny104");
		addresses.add("20 rudolph rd oswego");
		System.out.println(m.calculateRoute(addresses));
	}

}
