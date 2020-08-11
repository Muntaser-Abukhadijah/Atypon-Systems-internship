package com.company;

public class OpenDoor {
  Player player;
  Room room;
  Furniture furniture;

  public OpenDoor(Player player, Room room) {
    this.player = player;
    this.room = room;
    furniture = room.getFurniture(player);
  }

  private boolean checkOppositeFurniture() {
    if (furniture.getIdentification().equalsIgnoreCase("door")) {
      return true;
    }
    return false;
  }

  public void open() {
    if (checkOppositeFurniture()) {
      ((Door) furniture).openDoor();
    } else {
      PlayerConsole.display("That is not a Door");
    }
  }

  /**
   * Returns the player who wants to open the door, and the room that contain this door, with the
   * furniture.
   */
  @Override
  public String toString() {
    return "OpenDoor{" + "player=" + player + ", room=" + room + ", furniture=" + furniture + '}';
  }
}
