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

public class myFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private InformationCollector data;
	private JTextPane text, textBox, orderTextFeed, loadTextFeed;
	private	JButton steak, cheeseburger, burger, pepPizza, cheesePizza, fries, checkout, closeLoad, empty;
	private JPanel leftOkCancel, menuOutput, menuHost, leftPanel, rightPanel;
	private JPanel mainPanel;
	private CardLayout mainCardLayout = new CardLayout();
	private CardLayout orderCardLayout = new CardLayout();
	private CardLayout main = new CardLayout();
	private JPanel cards, orderPanel;
	private String name, address, phone;
	
	public myFrame() 
	{
		super("Campus Delivery");
		cards = new JPanel(mainCardLayout);
		data = new InformationCollector();
		orderTextFeed = new JTextPane();
		loadTextFeed = new JTextPane();
		leftOkCancel = new JPanel(new BorderLayout());		//Borderlayout to put buttons below text
		leftPanel = new JPanel(mainCardLayout);				//Card layout to have multiple variations on the left side
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
				try {
					data.getRoute(data.getLoad(currentLoadIndex()).getAddresses());	//data.getAllLoads().size() - 1
					mainCardLayout.previous(cards);
				} catch (AddressException e1) {
					e1.printStackTrace();
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
				mainCardLayout.first(cards);
			}
		});
		
		beginOrder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				data.getLoad(currentLoadIndex()).addOrder(new Order(""));	//getData().getAllLoads().size() -1
				if(data.getAllLoads().size() > 0)
				{
					loadTextFeed.setText(loadTextFeed.getText() 
							+ "\tOrder # " + (currentOrderIndex() + 1) + "\n\t\t");
				}
				orderTextFeed.setText("Order # " + (currentOrderIndex() + 1) + "\t\t");
				orderTextFeed.setForeground(Color.BLACK);
				toggleEnable();
				mainCardLayout.next(cards);
				orderPanel.add(buildInfoSubmissionPanel(), "submission");
				orderCardLayout.show(orderPanel, "submission");
				
			}
		});
		
		return panel;
	}
	
	public JPanel panelForOrder()
	{
		
		orderTextFeed.setEditable(false);
		leftOkCancel.setBounds(getX(),getY(),getWidth()/2,getHeight());
		leftPanel.add(leftOkCancel);
		
		JPanel menu = new JPanel(new GridLayout(3,2));

	    cheesePizza = new JButton("Cheese Pizza");
	    cheesePizza.addActionListener(new ActionListener()
	    { 
	    	  public void actionPerformed(ActionEvent e)
	    	  { 
	    		  data.getLoad(currentLoadIndex()).getOrder(currentOrderIndex()).addItem(new Item("Cheese Pizza"));
	    		  orderTextFeed.setText(orderTextFeed.getText()+"\n    Cheese Pizza");
	    	  } 
	     } );
	    
	    pepPizza = new JButton("Pepperoni Pizza");
	    pepPizza.addActionListener(new ActionListener() 
	    { 
	    	public void actionPerformed(ActionEvent e) 
	    	{ 	
	    		  data.getLoad(currentLoadIndex()).getOrder(currentOrderIndex()).addItem(new Item("Pepperoni Pizza"));
	    		  orderTextFeed.setText(orderTextFeed.getText()+"\n    Pepperoni Pizza");
	    	} 
	    } );
	    
	    steak = new JButton("Steak");
	    steak.addActionListener(new ActionListener() 
	    { 
	    	  public void actionPerformed(ActionEvent e)
	    	  { 
	    		  data.getLoad(currentLoadIndex()).getOrder(currentOrderIndex()).addItem(new Item("Steak"));
	    		  orderTextFeed.setText(orderTextFeed.getText()+"\n    Steak");
	    	  } 
	    } );
	    
	    burger = new JButton("Burger");
	    burger.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) 
	    	  { 
	    		  data.getLoad(currentLoadIndex()).getOrder(currentOrderIndex()).addItem(new Item("Burger"));
	    		  orderTextFeed.setText(orderTextFeed.getText()+"\n    Burger");
	    	  } 
	    } );
	    
	    fries = new JButton("Fries");
	    fries.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) 
	    	  { 
	    		  data.getLoad(currentLoadIndex()).getOrder(currentOrderIndex()).addItem(new Item("Fries"));
	    		  orderTextFeed.setText(orderTextFeed.getText()+"\n    Fries");
	    	  }
	    } );
	    
	    cheeseburger = new JButton("Cheese Burger");
	    cheeseburger.addActionListener(new ActionListener() 
	    { 
	    	  public void actionPerformed(ActionEvent e) 
	    	  { 
	    		  data.getLoad(currentLoadIndex()).getOrder(currentOrderIndex()).addItem(new Item("Cheese Burger"));
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
	    		mainCardLayout.show(cards, "Load details");
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
