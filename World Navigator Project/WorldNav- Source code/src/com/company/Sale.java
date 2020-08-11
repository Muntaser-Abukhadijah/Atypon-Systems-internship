/*
 * World Navigator - file Sell.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.util.List;

public class Sale extends Trade {

  private int price;

  public Sale(Player player, Seller seller) {
    super(player, seller);
  }

//  private void
//      getItemPriceList() { // Think it's name should be change, because its get but we don't return
//    // anything
//    String[] pricesList = seller.listPrices(); // so it should be named as printitemPriceList
//    for (String itemPrice : pricesList) {
//      PlayerConsole.display(itemPrice);
//    }
//    PlayerConsole.display("\n\n");
//  }
//
  private void getItemPriceList(){
    PlayerConsole.display("Item prices: ");
    List<Item> itemPrices = seller.getItemPrices().getList();
    for (int i = 0; i < itemPrices.size(); i++) {
      PlayerConsole.display(itemPrices.get(i).getInfo());
    }
    PlayerConsole.display("");
  }

  public void performTrade() {
    getItemPriceList();
    if (checkItemCartValidation(player.getItemCart(), "Sell")) {
      item = (Item) player.getItemCart().getItem(itemId);
      player.getItemCart().removeItem(item);
      price = seller.buyItem(itemId,item);
      player.earnGold(new Key("", price));
      PlayerConsole.display("Done");
    }
  }

  /** Returns the price of the item to be sold. */
  @Override
  public String toString() {
    return "Sale{" + "price=" + price + '}';
  }
}
