package csc380;

import java.util.ArrayList;

public class Load {
	
	private ArrayList<Order> orders;
	String addresses[];
	
	public Load()
	{
		orders = new ArrayList<Order>();
	}
	
	public void addOrder(Order newOrder)
	{
		orders.add(newOrder);
	}
	
	public String[] getAddresses()
	{
		addresses = new String[getSize()];
		
		for(int i = 0; i < orders.size(); i++)
			addresses[i] = orders.get(i).getAddress();
		
		return addresses;
	}
	
	public int getSize()
	{
		return orders.size();
	}
	
	

}
