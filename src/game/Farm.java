package game;

import java.util.ArrayList;
import java.util.List;

import frame.*;

//the game
public class Farm {
	private NewGameFrame frame;
	private Player player;
	private Shop shop;
	private List<Soil> allSoils;
	
	//initialize the new game
	public Farm(String playerName) {
		player = new Player(playerName);
		
		//loading game constructor
		if(playerName.equals("loadgame")) {
			shop = new Shop();
			allSoils = initSoils();
		}
		//whole new game constructor
		else {
			//because adding the initial items is free
			player.riseMoney(720);
			shop = new Shop();
			shop.buyItem(player, 100);
			shop.buyItem(player, 101);
			
			allSoils = initSoils();
			player.addSoil(allSoils.get(0));
			player.addSoil(allSoils.get(1));
			allSoils.get(0).init();
			allSoils.get(1).init();
		}
	}
	//get the private attributes of farm
	public Player getPlayer() {
		return player;
	}
	public Shop getShop() {
		return shop;
	}
	public List<Soil> getAllSoils(){
		return allSoils;
	}
	public Soil getOneSoil(int idx) {
		return allSoils.get(idx);
	}
	
	//initialize the soils
	public List<Soil> initSoils(){
		List<Soil> newSoils = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            newSoils.add(new Soil(frame, player, this, i, "BasicSoil", 200));
        }
        return newSoils;
	}
}
