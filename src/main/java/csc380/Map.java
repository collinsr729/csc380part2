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

	public String[] addresses;
	final String HOME_BASE = "7060 NY104";
	
	public Map() {

	}
	
	public String calculateRoute(String home, String addresses[]) {
		ArrayList<String> arr = new ArrayList<String>();
		for(int i = 0; i< addresses.length; i++) {
			if(addresses[i]==null)
				break;
			arr.add(addresses[i]);
		}
//		home = findClosest(home, arr);
		String res = "";
//		res += home+"\n";
		while (!arr.isEmpty()) {
			home = findClosest(home, arr);
			arr.remove(home);
			res += home+"\n";
		}
		// Accesses a map API to get live updates on a route/ or find closest
		// address and go in order from the next closest house to that one
		
		return res;
	}
	
	public int DistanceCall(String address)
	{
		int distanceAsNumber;

		String address1, address2, address3;
		
		address1 = address;

		String distanceAsString;
		
		final String GEO_API_KEY = "AIzaSyCUcSoFBlKCqqxApVpprxj9CK6L7RrBhTU";
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(GEO_API_KEY)
			    .build();
		DistanceMatrix trix = null;
		
		try
		{
			DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context); 
	         trix = req.origins(HOME_BASE)
	                .destinations(address1,"7249 Dryer Rd Victor")
	                .mode(TravelMode.DRIVING)
	                //.avoid(RouteRestriction.TOLLS)
	                .language("en-EN")
	                .await();
		}
		
		catch(ApiException e){
	        
	    } catch(Exception e){
	        System.out.println(e.getMessage());
	    }   
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		System.out.println(gson.toJson(trix.rows));			//rows is an array: ex. rows[0] = output from first origin
		
		distanceAsString = gson.toJson(trix);
		
		distanceAsString = getDistance(distanceAsString);
		
		distanceAsNumber = Integer.parseInt(distanceAsString);
		
//		System.out.println(distanceAsNumber - 22);
		return distanceAsNumber;
	}
	
	public String findClosest(String add, ArrayList<String> a) {
		ArrayList<Integer> results = new ArrayList<Integer>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
			final String GEO_API_KEY = "AIzaSyCUcSoFBlKCqqxApVpprxj9CK6L7RrBhTU";
			GeoApiContext context = new GeoApiContext.Builder()
				    .apiKey(GEO_API_KEY)
				    .build();
			DistanceMatrix trix = null;
		for(int i = 0; i<a.size(); i++) {
			try
			{
				DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context); 
		         trix = req.origins(add)
		                .destinations(a.get(i))
		                .mode(TravelMode.DRIVING)
		                //.avoid(RouteRestriction.TOLLS)
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
			results.add(Integer.parseInt((getDistance(gson.toJson(trix.rows)))));
		}
		int result = 0;
		for(int i = 0; i < results.size();i++) {
			if(results.get(result) > results.get(i)) {
				result = i;
			}
		}
		return a.get(result);
	}

	private String getDistance(String o) {   //Finds inMeters value from output
		// TODO Auto-generated method stub
		o = o.substring(o.indexOf("\"inMeters\"")+12);
		return o.substring(0, o.indexOf(","));
	}

}
