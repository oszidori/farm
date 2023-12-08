package game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {
    private Map<Integer, Seed> shopItems;

    public Shop() {
        this.shopItems = new HashMap<>();
        initializeShop();
    }
    //items that appears on the shop always
    private void initializeShop() {
        //Add seeds to the shop
    	for(int i = 0; i < 10; i++) {
	        shopItems.put(100+i, new Seed(100+i, "CarrotSeed", 80, 1, 100));
	        shopItems.put(200+i, new Seed(200+i, "TomatoSeed", 100, 1, 120));
	        shopItems.put(300+i, new Seed(300+i, "SunflowerSeed", 120, 1, 140));
    	}
    }
    public Map<Integer, Seed> getShopItems(){
    	return shopItems;
    }

    //buy new Item in the shop
    public boolean buyItem(Player player, int id) {
        Seed shopItem = shopItems.get(id);

        if (shopItem != null) {
        	//if the player has enough money to buy the product
            if (player.getMoney() >= shopItem.getBuyPrice()) {
            	//reduces the players money
                player.deductMoney(shopItem.getBuyPrice());
                //clone to the players inventory
                player.addToInventory(shopItem.clone());
                return true;
            } else {
            	//not enough money
                return false;
            }
        }
        //not found the item in the shop
        return false;
    }
    //sell the items from the inventory
    public void sellItem(Player player, Seed item) {
    	//find the item in the player inventory
        Seed inventoryItem = findItemInInventory(player, item);
        //remove it from the players inventory
        if (inventoryItem != null) 
            player.removeFromInventory(inventoryItem.getId());
	    }
    //find the item by it's id in player inventory
    public Seed findItemInInventory(Player player, Seed found) {
    	
        for (Seed item : player.getInventoryItems()) {
            if (item.getId() == found.getId()) {
                return item;
            }
        }
        //not found
        return null;
    }
}
