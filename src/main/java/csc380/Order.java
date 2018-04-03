package csc380;

import java.util.ArrayList;
import java.util.Collections;

public class Order {
	
	private ArrayList<Item> items;
	private String name, address, telephoneNumber;
	

	public Order()
	{
		items = new ArrayList<Item>();
	}
	
	public void addItem(Item newItem)
	{
		items.add(newItem);
	}
	
	public Item getItem2(int index)
	{
		return items.get(index);
	}
	
	public int getTotalPrepTime()
	{
		int greatest;
		ArrayList<Integer> prepTimes = new ArrayList<Integer>();
		
		for(int i = 1; i < items.size(); i++)
		{
			prepTimes.add(i, items.get(i).getPrepTime());
			System.out.println("Success");
		}
		
		return Collections.max(prepTimes);
	}
	
	public void setName(String customerName)
	{
		name = customerName;
	}
	
	public String getName()
	{
		return name;
	}

	public void setAddress(String destAddress)
	{
		address = destAddress;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getTelephoneNumber()
	{
		return telephoneNumber;
	}
}
