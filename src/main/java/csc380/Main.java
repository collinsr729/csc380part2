package csc380;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class Main {


	public static void main(String[] args) {

		Variables vars = new Variables();
		
		Calendar now = Calendar.getInstance();
		int startTime;

		JOptionPane.showMessageDialog(null, "Welcome to the delivery service program.","Delivery Service",JOptionPane.PLAIN_MESSAGE);
		Map map = new Map();
		
		//GET MULTIPLE ADDRESSES
		int anotherAddress = JOptionPane.YES_OPTION;
		Load load = new Load();
		Load nextLoad = new Load();
		
		while(anotherAddress == JOptionPane.YES_OPTION)
		{
			String newAddress = JOptionPane.showInputDialog(null,"Enter an address:");
			
			if(!newAddress.equals("")) 
			{
				startTime = now.get(Calendar.SECOND);
				load = nextLoad;
				
				if(map.checkIfInBounds(map.DistanceCall(newAddress))) 
				{
					Order newOrder = new Order(newAddress);
					
					if(newOrder.getTimeOfOrder() - startTime <= 10)
					{	
						load.addOrder(newOrder);
						System.out.println("Placed in first order: " + (newOrder.getTimeOfOrder() - startTime));
					}
					
					else
					{
						nextLoad.addOrder(newOrder);
						System.out.println("Placed in next order: " + (newOrder.getTimeOfOrder() - startTime));
					}
				}
				else
					JOptionPane.showMessageDialog(null, String.format("Sorry address is too far by %.2fmi",map.convertMetersToMiles(map.DistanceCall(newAddress))),
							"Error", JOptionPane.ERROR_MESSAGE);
				anotherAddress = JOptionPane.showConfirmDialog(null, "Would you like to enter another address","More addresses",JOptionPane.YES_NO_OPTION);
				startTime = now.get(Calendar.MINUTE);
			}
		}


		ArrayList<String> addSorted = map.calculateRoute(load.getAddresses());

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
