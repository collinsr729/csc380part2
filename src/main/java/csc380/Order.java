package csc380;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JWindow;

public class Order {
	
	private ArrayList<Item> items;
	private String name, address, telephoneNumber;
	
	public void fillOrder(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new GridLayout(3, 2));
	    JButton CheesePizza = new JButton("Cheese Pizza");
	    CheesePizza.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e){ 
	    		    addItem(new Item("cheesepizza"));
	    		  } 
	    		} );
	    JButton PepPizza = new JButton("Peperoni Pizza");
	    CheesePizza.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    addItem(new Item("peppizza"));
	    		  } 
	    		} );
	    frame.add(CheesePizza);
	    frame.add(PepPizza);
		frame.setVisible(true);
		frame.setLocation(100, 100);
		frame.setBounds(100, 100, 500, 500);
	}

	public Order()
	{
		items = new ArrayList<Item>();
	}
	public Order(String add) {
		items = new ArrayList<Item>();
		setAddress(add);
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
}
