package csc380;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {

	
	public static void main(String[] args) {

		Variables vars = new Variables();
		
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
		int startTime, timeOfOrderPlaced;
		String input, food, address;
		
		input = scan.nextLine();
		
		while(input.compareTo("q") != 0)
		{
			Load load = new Load();
			Load nextLoad = new Load();
			
			load = nextLoad;
			
			Calendar now = Calendar.getInstance();
			startTime = now.get(Calendar.MINUTE);


		JOptionPane.showMessageDialog(null, "Welcome to the delivery service program.","Title",JOptionPane.WARNING_MESSAGE);
		ArrayList<String> adds = new ArrayList<String>();
		Map map = new Map();
		//GET MULTIPLE ADDRESSES
		int index = 0;
		String newAddress = JOptionPane.showInputDialog(null,"Enter an address:");
		while (!newAddress.equals("") | newAddress != null){
			System.out.println(newAddress);
			if(map.checkIfInBounds(map.DistanceCall(newAddress))) {
				adds.add(newAddress);
				index++;
			}
			else
				JOptionPane.showMessageDialog(null, String.format("Sorry address is too far by %.2fmi",map.convertMetersToMiles(map.DistanceCall(newAddress))),
						"Error", JOptionPane.ERROR_MESSAGE);
			newAddress = JOptionPane.showInputDialog(null,"Enter an address:");
		}
		
		ArrayList<String> addSorted = map.calculateRoute(adds);
		
//		Scanner scan = new Scanner(System.in);
//		int startTime, timeOfOrderPlaced;
//		String input, food, address;
//		
//		JOptionPane.showConfirmDialog(null, addSorted.get(0)+"\n"+addSorted.get(1)+"\n"+addSorted.get(2), "Sorted", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
//
//		while(input.compareTo("q") != 0)
//		{
//			Load load = new Load();
//			Load nextLoad = new Load();
//			
//			load = nextLoad;
//			
//			Calendar now = Calendar.getInstance();
//			startTime = now.get(Calendar.MINUTE);
//
//			
//			while(input.compareTo("next") == 0)
//			{
//				Calendar now2 = Calendar.getInstance();
//				timeOfOrderPlaced = now2.get(Calendar.MINUTE);
//				//System.out.println(timeOfOrderPlaced);
//				
//				System.out.print("Food: ");
//				food = scan.nextLine();
//				System.out.print("\nAddress: ");
//				address = scan.nextLine();
//				
//				Order newOrder = new Order();
//				newOrder.addItem(new Item(food));
//				newOrder.setAddress(address);
//				
//				if(timeOfOrderPlaced - startTime <= 1)
//				{
//					//System.out.println(timeOfOrderPlaced - startTime);
//					load.addOrder(newOrder);
//				}
//				
//				else if(timeOfOrderPlaced - startTime > 1)
//				{	
//					nextLoad.addOrder(newOrder);
//					System.out.println("Added to next load. Time elapsed since start " 
//												+ (timeOfOrderPlaced - startTime));
//					input = "next load";
//				}
//				
//				if(load.getSize() == 3)
//					input = "next load";
//				
//				else
//					input = scan.nextLine();
//			}
//			
//			System.out.println("Load complete");
//			
//			Map map = new Map();
//			
//			map.calculateRoute(load.getAddresses());
//			
//			input = scan.nextLine();
//		}

	}
	}

}
