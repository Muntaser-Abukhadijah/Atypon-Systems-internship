/*
 * World Navigator - file Player.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Player implements Comparable<Player> {

  private int rowPosition;
  private int columnPosition;
  private String direction;
  private int amountOfGold;
  private ItemsCart<Item> itemsCart;

  public Player() {
    this.rowPosition = 0;
    this.columnPosition = 0;
    this.direction = "South";
    this.amountOfGold = 0;
    this.itemsCart = new ItemsCart(new ArrayList<Item>());
  }

  public int getRowPosition() {
    return rowPosition;
  }

  public int getColumnPosition() {
    return columnPosition;
  }

  public String getDirection() {
    return direction;
  }

  public int getAmountOfGold() {
    return amountOfGold;
  }

  public void orientationChange(String rotationDirection, Room room) {
    PlayerDirectionRotate playerDirectionRotate =
        new PlayerDirectionRotate(rotationDirection, getDirection());
    setDirection(playerDirectionRotate.getOrientation());
  }

  private void setDirection(String orientation) {
    this.direction = orientation;
  }

  public void move(String movementDirection, Room room) {
    PlayerMovement playerMovement = new PlayerMovement(this, room, movementDirection);
    Pair pair = playerMovement.move();
    if (pair.getX() + pair.getY() != -2) {
      setRowPosition(pair.getX());
      setColumnPosition(pair.getY());
    }
  }

  private void setRowPosition(int x) {
    rowPosition += x;
  }

  private void setColumnPosition(int y) {
    columnPosition += y;
  }

  public void printInfo() {
    PlayerConsole.display("Your direction is : " + getDirection());
    PlayerConsole.display("You have " + getAmountOfGold() + " Gold");
    PlayerConsole.display("Your position is : " + getRowPosition() + " " + getColumnPosition());
    if (itemsCart.numberOfItems() == 0) System.out.println("There is no items!");
    for (int i = 0; i < itemsCart.numberOfItems(); i++) {
      PlayerConsole.display((itemsCart.getItem(i)).getInfo());
    }
  }

  public void earnGold(Item item) {
    amountOfGold += item.getPrice();
  }

  public void payGold(Item item) {
    amountOfGold -= item.getPrice();
  }

  public void look(Room room) {
    PlayerLook playerLook = new PlayerLook(this, room);
    playerLook.printOppositeFurniture();
  }

  public void useFlashLight() {
    FlashLightSwitch flashLightSwitch = new FlashLightSwitch(this);
    flashLightSwitch.switchFlashLight();
  }

  public boolean isFlashLightOn() {
    FlashLightSwitch flashLightSwitch = new FlashLightSwitch(this);
    return flashLightSwitch.isFlashLightOn();
  }

  public ItemsCart getItemCart() {
    return itemsCart.getCopy();
  }

  public boolean run(Pair mapSize){
    if(rowPosition>-1&&rowPosition<mapSize.getX()&&
            columnPosition>-1&&columnPosition<mapSize.getY()){
      return true;
    }else {
      PlayerConsole.display("You win!!");
      return false;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return rowPosition == player.rowPosition
        && columnPosition == player.columnPosition
        && amountOfGold == player.amountOfGold
        && direction.equals(player.direction)
        && itemsCart.equals(player.itemsCart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rowPosition, columnPosition, direction, amountOfGold, itemsCart);
  }

  /**
   * Returns the string representation of this Player. The string consists whose format is
   * "Player{rowPosition=XXX', columnPosition=YYY', direction=ZZZ, amountOfGold=TTT, itemsCart=NNN
   * }" where XXX is the X position of the player inside the map, YYY is Y position, ZZZ is the
   * players orientation, TTT is the amount of gold that player has. NNN is the description of each
   * item player has.
   */
  @Override
  public String toString() {
    return "Player{"
        + "rowPosition="
        + rowPosition
        + ", columnPosition="
        + columnPosition
        + ", direction='"
        + direction
        + '\''
        + ", amountOfGold="
        + amountOfGold
        + ", itemsCart="
        + itemsCart
        + '}';
  }

  @Override
  public int compareTo(Player o) {
    if (o == null) {
      throw new NullPointerException();
    }
    int result = Integer.compare(amountOfGold, o.amountOfGold);
    if (result != 0) {
      return result;
    }

    // result == 1  if this.itemCart.size() > o.itemsCart.size()
    // result == -1  if this.itemCart.size() < o.itemsCart.size()
    // result == 0  if this.itemCart.size() == o.itemsCart.size()
    result = itemsCart.compareTo(o.itemsCart);
    if (result != 0) {
      return result;
    }
    return direction.compareToIgnoreCase(direction);
  }
}
