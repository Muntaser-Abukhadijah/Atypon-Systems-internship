/*
 * World Navigator - file Key.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.util.Objects;

public class Key implements Item, Comparable<Key> {
  private final String keyName;
  private final int price;

  public Key(String keyName, int price) {
    this.keyName = keyName;
    this.price = price;
  }

  @Override
  public int getPrice() {
    return price;
  }

  @Override
  public String getInfo() {
    return "( Key name: " + getItemName() + ", Key price: " + getPrice() + "$)";
  }

  @Override
  public String getItemName() {
    return keyName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Key key = (Key) o;
    return price == key.price && keyName.equals(key.keyName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyName, price);
  }

  /**
   * Returns the string representation of this Key. The string consists whose format is
   * "Key{keyName=XXX', price=YYY}" where XXX is the key name, YYY is the key price.
   */
  @Override
  public String toString() {
    return "Key{" + "keyName='" + keyName + '\'' + ", price=" + price + '}';
  }

  @Override
  public int compareTo(Key o) {
    if (o == null) {
      throw new NullPointerException();
    }
    int result = Integer.compare(price, o.price);
    if (result != 0) {
      return result;
    }
    return keyName.compareToIgnoreCase(o.keyName);
  }
}
