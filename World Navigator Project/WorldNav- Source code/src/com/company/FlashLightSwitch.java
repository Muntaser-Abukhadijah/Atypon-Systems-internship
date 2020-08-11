package com.company;

public class FlashLightSwitch {
  private Player player;

  public FlashLightSwitch(Player player) {
    this.player = player;
  }

  public void switchFlashLight() {
    ItemsCart<Item> itemsCart = player.getItemCart();
    for (Item item : itemsCart) {
      if (item instanceof FlashLight) {
        ((FlashLight) item).flipSwitch();
      }
    }
  }

  public boolean isFlashLightOn() {
    boolean flashLightState = false;
    ItemsCart<Item> itemsCart = player.getItemCart();
    for (Item item : itemsCart) {
      if (item instanceof FlashLight) {
        flashLightState |= ((FlashLight) item).getLightState();
      }
    }
    return flashLightState;
  }

  /** Returns a brief description for the player who wants to use the flash light. */
  @Override
  public String toString() {
    return "FlashLightSwitch{" + "player=" + player + '}';
  }
}
