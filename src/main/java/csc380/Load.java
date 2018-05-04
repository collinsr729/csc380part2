package csc380;

import java.util.ArrayList;

public class Load {

	private ArrayList<Order> orders;
	
	public Load()
	{
		orders = new ArrayList<Order>();
	}
	
	public void addOrder(Order newOrder)
	{
		orders.add(newOrder);
		System.out.println("new order created: " + orders.size());
	}
	
	public ArrayList<String> getAddresses()
	{		
		ArrayList<String> addresses = new ArrayList<String>();
		for(Order o : orders)
			addresses.add(o.getAddress());
		return addresses;
	}
	
	public int getSize()
	{
		return orders.size();
	}
	
	public ArrayList<Order> getOrders()
	{
		return orders;
	}
	
	public String toString() 
	{
		return orders.toString();
	}
	
		
	public Order getOrder(int index)
	{
		return orders.get(index);
	}
	
	public int getOrderSize()
	{
		return orders.size();
	}
	

}
