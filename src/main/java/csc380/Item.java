package csc380;


public class Item {
	
	String foodName;
	int prepTime;
	
	public Item(String foodName)
	{
		this.foodName = foodName;
		setPrepTime();
	}
	
	private void setPrepTime()
	{
		if(foodName.equalsIgnoreCase("steak"))
			prepTime = 20;
		else if(foodName.equalsIgnoreCase("burger"))
			prepTime = 15;
		else if(foodName.equalsIgnoreCase("fries"))
			prepTime = 5;
		else if(foodName.equalsIgnoreCase("peppizza"))
			prepTime = 12;
		else if(foodName.equalsIgnoreCase("cheesepizza"))
			prepTime = 10;
		else if(foodName.equalsIgnoreCase("cheeseburger")) 
			prepTime = 16;
		else
			prepTime = 25;
	}
	
	public String getFoodName()
	{
		return foodName;
	}
	
	public int getPrepTime()
	{
		return prepTime;
	}

}
