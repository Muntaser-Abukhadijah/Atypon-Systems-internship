package com.company;

public class PlayerDirectionRotate {
  private String rotationDirection;
  private String currentOrientation;

  public PlayerDirectionRotate(String rotationDirection, String currentOrientation) {
    this.rotationDirection = rotationDirection;
    this.currentOrientation = currentOrientation;
  }

  public String getOrientation() {
    return getNextDirection();
  }

  private String getNextDirection() {
    String[] directions = {"north", "east", "south", "west"};
    int index = 0;
    for (int i = 0; i < directions.length; i++) {
      if (currentOrientation.equalsIgnoreCase(directions[i])) {
        index = i;
        break;
      }
    }
    if (rotationDirection.equals("left")) {
      index = (((index - 1) % 4) + 4) % 4;
    } else {
      index = (((index + 1) % 4) + 4) % 4;
    }
    return directions[index];
  }

  /** Returns in which direction player looks and to which side wants to rotate. */
  @Override
  public String toString() {
    return "PlayerDirectionRotate{"
        + "rotationDirection='"
        + rotationDirection
        + '\''
        + ", currentOrientation='"
        + currentOrientation
        + '\''
        + '}';
  }
}
