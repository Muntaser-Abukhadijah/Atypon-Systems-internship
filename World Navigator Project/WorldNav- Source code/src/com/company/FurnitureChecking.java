package com.company;

public class FurnitureChecking {
  private Player player;
  private Room room;
  private String furnitureName;
  private Furniture furniture;

  public FurnitureChecking(Player player, Room room, String furnitureName) {
    this.player = player;
    this.room = room;
    this.furnitureName = furnitureName;
    this.furniture = room.getFurniture(player);
  }

  private boolean checkOppositeFurnitureName() {
    if (furniture.getIdentification().equalsIgnoreCase(furnitureName)) {
      return true;
    }
    return false;
  }

  private boolean isMoneyBag(Item item) {
    if (item instanceof MoneyBag) {
      return true;
    }
    return false;
  }

  private void addItem(Item item) {
    if (isMoneyBag(item)) {
      player.earnGold(new Key("null*", ((MoneyBag) item).getAmountOfMoney()));
    } else {
      player.getItemCart().addItem(item);
    }
  }

  public void checkFurniture() {
    if (isItTheRightFurniture()) {
      if(!(room.isLightStateOn()||player.isFlashLightOn())){
        PlayerConsole.display("It is dark here!");
        return;
      }
      ItemsCart<Item> itemsCart = ((CheckableFurniture) furniture).checkFurniture();
      if (itemsCart.numberOfItems() > 0) {
        for (int i = 0; i < itemsCart.numberOfItems(); i++) {
          addItem(itemsCart.getItem(i));
          PlayerConsole.display(itemsCart.getItem(i).getInfo() + "was acquired");
        }
        for (int i = itemsCart.numberOfItems() - 1; i > -1; i--) {
          itemsCart.removeItem(i);
        }
      } else if (!(furniture instanceof Door)) {
        PlayerConsole.display("There is nothing to acquire");
      }
    } else {
      PlayerConsole.display("That is not " + furnitureName);
    }
  }

  private boolean isItTheRightFurniture() {
    return checkOppositeFurnitureName() && furniture instanceof CheckableFurniture;
  }

  /**
   * Returns a brief description for all of the player who want's to check type of furniture,
   * current room, furniture name and description of the opposite furniture
   */
  @Override
  public String toString() {
    return "FurnitureChecking{"
        + "player="
        + player
        + ", room="
        + room
        + ", furnitureName='"
        + furnitureName
        + '\''
        + ", furniture="
        + furniture
        + '}';
  }
}
