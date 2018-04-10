package csc380;


import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JWindow;

public class Main {

	
	public static void main(String[] args) {

		Variables vars = new Variables();
		
		JOptionPane.showMessageDialog(null, "Welcome to the delivery service program.","Title",JOptionPane.PLAIN_MESSAGE);
				
//		Scanner scan = new Scanner(System.in);
//		int startTime, timeOfOrderPlaced;
//		String input, food, address;
		Calendar now = Calendar.getInstance();
		int startTime = now.get(Calendar.MINUTE);
		
//		input = scan.nextLine();
//		
//		while(input.compareTo("q") != 0)
//		{
//			Load load = new Load();
//			Load nextLoad = new Load();
//			
//			load = nextLoad;
//			
//			
//		}

		JOptionPane.showMessageDialog(null, "Welcome to the delivery service program.","Title",JOptionPane.WARNING_MESSAGE);
		ArrayList<String> addresses = new ArrayList<String>();
		ArrayList<Order> orders = new ArrayList<Order>();
		Map map = new Map();
		//GET MULTIPLE ADDRESSES
		int anotherAddress = JOptionPane.YES_OPTION;
		int index = 0;
		while(anotherAddress == JOptionPane.YES_OPTION){
			String newAddress = JOptionPane.showInputDialog(null,"Enter an address:");
			System.out.println(newAddress);
			if(map.checkIfInBounds(map.DistanceCall(newAddress))) {
				addresses.add(newAddress);
				orders.add(new Order(newAddress));
				orders.get(0).fillOrder();
				index++;
			}
			else
				JOptionPane.showMessageDialog(null, String.format("Sorry address is too far by %.2fmi",map.convertMetersToMiles(map.DistanceCall(newAddress))),
						"Error", JOptionPane.ERROR_MESSAGE);
			anotherAddress = JOptionPane.showConfirmDialog(null, "Would you like to enter another address","More addresses",JOptionPane.YES_NO_OPTION);
		}
		
		ArrayList<String> addSorted = map.calculateRoute(addresses);
		
		String confirmAddresses = "";
		for(String add : addSorted) {
			confirmAddresses = confirmAddresses+add+"\n";
		}

		JOptionPane.showConfirmDialog(null, confirmAddresses, "Sorted", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	

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
