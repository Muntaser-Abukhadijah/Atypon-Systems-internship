/*
 * World Navigator - file ItemsCart.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.util.*;
import java.util.function.Consumer;

public class ItemsCart<E> implements Comparable<ItemsCart>, Iterable<E> {

  private List<E> itemCart = new ArrayList<E>();

  public ItemsCart() {}

  public ItemsCart(List<E> itemCart) {
    this.itemCart = itemCart;
  }

  public List<E> getList() {
    return itemCart;
  }

  public void addItem(E item) {
    itemCart.add(item);
  }

  public void removeItem(E item) {
    itemCart.remove(item);
  }

  public void removeItem(int index) {
    itemCart.remove(index);
  }

  public int numberOfItems() {
    return itemCart.size();
  }

  public E getItem(int i) {
    return itemCart.get(i);
  }

  public ItemsCart getKeysList() {
    ItemsCart itemsCart = new ItemsCart();
    for (int i = 0; i < numberOfItems(); i++) {
      if (getItem(i) instanceof Key) {
        itemsCart.addItem(getItem(i));
      }
    }
    return itemsCart;
  }

  public void printItems(){
    for(int i=0;i<this.numberOfItems();i++){
      PlayerConsole.display(((Item)this.getItem(i)).getInfo());
    }
  }

  public ItemsCart<E> getCopy() {
    return new ItemsCart(itemCart);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemsCart itemsCart = (ItemsCart) o;
    return itemCart.equals(itemsCart.itemCart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemCart);
  }

  /** Returns a the content of the itemCart by there description. */
  @Override
  public String toString() {
    return "ItemsCart{" + "itemCart=" + itemCart + '}';
  }

  @Override
  public int compareTo(ItemsCart o) {
    if (o == null) {
      throw new NullPointerException();
    }
    return Integer.compare(itemCart.size(), o.itemCart.size());
  }

  @Override
  public Iterator<E> iterator() {
    return itemCart.iterator();
  }
}
