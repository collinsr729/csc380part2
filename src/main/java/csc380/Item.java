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
		if(foodName.toLowerCase() == "steak")
			prepTime = 20;
		
		else if(foodName.toLowerCase() == "burger")
			prepTime = 15;
		
		else if(foodName.toLowerCase() == "fries")
			prepTime = 5;
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
