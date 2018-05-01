package csc380;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JWindow;

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
		fillOrder();
	}
	
	
	public void fillOrder(){
		JFrame.setDefaultLookAndFeelDecorated(false);;
		JFrame frame = new JFrame("Menu");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setLayout(new GridLayout(3, 2));
	    JButton CheesePizza = new JButton("Cheese Pizza");
	    CheesePizza.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e){ 
	    		    addItem(new Item("cheesepizza"));
	    		  } 
	    		} );
	    JButton PepPizza = new JButton("Pepperoni Pizza");
	    PepPizza.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    addItem(new Item("peppizza"));
	    		  } 
	    		} );
	    JButton Steak = new JButton("Steak");
	    Steak.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e){ 
	    		    addItem(new Item("steak"));
	    		  } 
	    		} );
	    JButton Burger = new JButton("Burger");
	    Burger.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    addItem(new Item("burger"));
	    		  } 
	    		} );
	    JButton Fries = new JButton("Fries");
	    Fries.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    addItem(new Item("fries"));
	    		  } 
	    		} );
	    JButton CBurger = new JButton("Cheese Burger");
	    CBurger.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    addItem(new Item("cheeseburger"));
	    		  } 
	    		} );
	    
	    JButton Checkout = new JButton("Checkout");
	    Checkout.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    checkout();
	    		  } 
	    		} );
	    frame.add(CheesePizza);
	    frame.add(PepPizza);
	    frame.add(CBurger);
	    frame.add(Burger);
	    frame.add(Fries);
	    frame.add(Steak);
	    frame.add(Checkout);
		frame.setVisible(true);
		frame.setLocation(100, 100);
		frame.setBounds(100, 100, 500, 500);
		frame.requestFocus();
		while(!getCheckout()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		frame.dispose();
	}
	
	boolean check = false;
	private boolean getCheckout() {
		return check;
	}
	
	private void checkout() {
		check  = true;
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
		int greatest;
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
	
	public String getTelephoneNumber()
	{
		return telephoneNumber;
	}
	
	public int getTimeOfOrder() 
	{
		return timeOfOrder;
	}
	
	public String toString() {
		return getAddress() + ":\n" + items.toString();
	}
	
	public int getNumberOfItems()
	{
		return items.size();
	}
	
}
