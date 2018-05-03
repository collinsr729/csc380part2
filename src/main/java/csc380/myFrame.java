package csc380;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
	JPanel leftOkCancel,menuOutput,menuHost,leftPanel,rightPanel, mainPanel;
	CardLayout left = new CardLayout();
	CardLayout right = new CardLayout();
	CardLayout main = new CardLayout();
	
	public myFrame() {
		super("Campus Delivery");
		
		setBounds(100, 100, 800, 400);
		setLayout(new BorderLayout());
		setBackground(new Color(0, 0, 0));
		confirm = new JButton("Ok");
		deny = new JButton("Cancel");

		text = new JTextPane();
		text.setEditable(false);
		textBox = new JTextPane();
		textBox.setEditable(true);
		textFeed = new JTextPane();
		textFeed.setEditable(false);
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
		leftPanel = new JPanel(left);	//Card layout to have multiple variations on the left side
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(confirm);
		buttonPanel.add(deny);
		leftOkCancel.add(buttonPanel, BorderLayout.SOUTH);
		leftPanel.add(leftOkCancel);
		
		rightPanel = new JPanel(right);
		menuHost = new JPanel(new BorderLayout());
		JPanel menu = new JPanel(new GridLayout(3,2));
//		menu.setSize(getWidth()/3, getHeight());
	    JButton CheesePizza = new JButton("Cheese Pizza");
	    CheesePizza.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e){ 
	    		    textFeed.setText(textFeed.getText()+"\nCheese Pizza");
	    		  } 
	    		} );
	    JButton PepPizza = new JButton("Pepperoni Pizza");
	    PepPizza.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    textFeed.setText(textFeed.getText()+"\nPepperoni Pizza");
	    		  } 
	    		} );
	    JButton Steak = new JButton("Steak");
	    Steak.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e){ 
	    		    textFeed.setText(textFeed.getText()+"\nSteak");
	    		  } 
	    		} );
	    JButton Burger = new JButton("Burger");
	    Burger.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    textFeed.setText(textFeed.getText()+"\nBurger");
	    		  } 
	    		} );
	    JButton Fries = new JButton("Fries");
	    Fries.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    textFeed.setText(textFeed.getText()+"\nFries");
	    		  } 
	    		} );
	    JButton CBurger = new JButton("Cheese Burger");
	    CBurger.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    textFeed.setText(textFeed.getText()+"\nCheese Burger");
	    		  } 
	    		} );
	    
	    JButton Checkout = new JButton("Checkout");
	    Checkout.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    textFeed.setText(textFeed.getText()+"\nScrew you i dont work");
	    		    ///////// NEED TO INSPECT textFeed here - create a order
	    		    showMenuOut();
	    		  } 
	    		} );
	    menu.add(CheesePizza);
	    menu.add(PepPizza);
	    menu.add(CBurger);
	    menu.add(Burger);
	    menu.add(Fries);
	    menu.add(Steak);
	    menu.add(Checkout);
	    menuHost.add(menu, BorderLayout.CENTER);
	    menuOutput = new JPanel();
	    menuOutput.add(textFeed);
	    menuHost.add(menuOutput,BorderLayout.EAST);
	    rightPanel.add(menuHost,"menu");
		mainPanel = new JPanel(main);
		JPanel border = new JPanel(new BorderLayout());
		border.add(leftPanel,BorderLayout.WEST);
		border.add(rightPanel,BorderLayout.CENTER);
		mainPanel.add(statupPanel(), "start");
		mainPanel.add(border,"menu");
		mainPanel.add(new JPanel(),"blank");
		add(mainPanel);
//		pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setText(String s) {
		text.setText(s);
	}

	public void showMenu() {
		main.show(mainPanel, "menu");
	}
	public void showStart() {
		main.show(mainPanel, "start");
	}
	public void showMenuOut() {
		main.show(mainPanel, "blank");
	}
}
