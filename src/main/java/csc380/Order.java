package csc380;

import java.util.ArrayList;
import java.util.Collections;

public class Order {
	
	private ArrayList<Item> items2;
	private String name, address, telephoneNumber;
	

	public Order()
	{
		items2 = new ArrayList<Item>();
	}
	
	public void addItem(Item newItem)
	{
		items2.add(newItem);
	}
	
	public Item getItem2(int index)
	{
		return items2.get(index);
	}
	
	public int getTotalPrepTime()
	{
		int greatest;
		ArrayList<Integer> prepTimes = new ArrayList<Integer>();
		
		for(int i = 1; i < items2.size(); i++)
		{
			prepTimes.add(i, items2.get(i).getPrepTime());
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
