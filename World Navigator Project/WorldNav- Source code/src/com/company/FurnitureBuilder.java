package com.company;

import java.util.HashMap;

public class FurnitureBuilder {

  private boolean isLocked;
  private boolean isOpen;
  private String wallColor;
  private ItemsCart<Item> itemsCart = new ItemsCart();
  private ItemsCart<Item> itemPrices = new ItemsCart();
  private Key key;

  public FurnitureBuilder key(Key key) {
    this.key = key;
    return this;
  }

  public FurnitureBuilder itemsPrices(ItemsCart itemsCart) {
    this.itemPrices = itemsCart;
    return this;
  }

  public FurnitureBuilder itemsCart(ItemsCart itemsCart) {
    this.itemsCart = itemsCart;
    return this;
  }

  public FurnitureBuilder open(boolean isOpen) {
    this.isOpen = isOpen;
    return this;
  }

  public FurnitureBuilder lock(boolean locked) {
    isLocked = locked;
    return this;
  }

  public FurnitureBuilder wallColor(String wallColor) {
    this.wallColor = wallColor;
    return this;
  }

  public boolean isLocked() {
    return isLocked;
  }

  public String getKeyName() {
    return key.getItemName();
  }

  public int getKeyPrice() {
    return key.getPrice();
  }

  public boolean isOpen() {
    return isOpen;
  }

  public String getWallColor() {
    return wallColor;
  }

  public ItemsCart<Item> getItemsCart() {
    return itemsCart;
  }

  public ItemsCart<Item> getItemPrices(){
    return itemPrices;
  }

  public Item getKey() {
    return key;
  }

  public Furniture build(String furnitureName) {
    switch (furnitureName) {
      case "chest":
        return new Chest(this);
      case "door":
        return new Door(this);
      case "painting":
        return new Painting(this);
      case "mirror":
        return new Mirror(this);
      case "emptyWall":
        return new EmptyWall(this);
      case "seller":
        return new Seller(this);
      default:
        System.out.println("Something went wrong");
    }
    return new Chest(new Key("string", 22), false, new ItemsCart());
  }
}
