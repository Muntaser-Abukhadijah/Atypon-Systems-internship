/*
 * World Navigator - file Door.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */

package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Door implements LockedFurniture, Comparable<Door> {
  private final Key key;
  private boolean isLooked;
  private boolean isOpen;

  public Door(Key key, boolean isLooked, boolean isOpen) {
    this.key = new Key(key.getItemName(), key.getPrice());
    this.isLooked = isLooked;
    this.isOpen = isOpen;
  }

  public Door(FurnitureBuilder furnitureBuilder) {
    this.key = new Key(furnitureBuilder.getKeyName(), furnitureBuilder.getKeyPrice());
    this.isLooked = furnitureBuilder.isLocked();
    this.isOpen = furnitureBuilder.isOpen();
  }

  @Override
  public String getIdentification() {
    return "door";
  }

  @Override
  public boolean isLooked() {
    return isLooked;
  }

  @Override
  public ItemsCart checkFurniture() {
    if (isLooked) {
      System.out.println("Door is locked" + getKeyName() + "key is needed to unlock");
    } else if (isOpen()) {
      System.out.println("Door is open");
    }else{
      System.out.println("Door is closed");
    }
    return new ItemsCart(Collections.emptyList());
  }

  public boolean isOpen() {
    return isOpen;
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
      isOpen = false;
      System.out.println("Locked");
    } else {
      System.out.println("You can't use this key for this door");
    }
  }

  @Override
  public void unLockFurniture(Key key) {
    if (key.getItemName().equals(getKeyName())) {
      isLooked = false;
      System.out.println("Unlocked");
    } else {
      System.out.println("You can't use this key for this door");
    }
  }

  public void openDoor() {
    if (isOpen) {
      PlayerConsole.display("It is already open");
    } else if (isLooked) {
      PlayerConsole.display(getKeyName() + " key required to unlock");
    } else {
      isOpen = true;
      PlayerConsole.display("It is open now");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Door door = (Door) o;
    return isLooked == door.isLooked && isOpen == door.isOpen && key.equals(door.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, isLooked, isOpen);
  }

  /**
   * Returns a brief description for all of key, whiter this door is looked or not, and if it's open
   * or not.
   */
  @Override
  public String toString() {
    return "Door{" + "key=" + key + ", isLooked=" + isLooked + ", isOpen=" + isOpen + '}';
  }

  @Override
  public int compareTo(Door o) {
    if (o == null) {
      throw new NullPointerException();
    }
    int result = Boolean.compare(isLooked, o.isLooked);
    if (result != 0) {
      return result;
    }
    return Boolean.compare(isOpen, o.isOpen);
  }
}
