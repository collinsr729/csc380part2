package csc380;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.*;
import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.errors.ApiException;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement; 
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;

import org.json.*;

public class Main {
	
	public static void main(String[] args) {
		
		
		JOptionPane.showMessageDialog(null, "Welcome to the delivery service program.","Title",JOptionPane.WARNING_MESSAGE);
		String a[] = new String[3];
		a[0] = JOptionPane.showInputDialog(null,"Enter an address:");
		int al = JOptionPane.showConfirmDialog(null, "Do you really live here", "Are you sure?", JOptionPane.OK_CANCEL_OPTION);
		if(al == JOptionPane.OK_OPTION)
			System.out.println("Ok, cool");
		else
			System.out.println("Why lie to me?");
		a[2] = null;
		
		Scanner scan = new Scanner(System.in);
		String food;
		String address;
		String input;
		
		input = scan.nextLine();
		
		while(!(input == "q"))
		{
			System.out.print("Food: ");
			food = scan.nextLine();
			
			System.out.print("\nAddress: ");
			address = scan.nextLine();

			Order order1 = new Order();
			
			order1.addItem(new Item(food));
			order1.setAddress(address);
			
			Load load = new Load(order1);
			Map map = new Map();
			
			map.calculateRoute(load.getAddresses());
			
			input = scan.nextLine();
		}

	}

}
