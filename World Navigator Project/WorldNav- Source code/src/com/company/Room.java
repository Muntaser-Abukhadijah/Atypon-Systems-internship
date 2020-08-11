/*
 * World Navigator - file Room.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room implements Comparable<Room> {
  private int rowPosition;
  private int columnPosition;
  private List<Wall> walls;
  private final boolean switchLight;
  private boolean lightState;

  public Room(
      int rowPosition,
      int columnPosition,
      List<Wall> walls,
      boolean switchLight,
      boolean lightState) {
    this.rowPosition = rowPosition;
    this.columnPosition = columnPosition;
    this.walls = walls;
    this.switchLight = switchLight;
    this.lightState = lightState;
  }

  public Furniture getFurniture(Player player) {
      for (int i = 0; i < walls.size(); i++) {
        if (walls.get(i).getDirection().equalsIgnoreCase(player.getDirection())) {
          return walls.get(i).getFurniture();
        }
      }
    return new EmptyWall("It is dark here!");
  }

  public Furniture getFurniture(String oppositeOrientation) {
      for (int i = 0; i < walls.size(); i++) {
        if (walls.get(i).getDirection().equalsIgnoreCase(oppositeOrientation)) {
          return walls.get(i).getFurniture();
        }
    }
    return new EmptyWall("It is dark here!");
  }

  private boolean isRoomLit(Player player) {
    if (lightState || player.isFlashLightOn()) {
      return true;
    }
    return false;
  }


  public boolean isLightStateOn(){
    return lightState;
  }

  public void switchLight() {
    if (lightState) {
      lightSwitchOff();
    } else {
      lightSwitchOn();
    }
  }

  private void lightSwitchOn() {
    if (switchLight) {
      lightState = true;
      PlayerConsole.display("Light is on now!!");
    } else {
      PlayerConsole.display("There is no switchLight, use flashLight");
    }
  }

  private void lightSwitchOff() {
    if (switchLight) {
      lightState = false;
      PlayerConsole.display("Light is off now");
    } else {
      PlayerConsole.display("There is no switchLight, use flashLight");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Room room = (Room) o;
    return rowPosition == room.rowPosition
        && columnPosition == room.columnPosition
        && switchLight == room.switchLight
        && lightState == room.lightState
        && walls.equals(room.walls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rowPosition, columnPosition, walls, switchLight, lightState);
  }

  /**
   * Returns the string representation of this Room. The string consists whose format is
   * "Room{rowPosition=XXX', columnPosition=YYY', Walls=ZZZ, switchLight=TTT, lightState=NNN }"
   * where XXX is the X position of the room inside the map, YYY is Y position, ZZZ is the
   * description of the walls the construct this room, TTT shows if there is switch light or not.
   * NNN shows whither the light is on or off in this room.
   */
  @Override
  public String toString() {
    return "Room{"
        + "rowPosition="
        + rowPosition
        + ", columnPosition="
        + columnPosition
        + ", walls="
        + walls
        + ", switchLight="
        + switchLight
        + ", lightState="
        + lightState
        + '}';
  }

  @Override
  public int compareTo(Room o) {
    if (o == null) {
      throw new NullPointerException();
    }
    int result = Boolean.compare(switchLight, o.switchLight);
    if (result != 0) {
      return result;
    }
    result = Boolean.compare(lightState, o.lightState);
    if (result != 0) {
      return result;
    }
    return Integer.compare(walls.size(), o.walls.size());
  }
}
