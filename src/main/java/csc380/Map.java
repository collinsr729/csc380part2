package csc380;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;

public class Map {
	
	private Variables vars;
	
	public Map()
	{
		vars = new Variables();
	}

	public ArrayList<String> calculateRoute(ArrayList<String> addresses)
	{
		ArrayList<String> res = new ArrayList<String>();
		String closest = vars.HOME;
		while (!addresses.isEmpty())
		{
			closest = findClosest(closest, addresses);
			addresses.remove(closest);
			res.add(closest);
		}
		
		return res;
	}

	public long DistanceCall(String address) 
	{
		GeoApiContext context = new GeoApiContext.Builder().apiKey(vars.GEO_API_KEY).build();
		DistanceMatrix trix = null;
		
		try 
		{
			DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
			trix = req.origins(vars.HOME)
					.destinations(address)
					.mode(TravelMode.DRIVING)
					.language("en-EN").await();
		} 
		catch (ApiException e){
			System.out.println(e.getMessage());
		}
		catch (Exception e) { 
			System.out.println(e.getMessage());
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String distance = gson.toJson(trix);
		return getDistanceFromJSON(distance);
	}

	public String findClosest(String home, ArrayList<String> adds) {

		ArrayList<Long> results = new ArrayList<Long>();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		GeoApiContext context = new GeoApiContext.Builder().apiKey(vars.GEO_API_KEY).build();
		for (int i = 0; i < adds.size(); i++) {
			DistanceMatrix trix = null;
			try {
				DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
				trix = req.origins(home)
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
			if (results.get(result) > results.get(i)) {
				result = i;
			}
		}
		return adds.get(result);
	}

	public long getDistanceFromJSON(String o) { // Finds inMeters value from output
		o = o.substring(o.indexOf("\"inMeters\"") + 12);
		return Long.parseLong(o.substring(0, o.indexOf(",")));

	}
	
	public boolean checkValidAddress(String address) {
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
		distance = distance.substring(distance.indexOf("STATUS")+10);
		distance = distance.substring(0,distance.indexOf(","));
		return distance.equalsIgnoreCase("NOT_FOUND");
	}

	public Double convertMetersToMiles(long distance) 
	{
		return distance * 0.00062137119;
	}

	public boolean checkIfInBounds(long d) 
	{
		return vars.DELIVERY_DISTANCE >= convertMetersToMiles(d);
	}
}
