/*
 * World Navigator - file Seller.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class Seller implements Furniture, Comparable<Seller> {

  private ItemsCart<Item> itemsCart;
  private final ItemsCart<Item>itemPrices;
 // private Map<String, Integer> itemPrices; // this one should be refactored.

//  public Seller(ItemsCart itemsCart, HashMap<String, Integer> itemPrices) {
//    this.itemsCart = itemsCart;
// //   this.itemPrices = itemPrices;
//  }

  public Seller(FurnitureBuilder furnitureBuilder) {
    this.itemsCart = furnitureBuilder.getItemsCart();
    this.itemPrices = furnitureBuilder.getItemPrices();

  }

  @Override
  public String getIdentification() {
    return "seller";
  }

  public String[] listPrices() {
    String[] priceList = new String[itemPrices.numberOfItems()];
    int i=0;
    for (Item item: itemPrices ) {
        priceList[i]= item.getInfo();
    }
    return priceList;
  }

  public int buyItem(int itemId, Item item) {
    int price = itemPriceToBuy(itemId);
    itemsCart.addItem(item);
    return price;
  }

  private int itemPriceToBuy(int itemId) {
   return itemPrices.getItem(itemId).getPrice();
  }

  public ItemsCart<Item> getItemCart() {
    return itemsCart.getCopy();
  }

  public ItemsCart<Item> getItemPrices(){
    return itemPrices.getCopy();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Seller seller = (Seller) o;
    return itemsCart.equals(seller.itemsCart) && itemPrices.equals(seller.itemPrices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemsCart, itemPrices);
  }

  /**
   * Returns the string representation of this Seller. The string consists whose format is
   * "Seller{itemsCart=XXX', itemPrices=YYY}" where XXX is the description of each item that seller
   * has YYY is Y each item and its price.
   */
  @Override
  public String toString() {
    return "Seller{" + "itemsCart=" + itemsCart + ", itemPrices=" + itemPrices + '}';
  }

  @Override
  public int compareTo(Seller o) {
    return itemsCart.compareTo(o.itemsCart);
  }
}
