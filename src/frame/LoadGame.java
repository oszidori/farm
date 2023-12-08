package frame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import game.Seed;
import game.Soil;

public class LoadGame {
	public static void loadState(NewGameFrame frame, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse each line and set the corresponding property of the NewGameFrame
                if (line.startsWith("PlayerName: ")) {
                    String playerName = line.substring("PlayerName: ".length());
                    // Set the player name property of the frame
                    frame.farm.getPlayer().setName(playerName);
                }
                if(line.startsWith("PlayerMoney: ")) {
                	String playerMoney = line.substring("PlayerMoney: ".length());
                	int money = Integer.parseInt(playerMoney);
                	frame.farm.getPlayer().setMoney(money);
                }
                if(line.startsWith("InventoryItems: ")) {
                	String[] item = line.substring("InventoryItems: ".length()).split(",");
                	int id = Integer.parseInt(item[0]);
                    String name = item[1];
                    int buyPrice = Integer.parseInt(item[2]);
                    int growTime = Integer.parseInt(item[3]);
                    int harvestProfit = Integer.parseInt(item[4]);

                    Seed seed = new Seed(id, name, buyPrice, growTime, harvestProfit);
                    frame.farm.getPlayer().getInventoryItems().add(seed);	
                }
                if(line.startsWith("Soils: ")) {
                	String[] soilItem = line.substring("Soils: ".length()).split(",");
                	int soilId = Integer.parseInt(soilItem[0]);
          	
                	for(int i = 0; i < frame.farm.getAllSoils().size(); i++) {
                		if(frame.farm.getAllSoils().get(i).getId() == soilId) {
                			frame.farm.getPlayer().getSoils().add(frame.farm.getAllSoils().get(i));
                		}
                	}
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
