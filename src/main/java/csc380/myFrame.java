package csc380;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

public class myFrame extends JFrame {
	JTextPane text, textBox,textFeed;
	JButton confirm, deny;
	boolean bool = true;
	boolean buttonPressed = false;
	JPanel leftOkCancel;
	public myFrame() {
		super("Campus Delivery");

		setBounds(100, 100, 800, 400);
		confirm = new JButton("Ok");
		deny = new JButton("Cancel");

		text = new JTextPane();
		text.setEditable(false);
		textBox = new JTextPane();
		textBox.setEditable(true);
		textFeed = new JTextPane() {
			public void addItem(String s) {
				this.setText(this.getText()+"\n"+s);
			}
		};
		text.setEditable(false);
		text.setText("Just setting up...");
		
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bool = true;
				buttonPressed = true;
			}
		});
		deny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bool = false;
				buttonPressed = true;
			}
		});
		
		leftOkCancel = new JPanel(new BorderLayout());		//Borderlayout to put buttons below text
		leftOkCancel.setBounds(getX(),getY(),getWidth()/2,getHeight());
		leftOkCancel.add(text, BorderLayout.NORTH);
		JPanel leftPanel = new JPanel(new CardLayout());	//Card layout to have multiple variations on the left side
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(confirm);
		buttonPanel.add(deny);
		leftOkCancel.add(buttonPanel, BorderLayout.SOUTH);
		leftPanel.add(leftOkCancel);
		
		JPanel rightSide = new JPanel(new CardLayout());
		JPanel menu = new JPanel();
	    menu.setLayout(new GridLayout(3, 2));
	    JButton CheesePizza = new JButton("Cheese Pizza");
	    CheesePizza.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e){ 
	    		    textFeed.setText(textFeed.getText()+"\nCheese Pizza");
	    		  } 
	    		} );
	    JButton PepPizza = new JButton("Pepperoni Pizza");
	    PepPizza.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    textFeed.setText(textFeed.getText()+"\nCheese Pizza");
	    		  } 
	    		} );
	    JButton Steak = new JButton("Steak");
	    Steak.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e){ 
	    		    textFeed.setText(textFeed.getText()+"\nCheese Pizza");
	    		  } 
	    		} );
	    JButton Burger = new JButton("Burger");
	    Burger.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    textFeed.setText(textFeed.getText()+"\nCheese Pizza");
	    		  } 
	    		} );
	    JButton Fries = new JButton("Fries");
	    Fries.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    textFeed.setText(textFeed.getText()+"\nCheese Pizza");
	    		  } 
	    		} );
	    JButton CBurger = new JButton("Cheese Burger");
	    CBurger.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    textFeed.setText(textFeed.getText()+"\nCheese Pizza");
	    		  } 
	    		} );
	    
	    JButton Checkout = new JButton("Checkout");
	    Checkout.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    textFeed.setText(textFeed.getText()+"\nCheese Pizza");
	    		    ///////// NEED TO INSPECT textFeed here - create a menu
	    		  } 
	    		} );
	    menu.add(CheesePizza);
	    menu.add(PepPizza);
	    menu.add(CBurger);
	    menu.add(Burger);
	    menu.add(Fries);
	    menu.add(Steak);
	    menu.add(Checkout);
		menu.setVisible(true);
		
		
		
		
		
		
		
		
		
		add(leftPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void setText(String s) {
		text.setText(s);
	}
}
