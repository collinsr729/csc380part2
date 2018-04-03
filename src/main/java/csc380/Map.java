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
	
	private final String HOME_BASE = "7060 NY104";
	
	public Map() 
	{


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
		
		distancesFromHomeInMiles = orderClosestToHome(distancesFromHomeInMiles);
		
		System.out.println(distancesFromHomeInMiles.get(0));
		System.out.println(distancesFromHomeInMiles.get(1));
		System.out.println(distancesFromHomeInMiles.get(2));
		
		for(int i = 0; i < distancesFromHomeInMiles.size(); i++)
			if(checkIfInBounds(distancesFromHomeInMiles.get(i)))
			{
				
			}
		
			else
				System.out.println("Order number \" \" is out of bounds. Please notify the customer. ");
		
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
			
			results.add(Integer.parseInt((getDistanceFromJSON(gson.toJson(trix.rows)))));
		}
		
		return results;
	}
	
	public boolean checkIfInBounds(Double distance)
	{
		if(distance <= 2)
			return true;
		else
			return false;
	}
	
	public Double convertMetersToMiles(Integer distance)
	{
		double distanceInMiles, roundedDistance;
		DecimalFormat decFormat = new DecimalFormat("#.######");
		
		distanceInMiles = distance * 0.000621371;
		roundedDistance = Double.valueOf(decFormat.format(distanceInMiles));
		
		return roundedDistance;
	}

	private String getDistanceFromJSON(String o)
	{   //Finds inMeters value from output
		o = o.substring(o.indexOf("\"inMeters\"")+12);
		int index = o.indexOf(",");
		o = o.substring(0, index);
		
		return o;
		
	}
	
	private ArrayList orderClosestToHome(ArrayList<Double> distancesFromHomeInMiles)
	{
		double temporary;
		
		if(distancesFromHomeInMiles.size() == 1)
			return distancesFromHomeInMiles;
		
		else if(distancesFromHomeInMiles.size() == 2)
		{
			if(distancesFromHomeInMiles.get(0) > distancesFromHomeInMiles.get(1))
			{
				temporary = distancesFromHomeInMiles.get(0);
				distancesFromHomeInMiles.set(0, distancesFromHomeInMiles.get(1));
				distancesFromHomeInMiles.set(1, temporary);
			}
			
			return distancesFromHomeInMiles;
		}
		
		else if(distancesFromHomeInMiles.size() == 3)
		{	
			if(distancesFromHomeInMiles.get(0) > distancesFromHomeInMiles.get(1))
			{
				temporary = distancesFromHomeInMiles.get(0);
				distancesFromHomeInMiles.set(0, distancesFromHomeInMiles.get(1));
				distancesFromHomeInMiles.set(1, temporary);
			}
			
			if(distancesFromHomeInMiles.get(1) > distancesFromHomeInMiles.get(2))
			{
				temporary = distancesFromHomeInMiles.get(1);
				distancesFromHomeInMiles.set(1, distancesFromHomeInMiles.get(2));
				distancesFromHomeInMiles.set(2, temporary);
			}
			
			if(distancesFromHomeInMiles.get(0) > distancesFromHomeInMiles.get(1))
			{
				temporary = distancesFromHomeInMiles.get(0);
				distancesFromHomeInMiles.set(0, distancesFromHomeInMiles.get(1));
				distancesFromHomeInMiles.set(1, temporary);
			}
			
			return distancesFromHomeInMiles;
		}
		
		else
			return distancesFromHomeInMiles;
	}

}
