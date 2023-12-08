package frame;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import game.Farm;
import game.Player;
import game.Seed;
import game.Soil;

public class NewGameFrameSaver {
	public static void saveState(NewGameFrame frame, String fileName) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("PlayerName: " + frame.farm.getPlayer().getName() + "\n");
            writer.write("PlayerMoney: " + frame.farm.getPlayer().getMoney() + "\n");
            for (Seed seed : frame.farm.getPlayer().getInventoryItems()) {
                // Serialize the Seed properties to a string representation
                String serializedSeed = seed.getId() + "," + seed.getName() + "," +
                        seed.getBuyPrice() + "," + seed.getGrowTime() + "," + seed.getHarvestProfit();
                writer.write("InventoryItems: " + serializedSeed + "\n");
            }

            for(Soil soil: frame.farm.getPlayer().getSoils()) {
            	String serializedSoil = soil.getId() + "," + soil.getName() + "," + soil.getBuyPrice();
            	writer.write("Soils: " + serializedSoil + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
