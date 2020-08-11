/*
 * World Navigator - file Painting.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.util.Objects;

public class Painting implements CheckableFurniture, Comparable<Painting> {

  ItemsCart<Item> itemsCart;

  public Painting(ItemsCart itemsCart) {
    this.itemsCart = itemsCart;
  }

  public Painting(FurnitureBuilder furnitureBuilder) {
    this.itemsCart = furnitureBuilder.getItemsCart();
  }

  @Override
  public String getIdentification() {
    return "painting";
  }

  @Override
  public ItemsCart checkFurniture() {
    return itemsCart;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Painting painting = (Painting) o;
    return itemsCart.equals(painting.itemsCart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemsCart);
  }

  /**
   * Returns the string representation of this Painting. The string consists whose format is
   * "Painting{itemsCart=XXX}" where XXX is description of each item in the item cart.
   */
  @Override
  public String toString() {
    return "Painting{" + "itemsCart=" + itemsCart + '}';
  }

  @Override
  public int compareTo(Painting o) {
    if (o == null) {
      throw new NullPointerException();
    }
    return itemsCart.compareTo(o.itemsCart);
  }
}
