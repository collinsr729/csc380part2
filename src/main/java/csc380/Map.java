package csc380;

import java.text.DecimalFormat;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;

public class Map {
	Variables vars = new Variables();

	public ArrayList<String> calculateRoute(ArrayList<String> addresses) {

		ArrayList<String> res = new ArrayList<String>();

		while (!addresses.isEmpty()) {
			String closest = findClosest(addresses);
			addresses.remove(closest);
			res.add(closest);
		}
		return res;
	}

	public long DistanceCall(String address) {

		GeoApiContext context = new GeoApiContext.Builder().apiKey(vars.GEO_API_KEY).build();
		DistanceMatrix trix = null;
		System.out.println("DistanceCall: " + address);
		try {
			DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
			trix = req.origins(vars.HOME)
					.destinations(address)
					.mode(TravelMode.DRIVING)
					.language("en-EN").await();
		} catch (ApiException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String distance = gson.toJson(trix);
		System.out.println(distance);
		return getDistanceFromJSON(distance);

	}

	public String findClosest(ArrayList<String> adds) {

		ArrayList<Long> results = new ArrayList<Long>();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		GeoApiContext context = new GeoApiContext.Builder().apiKey(vars.GEO_API_KEY).build();
		DistanceMatrix trix = null;
		for (int i = 0; i < adds.size(); i++) {
			try {
				DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
				trix = req.origins(vars.HOME)
						.destinations(adds.get(i))
						.mode(TravelMode.DRIVING)
						.language("en-EN").await();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			results.add(getDistanceFromJSON(gson.toJson(trix.rows)));
		}

		int result = 0;
		for (int i = 0; i < results.size(); i++) {
			if (results.get(result) < results.get(i)) {
				result = i;
			}
		}
		return adds.get(result);
	}

	private long getDistanceFromJSON(String o) { // Finds inMeters value from output
		o = o.substring(o.indexOf("\"inMeters\"") + 12);
		return Long.parseLong(o.substring(0, o.indexOf(",")));

	}

	public Double convertMetersToMiles(long distance) {
		return distance * 0.00062137119;
	}

	public boolean checkIfInBounds(long d) {
		return vars.DELIVERY_DISTANCE >= convertMetersToMiles(d);
	}
}
