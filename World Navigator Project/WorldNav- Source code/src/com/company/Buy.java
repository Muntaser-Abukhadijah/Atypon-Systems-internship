/*
 * World Navigator - file Buy.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */

package com.company;

public class Buy extends Trade {

  /**
   * Instantiates a new Buy.
   *
   * @param player the player
   * @param seller the seller
   */
  public Buy(Player player, Seller seller) {
    super(player, seller);
  }

  public void performTrade() {

    if (checkItemCartValidation(seller.getItemCart(), "Buy")) {
      item = (Item) seller.getItemCart().getItem(itemId);
      if (checkItemPrice(item)) {
        player.getItemCart().addItem(item);
        player.payGold(item);
        seller.getItemCart().removeItem(item);
        PlayerConsole.display("Done");
      } else {
        PlayerConsole.display("Return when you have enough gold");
      }
    }
  }

  /**
   * Returns true if the player has enough money to buy the item otherwise returns false
   *
   * @param item the item to buy
   * @return boolean depends on the player amount of money
   */
  private boolean checkItemPrice(Item item) {
    if (player.getAmountOfGold() >= item.getPrice()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns a brief description for all of player, seller, itemID(The number of the item to buy).
   * and the item it self.
   */
  @Override
  public String toString() {
    return "Buy{"
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
