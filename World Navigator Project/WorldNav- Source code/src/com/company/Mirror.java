/*
 * World Navigator - file Mirror.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.util.ArrayList;

public class Mirror implements CheckableFurniture, Comparable<Mirror> {

  private ItemsCart itemsCart;

  public Mirror(ItemsCart itemsCart) {
    this.itemsCart = new ItemsCart(new ArrayList<Item>());
    this.itemsCart = itemsCart;
  }

  public Mirror(FurnitureBuilder furnitureBuilder) {
    this.itemsCart = furnitureBuilder.getItemsCart();
  }

  @Override
  public String getIdentification() {
    System.out.println("You See a silhouette of you");
    return "mirror";
  }

  @Override
  public ItemsCart checkFurniture() {
    return itemsCart;
  }

  /**
   * Returns the string representation of this Mirror. The string consists whose format is
   * "Mirror{itemsCart=XXX'}" where XXX is the is the description of the each item inside the item
   * cart.
   */
  @Override
  public String toString() {
    return "Mirror{" + "itemsCart=" + itemsCart + '}';
  }

  @Override
  public int compareTo(Mirror o) {
    if (o == null) {
      throw new NullPointerException();
    }
    return itemsCart.compareTo(o.itemsCart);
  }
}
