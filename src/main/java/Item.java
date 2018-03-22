

public class Item {
	
	String foodName;
	int prepTime;
	
	public Item(String foodName, int prepTime)
	{
		this.foodName = foodName;
		this.prepTime = prepTime;
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
