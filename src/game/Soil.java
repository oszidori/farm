package game;
import frame.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Soil{
	//popup frame and it's containers
	private JDialog popupSoilActions;
	private JPanel soilActions;
	private JButton soilButton;
	private JButton buySoil;
	private JButton sellSoil;
	private JButton plantSeed;
	private JButton watering;
	private JButton harvestPlant;
	private JLabel text;
	private JLabel waterInfo;
	
	public JComboBox<Integer> chooseSeed;
	
	private static Farm farm;
	private static Player player;
	private int id;
	private String name;
	private int buyPrice;
	//is it already bought by the player
    private boolean isOpen;
    //current seed on the soil
    private Seed plantedSeed;

    public Soil(NewGameFrame frame, Player player, Farm farm, int id, String name, int buyPrice) {
		Soil.player = player;
		Soil.farm = farm;
		//create the components
    	popupSoilActions = new JDialog(frame, "Soil", true);
    	String strId = Integer.toString(id);
    	soilButton = new JButton(strId);
    	buySoil = new JButton("Buy");
    	sellSoil = new JButton("Sell");
    	plantSeed = new JButton("Plant");
    	watering = new JButton("Water");
    	harvestPlant = new JButton("Harvest");
    	chooseSeed = new JComboBox<>();
    	soilActions = new JPanel();
		text = new JLabel("Please click on the preferred interaction!");
		waterInfo = new JLabel();
		
		//set default background and initialize the jcombobox
		soilButton.setBackground(Color.gray);
		refreshTheContentOfSeeds();
		chooseSeed.repaint();
		
		//set the interact buttons clickable status
		buySoil.setEnabled(true);
		sellSoil.setEnabled(false);
		plantSeed.setEnabled(false);
		watering.setEnabled(false);
		harvestPlant.setEnabled(false);
		
		//add actions to the buttons
		actions();
		//initialize the attributes of the class
    	this.id = id;
    	this.name = name;
    	this.buyPrice = buyPrice;
        this.isOpen = false;
        this.plantedSeed = null;
    }
    //customize the frame and components and add actionListeners to the buttons
    public void actions() {
    	Customize();
		
    	//soil button opens the popup frame
		soilButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				refreshTheContentOfSeeds();
				chooseSeed.repaint();
				//open a popup frame where the user can choose the options
				popupSoilActions.setVisible(true);

			}
		});
		Soil soil = this;
		//buySoil button add a new soil to the player
	    buySoil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//try to add soil
				boolean success = player.addSoil(soil);
				//if it was successful
				if(success) {
					//change the available buttons
					buySoil.setEnabled(false);
					sellSoil.setEnabled(true);
					plantSeed.setEnabled(true);
					watering.setEnabled(false);
					harvestPlant.setEnabled(false);
					//set the background green, to show the user that it is already owned
					soilButton.setBackground(Color.green);
					//refresh the amount of money
					NewGameFrame.refreshTheMoney();
					//close the frame  
					popupSoilActions.dispose();
					//affirmative message to the user
					Message information = new Message("Item was bought successfully!");
					information.setVisible(true);
				}
				//if it wasn't successful
				else {
					//the buttons status stay
					buySoil.setEnabled(true);
					sellSoil.setEnabled(false);
					plantSeed.setEnabled(false);
					watering.setEnabled(false);
					harvestPlant.setEnabled(false);
					//close the frame
					popupSoilActions.dispose();
					//if something went wrong, send errorMessage to the user
					ErrorMessage error = new ErrorMessage("Failed to buy the selected item!");
					error.setVisible(true);	
				}
			}
		});
	    //sellSoil is removed the soil from the player
	    sellSoil.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae) {
	    		//try to sell the soil
	    		boolean success = player.sellSoil(soil);
	    		//if it was success
	    		if(success) {
	    			//change the status of the buttons
	    			buySoil.setEnabled(true);
	    			sellSoil.setEnabled(false);
					plantSeed.setEnabled(false);
					watering.setEnabled(false);
					harvestPlant.setEnabled(false);
					
					//set the main button background gray, to show the user that it is available to buy
	    			soilButton.setBackground(Color.GRAY);
	    			
	    			//refresh the amount of money
					NewGameFrame.refreshTheMoney();
					//close the popupframe  
					popupSoilActions.dispose();
					//affirmative message to the user
					Message information = new Message("Item was sold successfully!");
					information.setVisible(true);
	    		}
	    		//if it wasn't success
	    		else {
	    			//buttons status is stay
	    			buySoil.setEnabled(false);
	    			sellSoil.setEnabled(true);
					plantSeed.setEnabled(true);
					watering.setEnabled(false);
					harvestPlant.setEnabled(false);
					
	    			//close the frame
					popupSoilActions.dispose();
					//if something went wrong, send errorMessage to the user
					ErrorMessage error = new ErrorMessage("Failed to sell the selected item!");
					error.setVisible(true);	
	    		}
	    	}
	    });
		//plantSeed on the soil
	    plantSeed.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae) {
	    		//if the inventory is empty then send a message to the user
	    		if(chooseSeed.getItemCount() == 0) {
	    			//close
					popupSoilActions.dispose();
					//if something went wrong, send errorMessage to the user
					ErrorMessage error = new ErrorMessage("Didn't have any seed to plant!");
					error.setVisible(true);	
	    		}
	    		//if the player has at least one seed
	    		else {
	    			//choose a seed ID from the jcombobox
		    		int selected = (int)chooseSeed.getSelectedItem();
		    		//plant the chosen seed and take out from the inventory
		    		for(int i = 0; i < player.getInventoryItems().size(); i++) {
		    			if(selected == player.getInventoryItems().get(i).getId()) {
		    				plantedSeed = player.getInventoryItems().get(i);
		    				player.getInventoryItems().remove(i);
		    			}
		    		}
		    		//refresh the content of this jcombobox
					refreshTheContentOfSeeds();
					chooseSeed.repaint();
					//refresh the content of the shop jcombobox
					NewGameFrame.refreshTheContent();
					NewGameFrame.availableToSell.repaint();
					//refresh the player inventory
					NewGameFrame.repaintTheInventory();
					
					//set the buttons state
	    			buySoil.setEnabled(false);
	    			sellSoil.setEnabled(true);
					plantSeed.setEnabled(false);
					watering.setEnabled(true);
					harvestPlant.setEnabled(false);

					//show the waterInfo for the user
					waterInfo.setVisible(true);
					waterInfo.setText("It needs to be watered " + plantedSeed.getGrowTime() + " more times!");
					
					//set the soul background brown to show that player that they planted here something
					soilButton.setBackground(new Color(150, 110, 70));
					
					//close the popup frame  
					popupSoilActions.dispose();
					//affirmative message to the user
					Message information = new Message(selected +" : Item was planted successfully!");
					information.setVisible(true);
	    		}
	    	}
	    });
	    //watering the soil
	    watering.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae) {
	    		//set the watered status true and use the plant water method
	    		plantedSeed.water();
	    		//change the buttons status
    			buySoil.setEnabled(false);
    			sellSoil.setEnabled(true);
				plantSeed.setEnabled(false);

				//waterInfo number changes
				waterInfo.setText("It needs to be watered " + plantedSeed.getGrowTime() + " more times!");
				/*if the planted seed is grown,
				 * than the harvest button is enabled and the background is yellow
				 *  to show the player that the plant is ready to harvest*/
				if(plantedSeed.isGrown()) {
					harvestPlant.setEnabled(true);
					watering.setEnabled(false);
					waterInfo.setVisible(false);
					if(plantedSeed.getName().equals("Wheat"))
						soilButton.setBackground(Color.yellow);
					if(plantedSeed.getName().equals("Tomato"))
						soilButton.setBackground(Color.red);
					if(plantedSeed.getName().equals("Carrot"))
						soilButton.setBackground(Color.orange);
				}
				//close the popup frame  
				popupSoilActions.dispose();
				//affirmative message to the user
				Message information = new Message("Seed was watered successfully!");
				information.setVisible(true);
	    		
	    	}
	    });
	    //harvest the plant
	    harvestPlant.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae) {
	    		//check it if it is grown already
	    		if(plantedSeed.isGrown()) {
	    			//add the profit to the player and refresh the amount
	    			int profit = plantedSeed.getHarvestProfit();
					player.riseMoney(profit);
					NewGameFrame.refreshTheMoney();
					//plantedSeed is null after harvesting
	    			plantedSeed = null;
	    			//button background is green, to show the player that available to plant seed again
	    			soilButton.setBackground(Color.green);
	    			
	    			//change the buttons status
	    			buySoil.setEnabled(false);
	    			sellSoil.setEnabled(true);
					plantSeed.setEnabled(true);
					watering.setEnabled(false);
					harvestPlant.setEnabled(false);
					
					//close the popup frame  
					popupSoilActions.dispose();
					//affirmative message to the user
					Message information = new Message("Seed was harvested successfully!");
					information.setVisible(true);
	    		}
	    	}
	    });
    }
    //refresh the content of the jcombobox
    public void refreshTheContentOfSeeds() {
    	chooseSeed.removeAllItems();
 
		for(int i = 0; i < player.getInventoryItems().size(); i++) {
			chooseSeed.addItem(player.getInventoryItems().get(i).getId());
		}
		
    }
    //initialize the already owned soil available interaction buttons
    public void init() {
		buySoil.setEnabled(false);
		sellSoil.setEnabled(true);
		plantSeed.setEnabled(true);
		watering.setEnabled(false);
		harvestPlant.setEnabled(false);
		
		soilButton.setBackground(Color.green);
    }
    //return the main soilButton
    public JButton getButton() {
    	return soilButton;
    }
    public void Customize() {
    	//customize the popup-frame
		popupSoilActions.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        popupSoilActions.setSize(600, 150);
        popupSoilActions.setResizable(false);
        popupSoilActions.setLocationRelativeTo(null);
        //add the smaller components to one that organize them 
		soilActions.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		soilActions.add(buySoil);
		soilActions.add(sellSoil);
		soilActions.add(chooseSeed);
		soilActions.add(plantSeed);
		soilActions.add(watering);
		soilActions.add(harvestPlant);
        //add components to the frame
		popupSoilActions.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		popupSoilActions.add(text);
		popupSoilActions.add(soilActions);

		waterInfo.setVisible(false);
		popupSoilActions.add(waterInfo);
		
		
    }
    //return the id
    public int getId() {
    	return id;
    }
    //return the soil name
    public String getName() {
    	return name;
    }
    //return the buy price
    public int getBuyPrice() {
    	return buyPrice;
    }
    //return the soil is available for the player
    public boolean isOpen() {
        return isOpen;
    }
    //change the soil status, is it available for the player
    public void setOpen(boolean status) {
    	isOpen = status;
    }

    //return the plant is grown and ready to harvest
    public boolean isGrown() {
        return isOpen && plantedSeed != null && plantedSeed.isGrown();
    }
    public Seed getPlanted() {
    	return plantedSeed;
    }
    //plant a new seed
    public void plantSeed(Seed seed) {
        if (isOpen && plantedSeed == null) {
            String currentname = seed.getName();
			switch(currentname){
				case "Wheat" :
					plantedSeed = new Wheat(seed.getId(), seed.getName(), seed.getBuyPrice(), seed.getGrowTime(), seed.getHarvestProfit());
					break;
				case "Tomato" :
					plantedSeed = new Tomato(seed.getId(), seed.getName(), seed.getBuyPrice(), seed.getGrowTime(), seed.getHarvestProfit());
					break;
				case "Carrot" :
					plantedSeed = new Carrot(seed.getId(), seed.getName(), seed.getBuyPrice(), seed.getGrowTime(), seed.getHarvestProfit());
					break;
				default: break;
			}
        }
    }
    //water the current seed
    public void water() {
        if (plantedSeed != null) {
            plantedSeed.water();
        }
    }
    //harvest the current plant
    public int harvest() {
        int profit = 0;
        if (isGrown()) {
            profit = plantedSeed.getHarvestProfit();
            plantedSeed = null;
        }
        return profit;
    }

}

