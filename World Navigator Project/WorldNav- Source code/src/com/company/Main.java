/*
 * World Navigator - file Main.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {

    /*
    * Things to do, convert all the for loops into ForEach
    * That is, a function that is called should be below a function that does the calling.2
    */


    String mapNames[]={"input.txt"};
    int mapId = PlayerConsole.readMenuChoice("Choose one of these maps:",mapNames);
    String[] mainOptions = {
      "Navigate",
      "Look",
      "Check",
      "Open",
      "Trade",
      "Use flashlight",
      "Use Key",
      "SwitchLights",
      "Quit",
      "Restart"
    };


    MapLoader map = new MapLoader(mapNames[mapId-1]);

    // write your code here
    Player player = new Player();
   boolean run = true;
    while (player.run(map.mapSize())&&run&&map.timer()) {
      PlayerConsole.display("");
      PlayerConsole.display("              "+map.getTime()/1000/60+":" +(map.getTime()/1000)%60 + " left.");
      //   int commandNumber = getCommandOptions();
      int commandNumber = PlayerConsole.readMenuChoice("Enter your Choice: ", mainOptions);

      switch (commandNumber) {
        case 1:
          navigate(player, map.getRoom(player));
          break;
        case 2:
          look(player, map.getRoom(player));
          break;
        case 3:
          check(player, map.getRoom(player));
          break;
        case 4:
          open(player, map.getRoom(player));
          break;
        case 5:
          trade(player, map.getRoom(player));
          break;
        case 6:
          player.useFlashLight();
          break;
        case 7:
          useKey(player, map.getRoom(player));
          break;
        case 8:
          map.getRoom(player).switchLight();
          break;
        case 9:
          run = false;
          break;
        case 10:
          map = new MapLoader("input.txt");
          player = new Player();
          break;
        default:
          System.out.println("That in not available option!");
      }
    }
  }

  public static void navigate(Player player, Room room) {
    String[] navigationCommand = {"Left", "Right", "Forward", "Backward", "Player status"};
    int choose = PlayerConsole.readMenuChoice("Choose one of these options:", navigationCommand);
    switch (choose) {
      case 1:
        player.orientationChange("left",room);
        break;
      case 2:
        player.orientationChange("right",room);
        break;
      case 3:
        player.move("forward",room);
        break;
      case 4:
        player.move("backward",room);
        break;
      case 5:
        player.printInfo();
        break;
    }
  }

  public static void look(Player player, Room room) {
    player.look(room);
  }

  public static void check(Player player, Room room) {
    String[] checkOptions= {"Check Door","Check Chest","check Painting","check Mirror"};
    int choose = PlayerConsole.readMenuChoice("Choose one of these options: ",checkOptions);
    String furniture = "";
    switch (choose) {
      case 1:
        furniture = "door";
        break;
      case 2:
        furniture = "chest";
        break;
      case 3:
        furniture = "painting";
        break;
      case 4:
        furniture = "mirror";
        break;
    }
    FurnitureChecking furnitureChecking =new FurnitureChecking(player,room,furniture);
    furnitureChecking.checkFurniture();
  }


  public static void open(Player player, Room room) {
   OpenDoor openDoor =new OpenDoor(player,room);
    openDoor.open();
  }

  public static void trade(Player player, Room room) {
    Furniture furniture = room.getFurniture(player);
    if (furniture.getIdentification().equals("seller")) {
      Trade trade = Trade.makeTrade(player, (Seller) furniture);
      while (trade instanceof Buy) {
        trade = Trade.makeTrade(player, (Seller) furniture);
      }
    } else {
      System.out.println("Your are not facing seller");
    }
  }


  public static void useKey(Player player, Room room) {
    KeyUsage keyUsage = new KeyUsage(player,room);
    keyUsage.useKey();
  }
}
