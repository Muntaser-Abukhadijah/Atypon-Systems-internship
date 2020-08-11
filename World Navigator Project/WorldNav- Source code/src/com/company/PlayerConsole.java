/*
 * World Navigator - file PlayerConsole.java
 *
 * copyright (c) 2020 - Muntaser A. Abukhadijah
 *
 */
package com.company;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class PlayerConsole {

  public static Scanner scanner = new Scanner(System.in);

  public PlayerConsole() {}

  public static void display(String message) {
    System.out.println(message);
  }

  public static int readMenuChoice(String prompt, String[] menu) {
    System.out.println();
    System.out.println(prompt);
    for (int i = 0; i < menu.length; i++) {
      System.out.println("Enter " + "\'" + ((i + 1) + "\'" + " to " + menu[i]));
    }
    System.out.println("Enter your choice : ");
    int choice = 100;

    try {
      choice = scanner.nextInt();
    } catch (Exception e) {
      scanner.next();
      System.out.println("Your input is wrong!, try again.");
    } finally {
      if (choice < 1 || choice > menu.length) {
        System.out.println("Your input is not within the options ID, try again!");
      }
      System.out.println("\n------------------------------------\n");
      return choice;
    }
  }

  /** Returns string that represent what this class do. */
  @Override
  public String toString() {
    return "PlayerConsole to display massages to user and take input from user";
  }
}
