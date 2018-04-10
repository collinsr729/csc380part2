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
		if(foodName.toLowerCase().compareTo("steak") == 0)
			prepTime = 20;
		
		else if(foodName.toLowerCase().compareTo("burger") == 0)
			prepTime = 15;
		
		else if(foodName.toLowerCase().compareTo("fries") == 0)
			prepTime = 5;
		else if(foodName.equalsIgnoreCase("peppizza")) {
			prepTime = 12;
		}
		else if(foodName.equalsIgnoreCase("cheesepizza")) {
			prepTime = 10;
		}
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
