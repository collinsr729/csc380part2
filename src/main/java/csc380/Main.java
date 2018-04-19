package csc380;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class Main {

	static Calendar now;
	
	public static void main(String[] args) {

		Variables vars = new Variables();
		Map map = new Map();
		int startTime;
		int anotherAddress = JOptionPane.YES_OPTION;
		ArrayList<Load> allLoads = new ArrayList<Load>();
		String newAddress;
		String confirmAddresses = "";
		Order newOrder;
		boolean loadComplete = false;
		boolean nextLoadInitiated = false;

		JOptionPane.showMessageDialog(null, "Welcome to CAMPUS DELIVERY", "Delivery Service", JOptionPane.PLAIN_MESSAGE);
		now = Calendar.getInstance();
		startTime = now.get(Calendar.MINUTE);
		allLoads.add(new Load());
		
		while (anotherAddress == JOptionPane.YES_OPTION){
			System.out.println("Start time: " + startTime);
			
			newAddress = JOptionPane.showInputDialog(null, "Address: ");

			if (!newAddress.equals(""))
			{	
				if (map.checkIfInBounds(map.DistanceCall(newAddress)))
				{
					newOrder = new Order(newAddress);

					if (newOrder.getTimeOfOrder() - startTime <= 1)
					{
						allLoads.get(allLoads.size()-1).addOrder(newOrder);
						System.out.println("Placed in load " + allLoads.size() + " at time: " + newOrder.getTimeOfOrder());
						
						anotherAddress = JOptionPane.showConfirmDialog(null, "Would you like to enter another address to the load?",
								"More addresses", JOptionPane.YES_NO_OPTION);
						
						if(anotherAddress == JOptionPane.NO_OPTION)
							loadComplete = true;
					}
					
					else if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Time has detected new Load,"
														+ " Is this correct", "New Load", JOptionPane.YES_NO_OPTION))
					{
						startTime = now.get(Calendar.MINUTE);
						Load nextLoad = new Load();
						nextLoad.addOrder(newOrder);
						allLoads.add(nextLoad);
						System.out.println("Placed in load "+ allLoads.size() + " at time: " + newOrder.getTimeOfOrder());
						loadComplete = true;
						nextLoadInitiated = true;
					}
					
					else 
					{
						allLoads.get(allLoads.size() - 1).addOrder(newOrder);
						System.out.println("Placed in load "+ allLoads.size() + " at time: " + newOrder.getTimeOfOrder());
						anotherAddress = JOptionPane.showConfirmDialog(null, "Would you like to enter another address to the load?",
								"More addresses", JOptionPane.YES_NO_OPTION);
						if(anotherAddress == JOptionPane.NO_OPTION)
							loadComplete = true;
					}
				}

				else
					JOptionPane.showMessageDialog(null,
							String.format("Sorry address is too far by %.2fmi",
									map.convertMetersToMiles(map.DistanceCall(newAddress))),
							"Error", JOptionPane.ERROR_MESSAGE);
			} // end if
			
			if(loadComplete)
			{
				ArrayList<String> loadAddresses = new ArrayList<String>();
				ArrayList<Order> ords = new ArrayList<Order>() {
					public String toString() {
						String res = "";
						int count = 0;
						for(Order o : this) {
							res += o+" " + ++count;
						}
						return res;
					}
				};
				for(Load l : allLoads) {
					for(String s : l.getAddresses())
						loadAddresses.add(s);
					for(Order s : l.getOrders())
							ords.add(s);
				}
				
				if(!nextLoadInitiated)
					loadAddresses = allLoads.get(allLoads.size() - 1).getAddresses();
				else
					loadAddresses = allLoads.get(allLoads.size() - 2).getAddresses();
				
				ArrayList<String> sortedAddresses = map.calculateRoute(loadAddresses);

				for (String add : sortedAddresses)
					confirmAddresses = confirmAddresses + add + "\n";

				JOptionPane.showConfirmDialog(null, confirmAddresses, "Sorted", JOptionPane.PLAIN_MESSAGE,
						JOptionPane.INFORMATION_MESSAGE);
				
				JOptionPane.showConfirmDialog(null, ords.toString(), "Order Details", JOptionPane.PLAIN_MESSAGE,
						JOptionPane.INFORMATION_MESSAGE);
				
				now = Calendar.getInstance();
				startTime = now.get(Calendar.MINUTE);
				if(!nextLoadInitiated)
					allLoads.add(new Load());
				String s = JOptionPane.showInputDialog(null, "Are there any more addresses?");
				
				if(s.compareTo("no") != 0)
				{
					anotherAddress = JOptionPane.YES_OPTION;
					confirmAddresses = "";
					loadComplete = false;
				}
				
				else
					anotherAddress = JOptionPane.NO_OPTION;
				
			}//end if
		} // end while
	}// end main
}// end Main