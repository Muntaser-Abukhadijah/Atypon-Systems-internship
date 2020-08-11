package com.company;

public class Pair {
  private int x;
  private int y;

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Pair(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /** Returns a the values of the pair class. */
  @Override
  public String toString() {
    return "Pair{" + "x=" + x + ", y=" + y + '}';
  }
}
