/*
 * World Navigator - file Chest.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */

package com.company;

import java.util.Objects;

public class Chest implements LockedFurniture, Comparable<Chest> {
  private final Key key;
  private boolean isLooked;
  private ItemsCart itemsCart;

  public Chest(Key key, boolean isLooked, ItemsCart itemsCart) {
    this.key = new Key(key.getItemName(), key.getPrice());
    this.isLooked = isLooked;
    this.itemsCart = itemsCart.getCopy();
  }

  public Chest(FurnitureBuilder furnitureBuilder) {
    this.key = new Key(furnitureBuilder.getKeyName(), furnitureBuilder.getKeyPrice());
    this.isLooked = furnitureBuilder.isLocked();
    this.itemsCart = furnitureBuilder.getItemsCart();
  }

  @Override
  public String getIdentification() {
    return "chest";
  }

  @Override
  public boolean isLooked() {
    return isLooked;
  }

  @Override
  public ItemsCart checkFurniture() {
    if (isLooked) {
      System.out.println("“chest is locked" + getKeyName() + "key is needed to unlock");
      return new ItemsCart();
    } else {
      return itemsCart;
    }
  }

  @Override
  public String getKeyName() {
    return key.getItemName();
  }

  @Override
  public int getKeyPrice() {
    return key.getPrice();
  }

  @Override
  public void lockFurniture(Key key) {
    if (key.getItemName().equals(getKeyName())) {
      isLooked = true;
      System.out.println("locked");
    } else {
      System.out.println("You can't use this key for this chest");
    }
  }

  @Override
  public void unLockFurniture(Key key) {
    if (key.getItemName().equals(getKeyName())) {
      isLooked = false;
      System.out.println("Unlocked");
    } else {
      System.out.println("You can't use this key for this chest");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Chest chest = (Chest) o;
    return isLooked == chest.isLooked && key.equals(chest.key) && itemsCart.equals(chest.itemsCart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, isLooked, itemsCart);
  }

  /**
   * Returns a brief description for all of key, whiter this chest is looked or not, and the content
   * of item cart.
   */
  @Override
  public String toString() {
    return "Chest{" + "key=" + key + ", isLooked=" + isLooked + ", itemsCart=" + itemsCart + '}';
  }

  @Override
  public int compareTo(Chest o) {
    if (o == null) {
      throw new NullPointerException();
    }
    int result = Boolean.compare(isLooked, o.isLooked);
    if (result != 0) {
      return result;
    }
    return Integer.compare(itemsCart.numberOfItems(), o.itemsCart.numberOfItems());
  }
}
