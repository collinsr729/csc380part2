package csc380;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class Main {


	public static void main(String[] args) {

		Variables vars = new Variables();
		boolean loadComplete = false;
		Map map = new Map();
		int startTime;
		int anotherAddress = JOptionPane.YES_OPTION;
		Load load = new Load();
		Load nextLoad = new Load();
		String newAddress;
		String confirmAddresses = "";
		Order newOrder;
		
		JOptionPane.showMessageDialog(null, "CAMPUS DELIVERY", "Delivery Service", JOptionPane.PLAIN_MESSAGE);
		
		Calendar now = Calendar.getInstance();
		startTime = now.get(Calendar.MINUTE);
	
		while(anotherAddress == JOptionPane.YES_OPTION)
		{
			newAddress = JOptionPane.showInputDialog(null,"Address: ");
			
			if(!newAddress.equals("")) 
			{
				
				if(map.checkIfInBounds(map.DistanceCall(newAddress))) 
				{
					newOrder = new Order(newAddress);
					
					if(newOrder.getTimeOfOrder() - startTime <= 1)
					{	
						load.addOrder(newOrder);
						System.out.println("Placed in first order: " + (newOrder.getTimeOfOrder() - startTime));
					}
					
					else
					{
						nextLoad.addOrder(newOrder);
						System.out.println("Placed in next order: " + (newOrder.getTimeOfOrder() - startTime));
						loadComplete = true;
					}
				}
				
				else
					JOptionPane.showMessageDialog(null, String.format("Sorry address is too far by %.2fmi",map.convertMetersToMiles(map.DistanceCall(newAddress))),
							"Error", JOptionPane.ERROR_MESSAGE);
			}//end if
			
			if(loadComplete)
			{
				ArrayList<String> addSorted = map.calculateRoute(load.getAddresses());

				for(String add : addSorted) 
					confirmAddresses = confirmAddresses + add + "\n";

				JOptionPane.showConfirmDialog(null, confirmAddresses, "Sorted", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
				load = nextLoad;
				
				now = Calendar.getInstance();
				startTime = now.get(Calendar.MINUTE);
			}
			
			anotherAddress = JOptionPane.showConfirmDialog(null, "Would you like to enter another address","More addresses",JOptionPane.YES_NO_OPTION);
			System.out.println(startTime);
		
		}//end while
	}//end main
}//end Main