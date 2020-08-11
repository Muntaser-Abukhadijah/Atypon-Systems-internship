package com.company;

public class PlayerMovement {

  private String playerOrientation;
  private Room room;
  private String inputDirection;
  private Player player;

  public PlayerMovement(Player player, Room room, String inputDirection) {
    this.player = player;
    this.playerOrientation = player.getDirection();
    // In case backward move, the orientation of the player will be flipped virtually.
    this.playerOrientation = fixOrientation(inputDirection);
    this.room = room;
    this.inputDirection = inputDirection;
  }

  public Pair move() {
    Furniture furniture = room.getFurniture(playerOrientation);
    if (checkIfDoor(furniture)) {
      if (!checkIfDoorLooked((Door) furniture)) {
        if (checkIfDoorOpen((Door) furniture)) {
          return getNextRoomPosition();
        } else {
          PlayerConsole.display("This Door is closed");
        }
      } else {
        PlayerConsole.display("This Door is Looked");
      }
    } else {
      PlayerConsole.display("That is not a Door");
    }
    return new Pair(-1, -1);
  }

  private boolean checkIfDoorOpen(Door door) {
    return door.isOpen();
  }

  private boolean checkIfDoor(Furniture furniture) {
    if (furniture.getIdentification().equals("door")) {
      return true;
    }
    return false;
  }

  private boolean checkIfDoorLooked(Door door) {
    return door.isLooked();
  }

  private Pair getNextRoomPosition() {
    if (playerOrientation.equals("north")) {
      return new Pair(-1, 0);
    } else if (playerOrientation.equals("east")) {
      return new Pair(0, 1);
    } else if (playerOrientation.equals("south")) {
      return new Pair(1, 0);
    } else {
      return new Pair(0, -1);
    }
  }

  private String fixOrientation(String inputDirection) {
    if (inputDirection.equals("backward")) {
      if (playerOrientation.toLowerCase().equals("north")) {
        return "south";
      } else if (playerOrientation.toLowerCase().equals("south")) {
        return "north";
      } else if (playerOrientation.toLowerCase().equals("west")) {
        return "east";
      } else {
        return "west";
      }
    } else {
      return playerOrientation;
    }
  }

  /** Returns player orientation, and his destination. */
  @Override
  public String toString() {
    return "PlayerMovement{"
        + "playerOrientation='"
        + playerOrientation
        + '\''
        + ", room="
        + room
        + ", inputDirection='"
        + inputDirection
        + '\''
        + '}';
  }
}
