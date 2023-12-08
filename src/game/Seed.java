package game;

public class Seed{
	private int id;
	private String name;
	private int buyPrice;
	//watering status
	private boolean isWatered;
	//grown status, if it's true, ready to harvest
    private boolean isGrown;
    //seed grow time
    private int growTime;
    //amount of money to get after harvesting
    private int harvestProfit;

    public Seed(int id, String name, int buyPrice, int growTime, int harvestProfit) {
    	this.id = id;
    	this.name = name;
    	this.buyPrice = buyPrice;
        this.growTime = growTime;
        this.harvestProfit = harvestProfit;
        this.isWatered = false;
        this.isGrown = false;
    }
    public int getId() {
    	return id;
    }
    public String getName() {
    	return name;
    }
    public int getGrowTime() {
    	return growTime;
    }
    public int getBuyPrice() {
    	return buyPrice;
    }
    //return the grown status
    public boolean isGrown() {
        return isGrown;
    }
    //return the profit
    public int getHarvestProfit() {
        return harvestProfit;
    }
    //reduces the growTime and change the grown status
    public void water() {
        isWatered = true;
        growTime--;
        if (growTime == 0) {
            isGrown = true;
        }
    }
    public Seed clone() {
    	return new Seed(id, name, buyPrice, growTime, harvestProfit);
    }
}
