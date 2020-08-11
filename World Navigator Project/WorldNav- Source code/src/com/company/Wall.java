/*
 * World Navigator - file Wall.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.util.Objects;

public class Wall implements Comparable<Wall> {
  private final String direction;
  private final Furniture furniture;

  public Wall(String direction, Furniture furniture) {
    this.direction = direction;
    this.furniture = furniture;
  }

  public String getDirection() {
    return direction;
  }

  public Furniture getFurniture() {
    return furniture;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Wall wall = (Wall) o;
    return direction.equals(wall.direction) && furniture.equals(wall.furniture);
  }

  @Override
  public int hashCode() {
    return Objects.hash(direction, furniture);
  }

  /**
   * Returns the string representation of this Wall. The string consists whose format is
   * "Wall{direction=XXX', furniture=YYY}" where XXX is the orientation of this wall, YYY is
   * description of the furniture that this wall carry.
   */
  @Override
  public String toString() {
    return "Wall{" + "direction='" + direction + '\'' + ", furniture=" + furniture + '}';
  }

  @Override
  public int compareTo(Wall o) {
    return direction.compareToIgnoreCase(o.direction);
  }
}
