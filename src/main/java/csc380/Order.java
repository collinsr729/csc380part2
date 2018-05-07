package csc380;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class Order {
	
	private ArrayList<Item> items;
	private String name, address, telephoneNumber;
	private int timeOfOrder;
	private Calendar now;
	
	public Order(String add) 
	{
		items = new ArrayList<Item>();
		address = add;
		now = Calendar.getInstance();
		timeOfOrder = now.get(Calendar.MINUTE);
	}
	

	public void addItem(Item newItem)
	{
		items.add(newItem);
	}
	
	public Item getItem(int index)
	{
		return items.get(index);
	}
	
	public int getTotalPrepTime()
	{
		ArrayList<Integer> prepTimes = new ArrayList<Integer>();
		
		for(int i = 0; i < items.size(); i++)
		{
			prepTimes.add(items.get(i).getPrepTime());
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
	
	public void setTelephoneNumber(String number)
	{
		telephoneNumber = number;
	}
	
	public String getTelephoneNumber()
	{
		return telephoneNumber;
	}
	
	public int getTimeOfOrder() 
	{
		return timeOfOrder;
	}
	
	public String toString() 
	{
		return getAddress() + ":\n" + items.toString();
	}
	
	public int getNumberOfItems()
	{
		return items.size();
	}
	
	public ArrayList<Item> getItems()
	{
		return items;
	}
	
	
}
