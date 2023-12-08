package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {
    private Map<Integer, Seed> shopItems;

    public Shop() {
        this.shopItems = new HashMap<>();
        initializeShop("seeds.txt");
    }
    //items that appears on the shop always
    private void initializeShop(String fileName) {
        //Add seeds to the shop
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] seed = line.split(",");
                int id = Integer.parseInt(seed[0]);
                String name = seed[1];
                int buyPrice = Integer.parseInt(seed[2]);
                int growTime = Integer.parseInt(seed[3]);
                int harvestProfit = Integer.parseInt(seed[4]);

                if(name.equals("Wheat")){
                    shopItems.put(id, new Wheat(id, name, buyPrice, growTime, harvestProfit));
                }
                if(name.equals("Tomato")){
                    shopItems.put(id, new Tomato(id, name, buyPrice, growTime, harvestProfit));
                }
                if(name.equals("Carrot")){
                    shopItems.put(id, new Carrot(id, name, buyPrice, growTime, harvestProfit));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
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
