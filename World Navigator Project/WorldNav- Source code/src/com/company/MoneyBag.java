/*
 * World Navigator - file MoneyBag.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */

package com.company;

import java.util.Objects;

public class MoneyBag implements Item, Comparable<MoneyBag> {
  private int amountOfMoney;
  private int price;

  public MoneyBag(int amountOfMoney, int price) {
    this.amountOfMoney = amountOfMoney;
    this.price = price;
  }

  @Override
  public int getPrice() {
    return price;
  }

  public int getAmountOfMoney() {
    return amountOfMoney;
  }

  @Override
  public String getItemName() {
    return "MoneyBag";
  }

  @Override
  public String getInfo() {
    return "( MoneyBag: " + getAmountOfMoney() + ", it's price: " + getPrice() + "$)";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MoneyBag moneyBag = (MoneyBag) o;
    return amountOfMoney == moneyBag.amountOfMoney && price == moneyBag.price;
  }

  @Override
  public int hashCode() {
    return Objects.hash(amountOfMoney, price);
  }

  /**
   * Returns the string representation of this MoneyBag. The string consists whose format is
   * "MoneyBag{amountOfMoney=XXX, price=YYY}" where XXX is the amount of money inside the money bag,
   * YYY is the price of the bag if you want to buy it from the seller.
   */
  @Override
  public String toString() {
    return "MoneyBag{" + "amountOfMoney=" + amountOfMoney + ", price=" + price + '}';
  }

  @Override
  public int compareTo(MoneyBag o) {
    if (o == null) {
      throw new NullPointerException();
    }
    int result = Integer.compare(amountOfMoney, o.amountOfMoney);
    if (result != 0) {
      return result;
    }
    return Integer.compare(price, o.price);
  }
}
