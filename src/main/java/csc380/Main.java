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
		ArrayList<Load> loads = new ArrayList<Load>();
		String newAddress;
		String confirmAddresses = "";
		Order newOrder;

		JOptionPane.showMessageDialog(null, "CAMPUS DELIVERY", "Delivery Service", JOptionPane.PLAIN_MESSAGE);

		now = Calendar.getInstance();
		startTime = now.get(Calendar.MINUTE);
		loads.add(new Load());

		while (anotherAddress == JOptionPane.YES_OPTION) {
			newAddress = JOptionPane.showInputDialog(null, "Address: ");

			if (!newAddress.equals("")) {

				if (map.checkIfInBounds(map.DistanceCall(newAddress))) {
					newOrder = new Order(newAddress);

					if (newOrder.getTimeOfOrder() - startTime <= 1) {
						loads.get(loads.size()-1).addOrder(newOrder);
						System.out.println("Placed in load"+loads.size()+" at time: " + newOrder.getTimeOfOrder());
					}
					else if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Time has detected new Load, Is this correct", "New Load", JOptionPane.YES_NO_OPTION)){
						startTime = now.get(Calendar.MINUTE);
						Load ld = new Load();
						ld.addOrder(newOrder);
						loads.add(ld);
						System.out.println("Placed in load"+loads.size()+" at time: " + newOrder.getTimeOfOrder());
					} else {
						loads.get(loads.size()-1).addOrder(newOrder);
						System.out.println("Placed in load"+loads.size()+" at time: " + newOrder.getTimeOfOrder());
					}
				}

				else
					JOptionPane.showMessageDialog(null,
							String.format("Sorry address is too far by %.2fmi",
									map.convertMetersToMiles(map.DistanceCall(newAddress))),
							"Error", JOptionPane.ERROR_MESSAGE);
			} // end if

			now = Calendar.getInstance();

			anotherAddress = JOptionPane.showConfirmDialog(null, "Would you like to enter another address",
					"More addresses", JOptionPane.YES_NO_OPTION);
			System.out.println(startTime);

		} // end while
		ArrayList<String> ad2 = new ArrayList<String>();
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
		for(Load l : loads) {
			for(String s : l.getAddresses())
				ad2.add(s);
			for(Order s : l.getOrders())
					ords.add(s);
		}
		ArrayList<String> addSorted = map.calculateRoute(ad2);

		for (String add : addSorted)
			confirmAddresses = confirmAddresses + add + "\n";

		JOptionPane.showConfirmDialog(null, confirmAddresses, "Sorted", JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		
		JOptionPane.showConfirmDialog(null, ords.toString(), "Order Details", JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		
	}// end main
}// end Main