package csc380;

import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class InformationCollector {
	
	ArrayList<Load> allLoads;
	
	public InformationCollector() {
		
		allLoads = new ArrayList<Load>();
		
	}
	
	public void getRoute(ArrayList<String> unsortedAddresses) throws AddressException, MessagingException
	{
		
		Email email = new Email();
		Map m = new Map();
		ArrayList<String> adds = m.calculateRoute(unsortedAddresses);
		email.sendEmail(adds, allLoads.get(allLoads.size() - 1), allLoads.size());
	}
	
	public void addLoad (Load newLoad){
		
		allLoads.add(newLoad);
	}
	
	public void addOrder(Order newOrder){
		
		allLoads.get(allLoads.size()).addOrder(newOrder);
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
		return getLoad(allLoads.size()-1).getSize();
	}

}
