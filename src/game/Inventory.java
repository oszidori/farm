package game;

import java.util.ArrayList;
import java.util.List;

//player's inventory, contains the seeds
public class Inventory {
	private final static int maxSize = 30;
	private List<Seed> items;
	
	public Inventory() {
        this.items = new ArrayList<>();
    }
    //return the items
    public List<Seed> getItems() {
        return items;
    }
    //return item with the given id 
    public Seed getOneItem(int id) {
    	for(int i = 0; i < items.size(); i++) {
    		if(id == items.get(i).getId()) {
    			return items.get(i);
    		}
    	}
    	return null;
    }
    //check if it's full already
    public boolean isFull() {
    	if (items.size() >= maxSize)
    		return true;
    	return false;
    }
	//add new item if it isn't full
    public boolean addItem(Seed item) {
        if (!isFull()) {
        	boolean found = false;
        	for(int i = 0; i < items.size(); i++) {
        		 if(item.getId() == items.get(i).getId()) {
        			 found = true;
        			 return false;
        		 }
        	}
        	if(!found) {
	        	items.add(item);
	        	return true;
        	}
        }
        //inventory is full
        return false;
    }
    //remove item from here
    public boolean removeItem(int id) {
    	for(int i = 0; i < items.size(); i++) {
    		if(id == items.get(i).getId()) {
    			items.remove(items.get(i));
    			return true;
    		}
    	}
    	return false;
    }

}

