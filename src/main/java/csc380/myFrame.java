package csc380;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

public class myFrame extends JFrame {
	
	InformationCollector data;
	JTextPane text, textBox, orderTextFeed, loadTextFeed;
	JButton confirm, deny;
	boolean bool = true;
	boolean buttonPressed = false;
	boolean loadOpened;
	JPanel leftOkCancel,menuOutput,menuHost,leftPanel,rightPanel, mainPanel;
	CardLayout left = new CardLayout();
	CardLayout right = new CardLayout();
	CardLayout main = new CardLayout();
	
	JPanel cards;

	
	public myFrame() 
	{
		super("Campus Delivery");
		cards = new JPanel(left);
		data = new InformationCollector();
		loadOpened = false;
		orderTextFeed = new JTextPane();
		loadTextFeed = new JTextPane();
		leftOkCancel = new JPanel(new BorderLayout());		//Borderlayout to put buttons below text
		leftPanel = new JPanel(left);	//Card layout to have multiple variations on the left side
		rightPanel = new JPanel(right);
		menuHost = new JPanel(new BorderLayout());
	    menuOutput = new JPanel();
		mainPanel = new JPanel(main);
	
		setUpFrame();

		cards.add(startUpPanel(), "Start up");
		cards.add(panelForLoad(),"Load details");
		cards.add(panelForOrder(), "Orders");
		add(cards);
			
//		
//		confirm = new JButton("Ok");
//		deny = new JButton("Cancel");
//
//		text = new JTextPane();
//		text.setEditable(false);
//		textBox = new JTextPane();
//		textBox.setEditable(true);
//		textFeed = new JTextPane();
//		textFeed.setEditable(false);
//		text.setText("Just setting up...");
//		
//		
//		confirm.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				bool = true;
//				buttonPressed = true;
//			}
//		});
//		deny.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				bool = false;
//				buttonPressed = true;
//			}
//		});
//		
//		leftOkCancel = new JPanel(new BorderLayout());		//Borderlayout to put buttons below text
//		leftOkCancel.setBounds(getX(),getY(),getWidth()/2,getHeight());
//		leftOkCancel.add(text, BorderLayout.NORTH);
//		leftPanel = new JPanel(left);	//Card layout to have multiple variations on the left side
//		JPanel buttonPanel = new JPanel();
//		buttonPanel.add(confirm);
//		buttonPanel.add(deny);
//		leftOkCancel.add(buttonPanel, BorderLayout.SOUTH);
//		leftPanel.add(leftOkCancel);
//		
//		rightPanel = new JPanel(right);
//		menuHost = new JPanel(new BorderLayout());
//		JPanel menu = new JPanel(new GridLayout(3,2));
////		menu.setSize(getWidth()/3, getHeight());
//	    JButton CheesePizza = new JButton("Cheese Pizza");
//	    CheesePizza.addActionListener(new ActionListener() { 
//	    	  public void actionPerformed(ActionEvent e){ 
//	    		    textFeed.setText(textFeed.getText()+"\nCheese Pizza");
//	    		  } 
//	    		} );
//	    JButton PepPizza = new JButton("Pepperoni Pizza");
//	    PepPizza.addActionListener(new ActionListener() { 
//	    	  public void actionPerformed(ActionEvent e) { 
//	    		    textFeed.setText(textFeed.getText()+"\nPepperoni Pizza");
//	    		  } 
//	    		} );
//	    JButton Steak = new JButton("Steak");
//	    Steak.addActionListener(new ActionListener() { 
//	    	  public void actionPerformed(ActionEvent e){ 
//	    		    textFeed.setText(textFeed.getText()+"\nSteak");
//	    		  } 
//	    		} );
//	    JButton Burger = new JButton("Burger");
//	    Burger.addActionListener(new ActionListener() { 
//	    	  public void actionPerformed(ActionEvent e) { 
//	    		    textFeed.setText(textFeed.getText()+"\nBurger");
//	    		  } 
//	    		} );
//	    JButton Fries = new JButton("Fries");
//	    Fries.addActionListener(new ActionListener() { 
//	    	  public void actionPerformed(ActionEvent e) { 
//	    		    textFeed.setText(textFeed.getText()+"\nFries");
//	    		  } 
//	    		} );
//	    JButton CBurger = new JButton("Cheese Burger");
//	    CBurger.addActionListener(new ActionListener() { 
//	    	  public void actionPerformed(ActionEvent e) { 
//	    		    textFeed.setText(textFeed.getText()+"\nCheese Burger");
//	    		  } 
//	    		} );
//	    
//	    JButton Checkout = new JButton("Checkout");
//	    Checkout.addActionListener(new ActionListener() { 
//	    	  public void actionPerformed(ActionEvent e) { 
//	    		    textFeed.setText(textFeed.getText()+"\nScrew you i dont work");
//	    		    ///////// NEED TO INSPECT textFeed here - create a order
//	    		    showMenuOut();
//	    		  } 
//	    		} );
//	    menu.add(CheesePizza);
//	    menu.add(PepPizza);
//	    menu.add(CBurger);
//	    menu.add(Burger);
//	    menu.add(Fries);
//	    menu.add(Steak);
//	    menu.add(Checkout);
//	    menuHost.add(menu, BorderLayout.CENTER);
//	    menuOutput = new JPanel();
//	    menuOutput.add(textFeed);
//	    menuHost.add(menuOutput,BorderLayout.EAST);
//	    rightPanel.add(menuHost,"menu");
//		mainPanel = new JPanel(main);
//		JPanel border = new JPanel(new BorderLayout());
//		border.add(leftPanel,BorderLayout.WEST);
//		border.add(rightPanel,BorderLayout.CENTER);
//		//mainPanel.add(statupPanel(), "start");
//		mainPanel.add(border,"menu");
//		mainPanel.add(new JPanel(),"blank");
//		add(mainPanel);
////		pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void setUpFrame()
	{
		setBounds(100, 100, 800, 400);
		setLayout(new BorderLayout());
		setBackground(new Color(0, 0, 0));
	}
	
	public JPanel startUpPanel()
	{
		JPanel panel = new JPanel();
		JButton createLoad = new JButton("Open Load");
		
		panel.setLayout(new BorderLayout());
		panel.add(createLoad, BorderLayout.WEST);
		
		createLoad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev)
			{
				getData().addLoad(new Load());				
				left.next(cards);
				loadTextFeed.setText("LOAD " + getData().getAllLoads().size() + " DETAILS \n");
				loadTextFeed.setFont(new Font("Calibri", Font.BOLD, 15));
			}
		});
		
		return panel;
	}
	
	private static JPanel openNewPanel()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		return panel;
	}
	
	public JPanel panelForLoad()
	{
		JPanel panel = openNewPanel();
		JButton beginOrder = new JButton("Begin order");
		JButton closeLoad = new JButton("Close load");
		
		loadTextFeed.setEditable(false);
		panel.setLayout(new BorderLayout());
		panel.add(beginOrder, BorderLayout.WEST);
		panel.add(closeLoad, BorderLayout.EAST);
		panel.add(loadTextFeed,BorderLayout.CENTER);
		
		closeLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				left.previous(cards);
			}
		});
		
		beginOrder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getData().getLoad(getData().getAllLoads().size() -1).addOrder(new Order("Hello"));
				if(data.getAllLoads().size() > 0)
				{
					int loadIndex = data.getAllLoads().size() - 1;
					int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;
					
					loadTextFeed.setText(loadTextFeed.getText() 
							+ "\tOrder # " + data.getLoad(data.getAllLoads().size() - 1).getOrders().size() + "\n\t\t");
				}
				orderTextFeed.setText("Order # " + data.getLoad(data.getAllLoads().size() - 1).getOrders().size() 
						+ "\t\t");
				orderTextFeed.setForeground(Color.RED);
				left.next(cards);
			}
		});
		
		return panel;
	}
	
	public JPanel panelForOrder()
	{
		JPanel panel = openNewPanel();
		
		orderTextFeed.setEditable(false);
		leftOkCancel.setBounds(getX(),getY(),getWidth()/2,getHeight());
		leftPanel.add(leftOkCancel);
		
		JPanel menu = new JPanel(new GridLayout(3,2));

	    JButton CheesePizza = new JButton("Cheese Pizza");
	    CheesePizza.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e)
	    	  { 
	    		  int loadIndex = data.getAllLoads().size() - 1;
	    		  int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;

	    		  data.getLoad(loadIndex).getOrder(orderIndex).addItem(new Item("Cheese Pizza"));
	    		  orderTextFeed.setText(orderTextFeed.getText()+"\nCheese Pizza");
	    	  } 
	     } );
	    
	    JButton PepPizza = new JButton("Pepperoni Pizza");
	    PepPizza.addActionListener(new ActionListener() { 
	    	
	    	public void actionPerformed(ActionEvent e) { 
	
	    		  int loadIndex = data.getAllLoads().size() - 1;
	    		  int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;

	    		  data.getLoad(loadIndex).getOrder(orderIndex).addItem(new Item("Pepperoni Pizza"));
	    		    orderTextFeed.setText(orderTextFeed.getText()+"\nPepperoni Pizza");
	    		  } 
	    		} );
	    
	    JButton Steak = new JButton("Steak");
	    Steak.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e){ 
	    		  int loadIndex = data.getAllLoads().size() - 1;
	    		  int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;
	    		  data.getLoad(loadIndex).getOrder(orderIndex).addItem(new Item("Steak"));
	    		    orderTextFeed.setText(orderTextFeed.getText()+"\nSteak");
	    		  } 
	    		} );
	    
	    JButton Burger = new JButton("Burger");
	    Burger.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		  int loadIndex = data.getAllLoads().size() - 1;
	    		  int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;
	    		  data.getLoad(loadIndex).getOrder(orderIndex).addItem(new Item("Burger"));
	    		    orderTextFeed.setText(orderTextFeed.getText()+"\nBurger");
	    		  } 
	    		} );
	    
	    JButton Fries = new JButton("Fries");
	    Fries.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		  int loadIndex = data.getAllLoads().size() - 1;
	    		  int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;
	    		  data.getLoad(loadIndex).getOrder(orderIndex).addItem(new Item("Fries"));
	    		  orderTextFeed.setText(orderTextFeed.getText()+"\nFries");
	    		  } 
	    		} );
	    
	    JButton CBurger = new JButton("Cheese Burger");
	    CBurger.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		  int loadIndex = data.getAllLoads().size() - 1;
	    		  int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;

	    		  data.getLoad(loadIndex).getOrder(orderIndex).addItem(new Item("Cheese Burger"));
	    		    orderTextFeed.setText(orderTextFeed.getText()+"\nCheese Burger");
	    		  } 
	    		} );
	    
	    JButton checkout = new JButton("Checkout");
	    checkout.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{

				if(data.getLoad(data.getAllLoads().size() - 1).getOrders().size() > -1)
				{
					for(int i = 0; i < data.getLoad(data.getAllLoads().size() - 1).getOrders().size() - 1; i++)
	    			{		
						loadTextFeed.setText(loadTextFeed.getText()
								+ data.getLoad(data.getAllLoads().size() - 1).getOrder(data.getLoad(data.getAllLoads().size() 
										- 1).getOrders().size() - 1).getItem(i).getFoodName() + "\n\t\t");
	    			}
				}
				
				loadTextFeed.setText(loadTextFeed.getText() + "\n");
	    		left.previous(cards);
	    	}
	    });
	    
	    JButton closeLoad = new JButton("Close load");
	    closeLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				left.first(cards);
			}
		});
	    
	    JButton empty = new JButton();
	    
	    menu.add(CheesePizza);
	    menu.add(PepPizza);
	    menu.add(CBurger);
	    menu.add(Burger);
	    menu.add(Fries);
	    menu.add(Steak);
	    menu.add(checkout);
	    menu.add(empty);
	    menu.add(closeLoad);

	    
	    menuHost.add(menu, BorderLayout.CENTER);
	    menuOutput.add(orderTextFeed);
	    menuHost.add(menuOutput,BorderLayout.EAST);
	    rightPanel.add(menuHost,"menu");
		JPanel border = new JPanel(new BorderLayout());
		border.add(leftPanel,BorderLayout.WEST);
		border.add(rightPanel,BorderLayout.CENTER);
		mainPanel.add(border,"menu");
		mainPanel.add(new JPanel(),"blank");
		
		return mainPanel;
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
	
	public InformationCollector getData() 
	{
		return data;
	}
	
	public ArrayList<Load> getLoad()
	{
		return data.getAllLoads();
	}
	
	public Load getLoad(int i)
	{
		return data.getLoad(i);
	}

}
