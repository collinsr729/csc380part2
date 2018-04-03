package csc380;


import java.awt.JobAttributes;
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

		Variables vars = new Variables();
		
		JOptionPane.showMessageDialog(null, "Welcome to the delivery service program.","Title",JOptionPane.WARNING_MESSAGE);
		ArrayList<String> adds = new ArrayList<String>();
		Map map = new Map();
		//GET MULTIPLE ADDRESSES
		int index = 0;
		String add = JOptionPane.showInputDialog(null,"Enter an address:");
		while (!add.equals("")){
			if(vars.DELIVERY_DISTANCE >= map.DistanceCall(add)) {
				adds.add(add);
				index++;
			}
			else
				JOptionPane.showMessageDialog(null, String.format("Sorry address is too far by %.2fmi",map.convertMetersToMiles(map.DistanceCall(add))),
						"Error", JOptionPane.ERROR_MESSAGE);
			add = JOptionPane.showInputDialog(null,"Enter an address:");
		}
		
		ArrayList<String> addSorted = map.calculateRoute(adds);
		
		JOptionPane.showConfirmDialog(null, addSorted.get(0)+"\n"+addSorted.get(1)+"\n"+addSorted.get(2), "Sorted", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
		

	}

}
