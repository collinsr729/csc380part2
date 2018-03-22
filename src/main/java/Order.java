

public class Order {
	
	private Item [] items;
	private String name, address, telephoneNumber;
	

	public Order(Item itemA, Item itemB, Item itemC)
	{
		items = new Item[3];
		items[0] = itemA;
		items[1] = itemB;
		items[2] = itemC;

	}
	public Order(Item itemA, Item itemB)
	{
		items = new Item[2];
		items[0] = itemA;
		items[1] = itemB;
	}
	public Order(Item itemA)
	{
		items = new Item[1];
		items[0] = itemA;
	}
	
	public Item getItem(int whichItem)
	{
		Item returningItem;
		
		returningItem = items[whichItem];
		
		return returningItem;
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
