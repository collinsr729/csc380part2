package csc380;

import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class InformationCollector {
	
	ArrayList<Load> allLoads;
	
	public InformationCollector() {
		
		allLoads = new ArrayList<Load>();
		
	}
	
	public void getRoute(ArrayList<String> sortedAddresses) throws AddressException, MessagingException
	{
		Email email = new Email();
		email.sendEmail(sortedAddresses, allLoads.get(allLoads.size() - 1), allLoads.size());
	}
	
	public void addLoad (Load newLoad){
		
		allLoads.add(newLoad);
		System.out.println("New load opened: " + allLoads.size());
	}
	
	public void addOrder(Order newOrder){
		
		allLoads.get(allLoads.size()).addOrder(newOrder);
		System.out.println("New order started: " + getLoad(allLoads.size()).getOrders().size());
	}
	
	public void addItem(String newItem){
		
		allLoads.get(0).getOrder(0).addItem(new Item(newItem));
		System.out.println(allLoads.get(0).getOrder(0).getItem(0).getFoodName());
	}
	
	public ArrayList<Load> getAllLoads(){
		return allLoads;
	}
	
	public Load getLoad(int i)
	{
		return allLoads.get(i);
	}
	
	public int getOrderSize()
	{
		return getLoad(allLoads.size()).getSize();
	}

}
