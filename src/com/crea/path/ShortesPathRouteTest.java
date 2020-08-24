package com.crea.path;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class ShortesPathRouteTest {

	@Test
	public void test_path() throws Exception {
		
		String data = "Sydney" +
		        "\nBangkok";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		Map<String,Integer> ab = ShortestPathRoute.main(new String[] {"/data/RealTimeRoutes.csv"});
		int distance = ab.get("distance");
		int stops = ab.get("steps");
		assertEquals(8,distance);
		assertEquals(0,stops);
	}
	@Test
	public void test_path1() throws Exception {
		
		String data = "Brisbane" +
		        "\nMelbourne";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		Map<String,Integer> ab = ShortestPathRoute.main(new String[] {"/data/RealTimeRoutes.csv"});
		int distance = ab.get("distance");
		int stops = ab.get("steps");
		assertEquals(4,distance);
		assertEquals(1,stops);
	}
	@Test
	public void test_path3() throws Exception {
		
		String data = "Brisbane" +
		        "\nBangkok";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		Map<String,Integer> ab = ShortestPathRoute.main(new String[] {"/data/RealTimeRoutes.csv"});
		int distance = ab.get("distance");
		int stops = ab.get("steps");
		assertEquals(10,distance);
		assertEquals(1,stops);
	}
}
