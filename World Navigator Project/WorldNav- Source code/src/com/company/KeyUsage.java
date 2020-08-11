package com.company;

import java.util.Arrays;

public class KeyUsage {
  private Player player;
  private Room room;
  private ItemsCart<Item> keyList;
  private String[] keyNames;

  public KeyUsage(Player player, Room room) {
    this.player = player;
    this.room = room;
  }

  public void useKey() {
    if (isLockableFurniture()) {
      LockedFurniture lockedFurniture = (LockedFurniture) room.getFurniture(player);
      int keyId = getKeyId() - 1;
      if (checkKeyId(keyId)) {
        if (lockedFurniture.isLooked()) {
          lockedFurniture.unLockFurniture((Key) keyList.getItem(keyId));
        } else {
          lockedFurniture.lockFurniture((Key) keyList.getItem(keyId));
        }
      }
    } else {
      PlayerConsole.display("That is not a door/chest");
    }
  }

  private boolean isLockableFurniture() {
    return (room.getFurniture(player) instanceof LockedFurniture);
  }

  private boolean checkKeyId(int keyId) {
    if (keyId == -1) {
      PlayerConsole.display("You have no key to use!");
      return false;
    } else if (keyId < 0 || keyId >= keyList.numberOfItems()) {
      PlayerConsole.display("This key id not exist");
      return false;
    }
    return true;
  }

  private int getKeyId() {
    String[] keyNames = getKeyNames();
    int choice = -1;
    if (keyNames.length > 0) {
      choice = PlayerConsole.readMenuChoice("Choose which key to use : ", keyNames);
    }
    return choice;
  }

  private String[] getKeyNames() {
    keyList = getKeysList();
    keyNames = new String[keyList.numberOfItems()];
    for (int i = 0; i < keyList.numberOfItems(); i++) {
      keyNames[i] = keyList.getItem(i).getItemName();
    }
    return keyNames;
  }

  private ItemsCart getKeysList() {
    return player.getItemCart().getKeysList();
  }

  /**
   * Returns a the player who want to use key, with list of keys and names and the room that player
   * stands in.
   */
  @Override
  public String toString() {
    return "KeyUsage{"
        + "player="
        + player
        + ", room="
        + room
        + ", keyList="
        + keyList
        + ", keyNames="
        + Arrays.toString(keyNames)
        + '}';
  }
}
