/*
 * World Navigator - file Map.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapLoader {

  public static FileReader myobj;
  private ArrayList<ArrayList<Room>> map;  // shouldn't it be list instead of arraylist
  private final long start = System.currentTimeMillis();
  private final int minuets;
  private long end;
  private int numberOfRows;
  private int numberOfColumns;

  static {
    try {
      myobj = new FileReader("input.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
  public static BufferedReader reader = new BufferedReader(myobj);

  public MapLoader(String mazeName) throws IOException {
    reader = new BufferedReader(new FileReader(mazeName));
    minuets =Integer.parseInt(reader.readLine()) ;
    System.out.println("Enter the number of rows: ");
    numberOfRows = Integer.parseInt(reader.readLine()) ;
    System.out.println(numberOfRows);
    System.out.println("Enter the number of columns: ");
    numberOfColumns = Integer.parseInt(reader.readLine()) ;
    System.out.println(numberOfColumns);

    map = new ArrayList<ArrayList<Room>>();
    for (int i = 0; i < numberOfRows; i++) {
      map.add(new ArrayList<Room>());
      for (int j = 0; j < numberOfColumns; j++) {
        System.out.println("Enter the number of Walls : \n");
        int numberOfWalls = Integer.parseInt(reader.readLine());
        System.out.println(numberOfWalls);
        List walls = new ArrayList<Wall>();
        for (int z = 0; z < numberOfWalls; z++) {

          // Create a wall
          System.out.println("Enter the direction : ");
          String direction = reader.readLine();
          System.out.println(direction);
          System.out.println("Enter the furnitureName : ");
          String furnitureName = reader.readLine();
          System.out.println(furnitureName);

          walls.add(createWall(direction, furnitureName));
        }
        System.out.println("Is there are light switch : ");
        boolean switchLight = Boolean.parseBoolean(reader.readLine());
        System.out.println(switchLight);
        System.out.println("What is the state of the light switch : ");
        boolean switchState = Boolean.parseBoolean(reader.readLine());
        System.out.println(switchState);
        map.get(i).add(new Room(i, j, walls, switchLight, switchState));
      }
    }
    reader.close();
    myobj.close();
  }

  public Room getRoom(Player player) {
    return map.get(player.getRowPosition()).get(player.getColumnPosition());
  }

  private ArrayList<ArrayList<Room>> loadMap() {
    return new ArrayList<ArrayList<Room>>();
  }

  private Wall createWall(String direction, String furnitureName) throws IOException {
    return new Wall(direction, createFurniture(furnitureName));
  }

  private Furniture createFurniture(String furnitureName) throws IOException {
    if (furnitureName.equalsIgnoreCase("Chest")) {
      return createChest();
    } else if (furnitureName.equalsIgnoreCase("Door")) {
      return createDoor();
    } else if (furnitureName.equalsIgnoreCase("Painting")) {
      return createPainting();
    } else if (furnitureName.equalsIgnoreCase("Mirror")) {
      return createMirror();
    } else if(furnitureName.equalsIgnoreCase("Seller")){
      return createSeller();
    }else{
      return createEmptyWall();
    }
  }

  private Furniture createEmptyWall() throws IOException {
    System.out.println("Enter wall color: ");
    String color = reader.readLine();
    System.out.println(color);
    return new FurnitureBuilder().wallColor(color).build("emptyWall");
  }

  private Furniture createChest() throws IOException {
    return new FurnitureBuilder().key(createKey()).lock(isLocked()).itemsCart(createItemCart()).build("chest");
  }

  private Furniture createDoor() throws IOException {
    System.out.println("Is the door open?");
    boolean isOpen = Boolean.parseBoolean(reader.readLine());
    System.out.println(isOpen);
    return new FurnitureBuilder().open(isOpen).key(createKey()).lock(isLocked()).build("door");
  }

  private Furniture createPainting() throws IOException {
    return new FurnitureBuilder().itemsCart(createItemCart()).build("painting");
  }

  private Furniture createMirror() throws IOException {
    return new FurnitureBuilder().itemsCart(createItemCart()).build("mirror");
  }

  private Furniture createSeller() throws IOException {
    return new FurnitureBuilder().itemsCart(createItemCart()).itemsPrices(createItemPrice()).build("seller");
 //   return new Seller(createItemCart(), createItemPriceList());
  }

  private ItemsCart<Item> createItemPrice() throws IOException {
    ItemsCart<Item> itemPrices = new ItemsCart<Item>();

    System.out.println("Enter the number of the items in the map: ");
    int numberOfItems = Integer.parseInt(reader.readLine());
    System.out.println("Number of items is : "+ numberOfItems);
   for(int i=0;i<numberOfItems;i++){
     System.out.println("Enter the item name: ");
     String itemName = reader.readLine();
      System.out.println(itemName);
     switch (itemName){
       case "key":
         itemPrices.addItem(createKey());
         break;
       case "flashLight":
         itemPrices.addItem(createFlashLight());
         break;
       case "moneyBag":
         itemPrices.addItem(createMoneyBag());
         break;
       default:
         System.out.println("Something went wrong");
     }
   }
    return itemPrices;
  }

  private Key createKey() throws IOException {
    System.out.println("Enter Key name: ");
    String lockKeyName = reader.readLine();
    System.out.println(lockKeyName);
    System.out.println("Enter Key price: ");
    int lockKeyPrice = Integer.parseInt(reader.readLine());
    System.out.println(lockKeyPrice);
    return new Key(lockKeyName, lockKeyPrice);
  }

  private boolean isLocked() throws IOException {
    System.out.println("Is locked: ");
    boolean isLocked = Boolean.parseBoolean(reader.readLine());
    System.out.println(isLocked);
    return isLocked;
  }

  private ItemsCart createItemCart() throws IOException {
    ItemsCart itemCart = new ItemsCart();
    System.out.println("Enter number of items: ");
    int numberOfItems = Integer.parseInt(reader.readLine());
    System.out.println(numberOfItems);
    for (int i = 0; i < numberOfItems; i++) {
      System.out.println("Enter Item name : ");
      String itemName = reader.readLine();
      System.out.println(itemName);
      if (itemName.equals("MoneyBag")) {
        itemCart.addItem(createMoneyBag());
      } else if (itemName.equals("Key")) {
        itemCart.addItem(createKey());
      } else {
        itemCart.addItem(createFlashLight());
      }
    }
    return itemCart;
  }

  private MoneyBag createMoneyBag() throws IOException {
    System.out.println("Enter amount of money: ");
    int amountOfMoney = Integer.parseInt(reader.readLine());
    System.out.println(amountOfMoney);
    System.out.println("Enter bag price: ");
    int moneyBagPrice = Integer.parseInt(reader.readLine());
    System.out.println(moneyBagPrice);
    return new MoneyBag(amountOfMoney, moneyBagPrice);
  }

  private FlashLight createFlashLight() throws IOException {
    System.out.println("Enter flash light price : ");
    int flashLightPrice = Integer.parseInt(reader.readLine());
    System.out.println(flashLightPrice);
    return new FlashLight(flashLightPrice);
  }

  public Pair mapSize(){
    return new Pair(numberOfRows,numberOfColumns);
  }

  public boolean timer(){
    end = start + minuets *1000;
    if(!(System.currentTimeMillis() < end)){
      System.out.println("Timeout, Gameover!");
      return false;
    }
    return true;
  }

  public long getTime(){
    return end - System.currentTimeMillis();
  }

  /**
   * Returns a the content of each room in the map.
   */

  @Override
  public String toString() {
    return "Map{" +
            "map=" + map +
            '}';
  }
}
