/*
 * World Navigator - file LockedFurniture.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

public interface LockedFurniture extends CheckableFurniture {
  public boolean isLooked();

  public String getKeyName();

  public int getKeyPrice();

  public void lockFurniture(Key key);

  public void unLockFurniture(Key key);
}
