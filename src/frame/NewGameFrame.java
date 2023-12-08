package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import game.*;

//start a new game
public class NewGameFrame extends JFrame{
	
	//farm: player, shop, soils
	public static Farm farm;
	
	//objects on the north of the frame
	private JPanel playerDetails;
	private JLabel playerName;
	private static JLabel playerMoney;
	private JButton backToMenu;
	private JButton help;
	
	//west side toolbar: contains the inventory and the shop
	private JToolBar toolbar;
	private JButton inventoryButton;
	private JButton shopButton;
	
	//popup side-frame for the shop
	private JDialog popupShop;
	private List<JButton> buttonsInTheShop = new ArrayList<>();
	private Map<Integer, Seed> shopItems;
	public static JComboBox<Integer> availableToSell = new JComboBox<>();
	private JButton sellButton;
	
	//popup side-frame for the inventory
	private JDialog popupInventory;
	private JPanel mainPanel;
	private JPanel headerPanel;
	private static JPanel inventoryPanel;
	public static List<Seed> inventoryItems;

	//soil panel
	private JPanel soilsPanel;
	private List<Soil> soils;
	private List<JButton> soilsButton = new ArrayList<>();
	
	//initialize the new game with the added playerName
	public NewGameFrame(String PlayerName) {
		//title of the window
		super("Daisy Fields - NewGame");
		//set the window size, not resizable
		super.setSize(1280, 720);
		super.setResizable(false);
		//click on the x, shut down the program
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//window appears on the center of the monitor
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setBackground(new Color(153, 255, 153));
		
		if(PlayerName.equals("loadgame")) {
			farm = new Farm(PlayerName);
			LoadGame.loadState(this, "saved.txt");
			
			initPlayerDetails();	
			initToolBar();
			initSoils();
			initSoilPanel();
			soils = farm.getAllSoils();
		}
		else {
			//initialize the containers and the farm
			farm = new Farm(PlayerName);
			initPlayerDetails();
			initToolBar();
			initSoilPanel();
			soils = farm.getAllSoils();
		}
		
		//add the containers to the frame
		add(playerDetails, BorderLayout.NORTH);
		add(toolbar, BorderLayout.WEST);
		add(soilsPanel, BorderLayout.CENTER);
	}
	public void initSoils() {
		for(Soil soil: farm.getPlayer().getSoils()) {
			soil.init();
		}
	}
	
	//initialize the details
	public void initPlayerDetails() {
		//create the containers
		playerDetails = new JPanel();
		playerName = new JLabel();
		playerMoney = new JLabel();
		backToMenu = new JButton("SAVE and go back to the Menu!");
		help = new JButton("Help");
		
		//customize the containers and the texts
		CustomizePlayerDetails();
		
		//go back to the main menu button response to clicking
		NewGameFrame frame = this;
		backToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				NewGameFrameSaver.saveState(frame, "saved.txt");
				//close the current frame
				dispose();
				//create the main menu frame and set visible for the user
				MyFrame frame = new MyFrame();			
				frame.setVisible(true);
			}
		});
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				Help helpframe = new Help();
				helpframe.setVisible(true);
			}
		});
		
		//customize the order of the containers
		playerDetails.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
		playerDetails.setBackground(new Color(153, 255, 153));
		//adding the containers to one
		playerDetails.add(playerName);
		playerDetails.add(playerMoney);
		playerDetails.add(backToMenu);
		playerDetails.add(help);
	}
	//customize the player details
	public void CustomizePlayerDetails() {
		//get the current details of the player
		String name = farm.getPlayer().getName();
		int money = farm.getPlayer().getMoney();
		
		//fix the current details
		playerName.setText(name);
		playerMoney.setText("Money: " + money);
		
		//change the font type
		Font f_text = new Font("Cooper Black", Font.PLAIN, 22);
		playerName.setFont(f_text);
		playerMoney.setFont(f_text);
		backToMenu.setFont(f_text);
		help.setFont(f_text);
		
		playerName.setBackground(new Color(153, 255, 153));
		playerMoney.setBackground(new Color(153, 255, 153));
		backToMenu.setBackground(new Color(0, 204, 0));
		help.setBackground(new Color(0, 204, 0));
	}
	
	//initialize the toolbar
	public void initToolBar() {
		//create the objects and containers
		inventoryButton = new JButton("Inventory");
		shopButton = new JButton("Shop");
		toolbar = new JToolBar();
		toolbar.setOrientation(SwingConstants.VERTICAL);
		toolbar.setFloatable(false);
		
		//initialize the popup side-frames
		initPopUpInventory();
		initPopUpShop();
		toolbar.setBackground(new Color(153, 255, 153));
		inventoryButton.setBackground(new Color(0,204,0));
		shopButton.setBackground(new Color(0,204,0));
		
		//add response to the buttons
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//set the popup frame visible to the user
                popupInventory.setVisible(true);
			}
		});
		
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//set the popup frame visible to the user
                popupShop.setVisible(true);
			}
		});
		
		//add buttons to a main_toolbar container
		toolbar.add(inventoryButton);
		toolbar.addSeparator();
		toolbar.add(shopButton);
	}
	//initialize the inventory frame
	public void initPopUpInventory() {
		//set the popup default properties
		popupInventory = new JDialog(this, "Inventory", true);
		
		popupInventory.setDefaultCloseOperation(HIDE_ON_CLOSE);
        popupInventory.setSize(200, 200);
        popupInventory.setResizable(false);
        
        //create the panels
        mainPanel = new JPanel();
        headerPanel = new JPanel();
        inventoryPanel = new JPanel();
        
        JLabel header = new JLabel("ID             NAME");
        headerPanel.add(header);
        
        //refresh the inventory content
        repaintTheInventory();

        
        //change the order of the content
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));   
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        //adding the panels to a main panel
        mainPanel.add(headerPanel);
        mainPanel.add(inventoryPanel);
        
        //add main panel to the frame
        popupInventory.add(mainPanel);
        popupInventory.setLocationRelativeTo(this);
        
	}
	//initialize the shop frame
	public void initPopUpShop() {
		//set the default properties
		popupShop= new JDialog(this, "Shop", true);
		
		popupShop.setDefaultCloseOperation(HIDE_ON_CLOSE);
        popupShop.setSize(600, 600);
        popupShop.setResizable(false);
        
        //create panels and buttons
        JPanel sidePanel = new JPanel();
        JLabel shoppingPanel = new JLabel("Buy: Click on the Seed || Sell: Choose the Id");   
        sellButton = new JButton("Sell");
        JPanel buttonPanel = new JPanel();
        sellButton.setBackground(new Color(0,204,0));
        
        //get the shopItems
        shopItems = farm.getShop().getShopItems();
        
        //create the buttons for shopping
        for(Seed item: shopItems.values()) {
        	String buttonName = item.getId() +" : "+ item.getName() + " || Price: "+ item.getBuyPrice();
        	JButton currentButton = new JButton(buttonName);
			Seed inInventory = farm.getShop().findItemInInventory(farm.getPlayer(), item);

			if(inInventory != null){
				currentButton.setEnabled(false);
			}
			switch (item.getName()) {
				case "Wheat":
					currentButton.setBackground(new Color(255, 255, 102));
					break;
				case "Tomato": 
					currentButton.setBackground(new Color(255, 102, 102));
					break;
				case "Carrot":
					currentButton.setBackground(new Color(255, 178, 102));
					break;
				default:
					break;
			}
        	buttonsInTheShop.add(currentButton);
        }
        //add the buttons to a panel to organize them
        for (int i = 0; i < buttonsInTheShop.size(); i++) {
        	buttonPanel.add(buttonsInTheShop.get(i));
        }
        //refresh the JComboBox content and the Inventory
        refreshTheContent();
        availableToSell.repaint();

        
        //set the frame layout
        popupShop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setLayout(new GridLayout(15, 2, 5, 5));
        sidePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        //add actions to the buttons
        ButtonActions();
        
        //organize the objects
        sidePanel.add(shoppingPanel);
        sidePanel.add(availableToSell);
        sidePanel.add(sellButton);
        
        //add panels to the frame
        popupShop.add(sidePanel);
        popupShop.add(buttonPanel);
        popupShop.setLocationRelativeTo(this);
	}
	/*MyActionListener class that implements ActionListener
	 *  to create the buy function when click one of the buttons
	*/
	public class BuyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			//get the Seed id
			JButton source = (JButton)ae.getSource();
			String[] text = source.getText().split(" ");
			int id = Integer.parseInt(text[0]);
			//success shows if the buying action was went through successfully or not
			boolean success = false;
			//current shopItems
			shopItems = farm.getShop().getShopItems();
			
			//try to add the selected item to the player's inventory
			for(Seed current: shopItems.values()) {
				if(id == current.getId()) {
					success = farm.getPlayer().addToInventory(current);
				}
			}
			//if the item was add successfully, than refresh the shop, inventory and money
			if(success) {

				source.setEnabled(false);
				refreshTheMoney();
				
				refreshTheContent();
				availableToSell.repaint();
				
				repaintTheInventory();
				
				soils = farm.getAllSoils();
		        for(int i = 0; i < soils.size(); i++) {
		        	soils.get(i).refreshTheContentOfSeeds();
		        	soils.get(i).chooseSeed.repaint();
		        }
				//close the shop frame  
				popupShop.dispose();
				//affirmative message to the user
				Message information = new Message(id + ": Item was bought successfully!");
				information.setVisible(true);
			}
			
			else {
				//close the shop
				popupShop.dispose();
				//if something went wrong, send errorMessage to the user
				ErrorMessage error = new ErrorMessage("Failed to buy the selected item!");
				error.setVisible(true);	
			}
		}
	}
	//create the button response
	public void ButtonActions() {
		//selling a chosen item
		sellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//get the selected id from the JComboBox
				int selectedId = (int)availableToSell.getSelectedItem();
				//try to remove
				boolean isRemoved = farm.getPlayer().removeFromInventory(selectedId);
				
				//after removed successfully, change the shop, inventory and money
				if(isRemoved) {
					
					refreshTheMoney();
					
					refreshTheContent();
					availableToSell.repaint();
					
					repaintTheInventory();
					
					for(JButton current: buttonsInTheShop){
						String[] buttonName = current.getText().split(" ");
						int id = Integer.parseInt(buttonName[0]);
						if(id == selectedId){
							current.setEnabled(true);
						}
					}
					
					soils = farm.getAllSoils();
			        for(int i = 0; i < soils.size(); i++) {
			        	soils.get(i).refreshTheContentOfSeeds();
			        	soils.get(i).chooseSeed.repaint();
			        }
					//affirmative message to the user
					popupShop.dispose();
					Message information = new Message("Item removed successfully!");
					information.setVisible(true);

				}
				//if something went wrong, send errorMessage to the user
				else {
					popupShop.dispose();
					ErrorMessage error = new ErrorMessage("Failed to remove the selected item!");
					error.setVisible(true);
				}
				
			}
		});
		//add actions to the seed buttons
		for(int i = 0; i < buttonsInTheShop.size(); i++) {
			buttonsInTheShop.get(i).addActionListener(new BuyActionListener());
		}
		
	}
	public static void refreshTheMoney() {
		playerMoney.setText("Money: " + farm.getPlayer().getMoney());
	}
	//refresh the content of JComboBox
	public static void refreshTheContent() {
        //if the combobox not empty, remove all items
		if(availableToSell.getItemCount() > 0) {
			availableToSell.removeAllItems();
		}
		//get the current elements of the inventory
		inventoryItems = farm.getPlayer().getInventoryItems();
		
		//fill the JComboBox with the updated inventory items
        for(int i = 0; i < inventoryItems.size(); i++) {
        	availableToSell.addItem(inventoryItems.get(i).getId());

        }
        
	}
	//repaint the inventory panel
	public static void repaintTheInventory() {
		//get the current items
        inventoryItems = farm.getPlayer().getInventoryItems();
        //remove the items before add the new ones 
        inventoryPanel.removeAll();
        
        //add the current items to the inventory
        for(int i = 0; i < inventoryItems.size(); i++) {
        	JLabel item = new JLabel();
        	String itemName = inventoryItems.get(i).getName();
        	int itemId = inventoryItems.get(i).getId();
        	item.setText(itemId + "        " +itemName);
        	inventoryPanel.add(item);
        }
	}
	//initialize the soil panel
	public void initSoilPanel() {
		//default properties
		soilsPanel = new JPanel();
		soilsPanel.setLayout(new GridLayout(5,5,10,10));
		soilsPanel.setBackground(new Color(153, 255, 153));
		soils = farm.getAllSoils();
		//add the buttons to the panel
		for (int i = 0; i < soils.size(); i++) {
			soilsButton.add(i, soils.get(i).getButton());
			soilsPanel.add(soilsButton.get(i));
		}
	}
}
