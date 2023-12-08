package main;
import game.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;


public class tests {
	//test Inventory class
	//test Player class
	//test two methods from Soil class
    private Inventory testInventory;
    private Seed testSeed;
    private Player testPlayer;
    private Soil testSoil;
  


    @Before
    public void setUp() {
        testInventory = new Inventory();
        testSeed = new Seed(1, "TestSeed", 80, 1, 100);
        testPlayer = new Player("TestPlayer");
        testSoil = new Soil(null, testPlayer, null, 1, "TestSoil", 200);

    }
    @Test
    public void testAddItem() {
    	assertTrue(testInventory.getItems().isEmpty());

        //add item to the inventory
    	assertTrue(testInventory.addItem(testSeed));
    	assertEquals(1, testInventory.getItems().size());

        //attempt to add the same item again
        assertFalse(testInventory.addItem(testSeed));
        assertEquals(1, testInventory.getItems().size());

        //fill the inventory to its maximum size
        for (int i = 2; i <= 30; i++) {
            assertTrue(testInventory.addItem(new Seed(i, "TestSeed" + i, 80, 1, 100)));
        }
        //attempt to add one more item when inventory is full
        assertFalse(testInventory.addItem(new Seed(101, "TestSeed101", 80, 1, 100)));
        assertEquals(30, testInventory.getItems().size());
    }

    @Test
    public void testRemoveItem() {
        assertTrue(testInventory.getItems().isEmpty());

        //attempt to remove an item from an empty inventory
        assertFalse(testInventory.removeItem(1));

        //add item to the inventory
        assertTrue(testInventory.addItem(testSeed));
        assertEquals(1, testInventory.getItems().size());

        //remove the added item
        assertTrue(testInventory.removeItem(1));
        assertTrue(testInventory.getItems().isEmpty());

        //attempt to remove a non-existing item
        assertFalse(testInventory.removeItem(1));
    }

    @Test
    public void testGetOneItem() {
        assertTrue(testInventory.getItems().isEmpty());

        //add an item to the inventory
        assertTrue(testInventory.addItem(testSeed));

        //get the added item by id
        Seed retrievedSeed = testInventory.getOneItem(1);
        assertNotNull(retrievedSeed);
        assertEquals(testSeed, retrievedSeed);

        //get a non-existing item by id
        assertNull(testInventory.getOneItem(2));
    }

    @Test
    public void testIsFull() {
        assertFalse(testInventory.isFull());

        //fill the inventory to its maximum size
        for (int i = 1; i <= 30; i++) {
            assertTrue(testInventory.addItem(new Seed(i, "TestSeed" + i, 80, 1, 100)));
        }

        //check if the inventory is full
	    assertTrue(testInventory.isFull());
	    }

    @Test
    public void testAddSoil() {
        assertTrue(testPlayer.getSoils().isEmpty());
        
        //set money to zero
        testPlayer.setMoney(0);
        //attempt to add a soil, while the player didn't have enough money
        assertFalse(testPlayer.addSoil(testSoil));
        assertTrue(testPlayer.getSoils().isEmpty());

        //add the proper funds to the player
        testPlayer.setMoney(200);
        
        //attempt to add a soil that is already open
        testSoil.setOpen(true);
        assertFalse(testPlayer.addSoil(testSoil));
        assertTrue(testPlayer.getSoils().isEmpty());
        
        //change the open status to add the soil properly
        testSoil.setOpen(false);
        //add a new soil successfully
        assertTrue(testPlayer.addSoil(testSoil));
        assertFalse(testPlayer.getSoils().isEmpty());
        
    }

    @Test
    public void testSellSoil() {
        assertTrue(testPlayer.getSoils().isEmpty());

        //add a new soil to the player
        testPlayer.setMoney(200);
        assertTrue(testPlayer.addSoil(testSoil));
        assertFalse(testPlayer.getSoils().isEmpty());

        //attempt to sell a soil that the player doesn't own
        assertFalse(testPlayer.sellSoil(new Soil(null, testPlayer, null, 2, "TestSoil2", 200)));
        assertFalse(testPlayer.getSoils().isEmpty());

        //successfully sell the added soil
        assertTrue(testPlayer.sellSoil(testSoil));
        assertTrue(testPlayer.getSoils().isEmpty());
    }

    @Test
    public void testAddToInventory() {
        assertTrue(testPlayer.getInventoryItems().isEmpty());

        testPlayer.setMoney(0);
        //attempt to add a seed, while the player didn't have enough money
        assertFalse(testPlayer.addToInventory(testSeed));
        assertTrue(testPlayer.getInventoryItems().isEmpty());

        //add money to the player
        testPlayer.setMoney(100);

        //add a seed successfully
        assertTrue(testPlayer.addToInventory(testSeed));
        assertFalse(testPlayer.getInventoryItems().isEmpty());
    }

    @Test
    public void testRemoveFromInventory() {
        assertTrue(testPlayer.getInventoryItems().isEmpty());

        //attempt to remove a non-existing item
        assertFalse(testPlayer.removeFromInventory(1));

        //add a seed to the player's inventory
        testPlayer.setMoney(80);
        assertTrue(testPlayer.addToInventory(testSeed));
        assertFalse(testPlayer.getInventoryItems().isEmpty());

        //remove the added seed
        assertTrue(testPlayer.removeFromInventory(1));
        assertTrue(testPlayer.getInventoryItems().isEmpty());
    }
    @Test
    public void testPlantSeed() {
        assertFalse(testSoil.isGrown());

        //plant a seed
        testSoil.setOpen(true);
        testSoil.plantSeed(testSeed);
        
        //check if the seed is watered, planted and equals it correctly
        assertFalse(testSoil.isWatered());
        assertNotNull(testSoil.getPlanted());
        assertEquals(testSeed, testSoil.getPlanted());
    }
    @Test
    public void testWater() {
    	//plant a seed
        testSoil.plantSeed(testSeed);
        //check it's watering status
        assertFalse(testSoil.isWatered());

        //water the soil
        testSoil.water();
        //check the status
        assertTrue(testSoil.isWatered());
    }


}

