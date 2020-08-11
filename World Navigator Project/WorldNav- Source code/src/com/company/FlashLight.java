/*
 * World Navigator - file FlashLight.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.util.Objects;

public class FlashLight implements Item, Comparable<FlashLight> {
  private final int price;
  private boolean lightState;

  public FlashLight(int price) {
    this.price = price;
    this.lightState = false;
  }

  public boolean getLightState() {
    return lightState;
  }

  public void flipSwitch() {
    lightState = !lightState;
  }

  @Override
  public int getPrice() {
    return price;
  }

  @Override
  public String getItemName() {
    return "FlashLight";
  }

  @Override
  public String getInfo() {
    return "( FlashLight name: " + getItemName() + ", FlashLight price: " + getPrice() + "$)";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FlashLight that = (FlashLight) o;
    return price == that.price && lightState == that.lightState;
  }

  @Override
  public int hashCode() {
    return Objects.hash(price, lightState);
  }

  /** Returns a brief description for all of flashLight price, and its light state. */
  @Override
  public String toString() {
    return "FlashLight{" + "price=" + price + ", lightState=" + lightState + '}';
  }

  @Override
  public int compareTo(FlashLight o) {
    if (o == null) {
      throw new NullPointerException();
    }
    int result = Boolean.compare(lightState, o.lightState);
    if (result != 0) {
      return result;
    }
    return Integer.compare(price, o.price);
  }
}
