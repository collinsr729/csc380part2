package csc380;



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

	public String[] addresses;
	final String HOME_BASE = "7060 NY104";
	
	public Map() {

	}
	
	public void calculateRoute(String addresses[]) 
	{
		ArrayList<String> listOfAddresses = new ArrayList<String>();
		ArrayList<Integer> distancesFromHomeInMeters = new ArrayList<Integer>();
		ArrayList<Double> distancesFromHomeInMiles = new ArrayList<Double>();
		
		for(int i = 0; i < addresses.length; i++)
		{
			if(addresses[i] == null)
				break;
			listOfAddresses.add(addresses[i]);
		}

		distancesFromHomeInMeters = getDistancesFromHome(listOfAddresses);
		
		for(int i = 0; i < distancesFromHomeInMeters.size(); i++)	
			distancesFromHomeInMiles.add(convertMetersToMiles(distancesFromHomeInMeters.get(i)));
		
		System.out.println(distancesFromHomeInMiles.get(0));
		System.out.println(distancesFromHomeInMiles.get(1));
		
		int index = 0 ;
		
		while(checkIfInBounds(distancesFromHomeInMiles.get(index)))
		{
			index++;
			System.out.println("In bounds");
		}
			
	}
	
	public ArrayList getDistancesFromHome(ArrayList<String> listOfAddresses)
	{
		final String GEO_API_KEY = "AIzaSyCUcSoFBlKCqqxApVpprxj9CK6L7RrBhTU";
		final String HOME = "7060 NY104";
		
		ArrayList<Integer> results = new ArrayList<Integer>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		GeoApiContext context = new GeoApiContext.Builder()
				.apiKey(GEO_API_KEY).build();
		DistanceMatrix trix = null;
		
		
		for(int i = 0; i < listOfAddresses.size(); i++) 
		{
			try
			{
				DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context); 
		         trix = req.origins(HOME)
		                .destinations(listOfAddresses.get(i))
		                .mode(TravelMode.DRIVING)
		                .avoid(RouteRestriction.TOLLS)
		                .language("en-EN")
		                .await();
			}
			
			catch(ApiException e)
			{
				System.out.println("There is an issue with the API request.");
			} 
			
			catch(Exception e)
			{
		        System.out.println(e.getMessage());
		    }   
			
			System.out.println(gson.toJson(trix.rows));
			results.add(Integer.parseInt((getDistanceFromJSON(gson.toJson(trix.rows)))));
		}
		
		return results;
	}
	
	private boolean checkIfInBounds(Double distance)
	{
		if(distance <= 2)
			return true;
		
		else {
			System.out.println("Not in bounds!");
			return false;
		}
	}
	
	private Double convertMetersToMiles(Integer distance)
	{
		double distanceInMiles;
		
		distanceInMiles = distance * 0.000621371;
		return distanceInMiles;
	}

	private String getDistanceFromJSON(String o)
	{   //Finds inMeters value from output
		o = o.substring(o.indexOf("\"inMeters\"")+12);
		int index = o.indexOf(",");
		o = o.substring(0, index);
		
		return o;
		
	}

}
