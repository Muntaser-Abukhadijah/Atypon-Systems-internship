/*
 * World Navigator - file Trade.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.util.List;

/** The type Trade. */
public class Trade {
  private static String[] options = {"Buy", "Sell", "List", "Finish"};
  /** The Player. */
  protected Player player;

  /** The Seller. */
  protected Seller seller;

  /** The Item id. */
  protected int itemId;

  /** The Item. */
  protected Item item;

  /**
   * Instantiates a new Trade.
   *
   * @param player the player
   * @param seller the seller
   */
  public Trade(Player player, Seller seller) {
    this.player = player;
    this.seller = seller;
  }

  /**
   * Make trade trade.
   *
   * @param player the player
   * @param seller the seller
   * @return the trade
   */
  public static Trade makeTrade(Player player, Seller seller) {

    PlayerConsole.display("\nSeller items : ");
    seller.getItemCart().printItems();

    int choice = 0;
    while (choice != 4) {
      choice = PlayerConsole.readMenuChoice("Choose of the options: ", options);
      switch (choice) {
        case 1:
          new Buy(player, seller).performTrade();
          return new Buy(player, seller);
        case 2:
          new Sale(player, seller).performTrade();
          return new Buy(player, seller);
        case 3:
          List<Item> itemCart = seller.getItemCart().getList();
          if (itemCart.size() == 0) {
            PlayerConsole.display("Seller doesn't have items!");
          } else {
            PlayerConsole.display("Seller items: ");
            for (int i = 0; i < itemCart.size(); i++) {
              PlayerConsole.display(itemCart.get(i).getInfo());
            }
          }
          return new Buy(player, seller);
        default:
          return new Sale(player, seller);
      }
    }
    return new Buy(player, seller);
  }

  /** Perform trade. */
  protected void performTrade() {};;

  /**
   * To check if the item cart is empty or not.
   *
   * @param itemsCart the items cart to be checked
   * @return the true in case the itemCart contain items,otherwise false.
   */
  protected boolean isItemCartContainsItems(ItemsCart itemsCart) {
    return itemsCart.numberOfItems() > 0;
  }

  /**
   * Is item id in item cart boolean.
   *
   * @param itemsCart the items cart
   * @param itemId the item id
   * @return the boolean
   */
  protected boolean isItemIdInItemCart(ItemsCart itemsCart, int itemId) {
    return itemId >= 0 && itemId < itemsCart.numberOfItems();
  }

  /**
   * Gets item id.
   *
   * @param itemsCart the items cart
   * @param operationName the operation name
   * @return the item id
   */
  protected int getItemId(ItemsCart itemsCart, String operationName) {
    List<Item> listOfItems = itemsCart.getList();
    String[] itemsInfo = new String[listOfItems.size()];
    for (int i = 0; i < listOfItems.size(); i++) {
      itemsInfo[i] = listOfItems.get(i).getInfo();
    }
    return PlayerConsole.readMenuChoice(
        "chose one of the items to " + operationName + ": ", itemsInfo);
  }

  /**
   * Check item cart validation boolean.
   *
   * @param itemsCart the items cart
   * @param operation the operation
   * @return the boolean
   */
  protected boolean checkItemCartValidation(ItemsCart itemsCart, String operation) {
    if (isItemCartContainsItems(itemsCart)) {
      itemId = getItemId(itemsCart, operation) - 1;
      if (!isItemIdInItemCart(itemsCart, itemId)) {
        PlayerConsole.display("The entered Id not in the list!,try again!");
        return false;
      }
    } else {
      PlayerConsole.display("Cart Is empty, you can't " + operation);
      return false;
    }
    return true;
  }

  /** Returns the needed components to complete the trad operation. */
  @Override
  public String toString() {
    return "Trade{"
        + "player="
        + player
        + ", seller="
        + seller
        + ", itemId="
        + itemId
        + ", item="
        + item
        + '}';
  }
}
