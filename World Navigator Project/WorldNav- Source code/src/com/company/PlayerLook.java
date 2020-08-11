package com.company;

public class PlayerLook {

  Player player;
  Room room;

  public PlayerLook(Player player, Room room) {
    this.player = player;
    this.room = room;
  }

  public void printOppositeFurniture() {
    if (player.isFlashLightOn() || room.isLightStateOn()) {
      Furniture furniture = room.getFurniture(player);
      PlayerConsole.display(furniture.getIdentification());
    } else {
      PlayerConsole.display("It is dark here!");
    }
  }

  /** Returns the player the room that stands in . */
  @Override
  public String toString() {
    return "PlayerLook{" + "player=" + player + ", room=" + room + '}';
  }
}
