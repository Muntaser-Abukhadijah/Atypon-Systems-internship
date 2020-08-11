package com.company;

import java.util.Objects;

public class EmptyWall implements Furniture, Comparable<EmptyWall> {

  private final String color;

  public EmptyWall(String color) {
    this.color = color;
  }

  public EmptyWall(FurnitureBuilder furnitureBuilder) {
    this.color = furnitureBuilder.getWallColor();
  }

  @Override
  public String getIdentification() {
    return color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EmptyWall emptyWall = (EmptyWall) o;
    return color.equals(emptyWall.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color);
  }

  /** Returns the color of the wall. */
  @Override
  public String toString() {
    return "EmptyWall{" + "color='" + color + '\'' + '}';
  }

  @Override
  public int compareTo(EmptyWall o) {
    if (o == null) {
      throw new NullPointerException();
    }
    return color.compareToIgnoreCase(o.color);
  }
}
