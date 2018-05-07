package csc380;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

public class myFrame extends JFrame {
	
	InformationCollector data;
	JTextPane text, textBox, orderTextFeed, loadTextFeed;
	JButton confirm, deny;
	JButton steak, cheeseburger, burger, pepPizza, cheesePizza, fries, checkout, closeLoad, empty;
	boolean bool = true;
	boolean buttonPressed = false;
	boolean loadOpened;
	boolean loadComplete;
	JPanel leftOkCancel, menuOutput, menuHost, leftPanel, rightPanel;
	JPanel mainPanel;
	CardLayout mainCardLayout = new CardLayout();
	CardLayout orderCardLayout = new CardLayout();
	CardLayout main = new CardLayout();
	JPanel cards;
	JPanel orderPanel;
	String name, address, phone;
	
	public myFrame() 
	{
		super("Campus Delivery");
		cards = new JPanel(mainCardLayout);
		data = new InformationCollector();
		loadOpened = false;
		orderTextFeed = new JTextPane();
		loadTextFeed = new JTextPane();
		leftOkCancel = new JPanel(new BorderLayout());		//Borderlayout to put buttons below text
		leftPanel = new JPanel(mainCardLayout);	//Card layout to have multiple variations on the left side
		rightPanel = new JPanel(orderCardLayout);
		orderPanel = new JPanel(orderCardLayout);
		menuHost = new JPanel(new BorderLayout());
	    menuOutput = new JPanel();
		mainPanel = new JPanel(main);
	}
	
	public void openFrame()
	{
		setUpFrame();

		cards.add(startUpPanel(), "Start up");
		cards.add(panelForLoad(),"Load details");
		cards.add(panelForOrder(), "Orders");
		add(cards);
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
				mainCardLayout.next(cards);
				loadTextFeed.setText("LOAD " + (currentLoadIndex() + 1) + " DETAILS \n"); //getData().getAllLoads().size()
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
		
		closeLoad.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Map map = new Map();
				try {
					data.getRoute(map.calculateRoute(data.getLoad(currentLoadIndex()).getAddresses()));	//data.getAllLoads().size() - 1
				} catch (AddressException e1) {
					e1.printStackTrace();
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
				setLoadComplete(true);
				mainCardLayout.previous(cards);
			}
		});
		
		beginOrder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				data.getLoad(currentLoadIndex()).addOrder(new Order("233 Slawson Drive"));	//getData().getAllLoads().size() -1
				if(data.getAllLoads().size() > 0)
				{
					//int loadIndex = data.getAllLoads().size() - 1;
					//int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;
					
					loadTextFeed.setText(loadTextFeed.getText() 
							+ "\tOrder # " + (currentOrderIndex() + 1) + "\n\t\t");
				}
				orderTextFeed.setText("Order # " + (currentOrderIndex() + 1) + "\t\t");
				orderTextFeed.setForeground(Color.BLACK);
				mainCardLayout.next(cards);
				orderPanel.add(buildInfoSubmissionPanel(), "submission");
				orderCardLayout.show(orderPanel, "submission");
				
				
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

	    cheesePizza = new JButton("Cheese Pizza");
	    cheesePizza.addActionListener(new ActionListener()
	    { 
	    	  public void actionPerformed(ActionEvent e)
	    	  { 
	    		  int loadIndex = data.getAllLoads().size() - 1;
	    		  int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;

	    		  data.getLoad(loadIndex).getOrder(orderIndex).addItem(new Item("Cheese Pizza"));
	    		  orderTextFeed.setText(orderTextFeed.getText()+"\n    Cheese Pizza");
	    	  } 
	     } );
	    
	    pepPizza = new JButton("Pepperoni Pizza");
	    pepPizza.addActionListener(new ActionListener() 
	    { 
	    	public void actionPerformed(ActionEvent e) 
	    	{ 	
	    		  int loadIndex = data.getAllLoads().size() - 1;
	    		  int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;

	    		  data.getLoad(loadIndex).getOrder(orderIndex).addItem(new Item("Pepperoni Pizza"));
	    		    orderTextFeed.setText(orderTextFeed.getText()+"\n    Pepperoni Pizza");
	    		  } 
	    		} );
	    
	    steak = new JButton("Steak");
	    steak.addActionListener(new ActionListener() 
	    { 
	    	  public void actionPerformed(ActionEvent e)
	    	  { 
	    		  int loadIndex = data.getAllLoads().size() - 1;
	    		  int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;
	    		  data.getLoad(loadIndex).getOrder(orderIndex).addItem(new Item("Steak"));
	    		    orderTextFeed.setText(orderTextFeed.getText()+"\n    Steak");
	    	  } 
	    } );
	    
	    burger = new JButton("Burger");
	    burger.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		  int loadIndex = data.getAllLoads().size() - 1;
	    		  int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;
	    		  data.getLoad(loadIndex).getOrder(orderIndex).addItem(new Item("Burger"));
	    		    orderTextFeed.setText(orderTextFeed.getText()+"\n    Burger");
	    		  } 
	    		} );
	    
	    fries = new JButton("Fries");
	    fries.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		  int loadIndex = data.getAllLoads().size() - 1;
	    		  int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;
	    		  data.getLoad(loadIndex).getOrder(orderIndex).addItem(new Item("Fries"));
	    		  orderTextFeed.setText(orderTextFeed.getText()+"\n    Fries");
	    		  } 
	    		} );
	    
	    cheeseburger = new JButton("Cheese Burger");
	    cheeseburger.addActionListener(new ActionListener() 
	    { 
	    	  public void actionPerformed(ActionEvent e) 
	    	  { 
	    		  int loadIndex = data.getAllLoads().size() - 1;
	    		  int orderIndex = data.getLoad(loadIndex).getOrders().size() - 1;

	    		  data.getLoad(loadIndex).getOrder(orderIndex).addItem(new Item("Cheese Burger"));
	    		    orderTextFeed.setText(orderTextFeed.getText()+"\n    Cheese Burger");
	    		} 
	    } );
	    
	    checkout = new JButton("Checkout");
	    checkout.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{

				if(currentOrderIndex() > -1)
				{
					for(int i = 0; i < data.getLoad(currentLoadIndex()).getOrder(currentOrderIndex()).getNumberOfItems(); i++)
	    			{		
						loadTextFeed.setText(loadTextFeed.getText() + "- " 
								+ data.getLoad(currentLoadIndex()).getOrder(currentOrderIndex())
								.getItem(i).getFoodName() + "\n\t\t");
	    			}
				}
				
				loadTextFeed.setText(loadTextFeed.getText() + "\n");
	    		mainCardLayout.previous(cards);
	    		toggleEnable();
	    	}
	    });
	    
	    closeLoad = new JButton("Close load");
	    closeLoad.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent e) 
			{
				
				Map map = new Map();
				try 
				{
					data.getRoute(map.calculateRoute(data.getLoad(currentLoadIndex()).getAddresses()));
				} catch (AddressException e1) {
					e1.printStackTrace();
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
				mainCardLayout.first(cards);
			}
		});
	    
	    empty = new JButton();
	    toggleEnable();
	    
	    menu.add(cheesePizza);
	    menu.add(pepPizza);
	    menu.add(cheeseburger);
	    menu.add(burger);
	    menu.add(fries);
	    menu.add(steak);
	    menu.add(checkout);
	    menu.add(empty);
	    menu.add(closeLoad);
	    
	    menuOutput.add(orderTextFeed);
	    
	    orderPanel.add(menuOutput,"order details");
	    rightPanel.add(buildMenuHostPanel(orderPanel, menu),"menu");
		JPanel border = new JPanel(new BorderLayout());
		border.add(leftPanel,BorderLayout.WEST);
		border.add(rightPanel,BorderLayout.CENTER);
		mainPanel.add(border,"menu");
		mainPanel.add(new JPanel(),"blank");
		
		return mainPanel;
	}
	
	private JPanel buildMenuHostPanel(JPanel orderPanel, JPanel menu)
	{
		menuHost.add(menu, BorderLayout.CENTER);
	    menuHost.add(orderPanel,BorderLayout.EAST);
	    
	    return menuHost;
	}
	
	private JPanel buildInfoSubmissionPanel()
	{
		JPanel infoSubmissionPanel = new JPanel();
	    final JTextField nameField = new JTextField(10);
	    final JTextField addressField = new JTextField(10);
	    final JTextField phoneField = new JTextField(10);
	    JLabel nameLabel = new JLabel("Name: ");
	    JLabel addressLabel = new JLabel("Address: ");
	    JLabel phoneLabel = new JLabel("Phone: ");
	    JButton submit = new JButton("Submit");
	    
	    nameLabel.setLabelFor(nameField);
	    addressLabel.setLabelFor(addressField);
	    phoneLabel.setLabelFor(phoneField);
	    
	    infoSubmissionPanel.setLayout(new GridLayout(4, 2, 5, 85));
	    infoSubmissionPanel.add(nameLabel);
	    infoSubmissionPanel.add(nameField);
	    infoSubmissionPanel.add(addressLabel);
	    infoSubmissionPanel.add(addressField);
	    infoSubmissionPanel.add(phoneLabel);
	    infoSubmissionPanel.add(phoneField);
	    infoSubmissionPanel.add(submit);
	    	    
	    submit.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		orderCardLayout.show(orderPanel, "order details");
	    		name = nameField.getText();
	    		
	    		data.getLoad(data.getAllLoads().size() 
	    				-1).getOrder(data.getLoad(currentLoadIndex()).getOrders().size()
	    						-1).setName(name);
	    		
	    		address = addressField.getText();
	    		
	    		data.getLoad(data.getAllLoads().size() 
	    				-1).getOrder(data.getLoad(currentLoadIndex()).getOrders().size()
	    						-1).setAddress(address);
	    		
	    		phone = phoneField.getText();
	    		data.getLoad(data.getAllLoads().size() 
	    				-1).getOrder(data.getLoad(currentLoadIndex()).getOrders().size()
	    						-1).setTelephoneNumber(phone);	    		
	    		toggleEnable();
	    	}
	    });
	    
	  return infoSubmissionPanel;
		
	}
	
	private void toggleEnable() 
	{
		burger.setEnabled(!burger.isEnabled());
		cheeseburger.setEnabled(!cheeseburger.isEnabled());
		fries.setEnabled(!fries.isEnabled());
		checkout.setEnabled(!checkout.isEnabled());
		closeLoad.setEnabled(!closeLoad.isEnabled());
		steak.setEnabled(!steak.isEnabled());
		pepPizza.setEnabled(!pepPizza.isEnabled());
		cheesePizza.setEnabled(!cheesePizza.isEnabled());
		empty.setEnabled(!empty.isEnabled());
	}
	
	private void setLoadComplete(boolean e)
	{
		loadComplete = e;
	}
	
	public void setText(String s) {
		text.setText(s);
	}
	
	public Load getCurrentLoad()
	{
		return data.getLoad(data.getAllLoads().size() - 1);
	}
	
	public int currentLoadIndex()
	{
		return data.getAllLoads().size() - 1;
	}
	
	public int currentOrderIndex()
	{
		return data.getLoad(currentLoadIndex()).getOrders().size() - 1;
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
	
	private void extraCode()
	{
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
	}

}
