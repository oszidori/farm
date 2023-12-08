package game;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
    private int money;
    private List<Soil> soils;
    private Inventory ownInventory = new Inventory();
    
    //create a player by adding a name
    public Player(String name) {
        this.name = name;
        this.money = 500;
        this.soils = new ArrayList<>();
    }
    //player name
    public String getName() {
        return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    //player money
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
    	this.money = money;
    }
    //own soils
    public List<Soil> getSoils() {
        return soils;
    }
    public void setSoils(List<Soil> s) {
    	soils = s;
    }
    //change the money
    public void deductMoney(int amount) {
        money -= amount;
    }
    public void riseMoney(int amount) {
    	money += amount;
    }
    //add new soils to the player
    public boolean addSoil(Soil soil) {
        int loss = soil.getBuyPrice();
        boolean success = false;
    	if((money - loss) < 0) {
    		return success;
    	}
    	else {
    		if(soil.isOpen()) {
    			return success;
    		}
    		else {
    			soil.setOpen(true);
    			soils.add(soil);
    			deductMoney(loss);
    			success = true;
    		}

    	}
    	return success;
    }
    public boolean sellSoil(Soil soil) {
    	int profit = soil.getBuyPrice();
    	boolean success = false;
    	boolean found =  false;
    	for(Soil current: soils) {
    		if(soil.getId() == current.getId()) {
    			found = true;
    		}
    	}
    	if(!found) {
    		return success;
    	}
    	else {
    		soil.setOpen(false);
    		soils.remove(soil);
    		riseMoney(profit);
    		success = true;
    	}
    	
    	return success;
    }
    //return the inventory items
    public List<Seed> getInventoryItems(){
    	return ownInventory.getItems();
    }
    //add a new item to inventory
    public boolean addToInventory(Seed item) {
    	boolean success = false;
    	int loss = item.getBuyPrice();
    	if((money - loss) < 0)
    		return success;
    	else {
	    	success = ownInventory.addItem(item);
	    	if(success) {
	    		deductMoney(loss);
	    	}
    	}	
    	return success;
    }
    //remove item from inventory
    public boolean removeFromInventory(int id) {
    	Seed current = ownInventory.getOneItem(id);
    	boolean success = false;
    	if(current != null)
    		success = ownInventory.removeItem(id);
    	
    	if(success) {
        	int profit = current.getBuyPrice();
        	riseMoney(profit);
    	}
    	return success;
    }
}
