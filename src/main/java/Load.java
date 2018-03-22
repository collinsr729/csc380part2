

public class Load {
	
	Order order1, order2, order3;
	String addresses[];
	Order orders[];
	
	public Load(Order order1)
	{
		this.order1 = order1;
		orders = new Order[1];
		orders[0] = order1;
		addresses = new String[1];
	}
	
	public Load(Order order1, Order order2)
	{
		this.order1 = order1;
		this.order2 = order2;
		orders = new Order[2];
		orders[0] = order1;
		orders[1] = order2;
		addresses = new String[2];
	}
	
	public Load(Order order1, Order order2, Order order3)
	{
		this.order1 = order1;
		this.order2 = order2;
		this.order3 = order3;
		orders = new Order[3];
		orders[0] = order1;
		orders[1] = order2;
		orders[2] = order3;
		addresses = new String[3];
	}
	
	public String[] getAddresses()
	{
		for(int i = 0; i < addresses.length; i++)
			addresses[i] = orders[i].getAddress();
		
		return addresses;
	}
	
	

}
