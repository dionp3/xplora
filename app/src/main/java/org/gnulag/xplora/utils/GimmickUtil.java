package org.gnulag.xplora.utils;

import java.util.ArrayList;
import java.util.Random;

/** GimmickUtil */
public class GimmickUtil {
  public static String checkGimmick(String searchParam) {
    if (searchParam.toLowerCase().contains("suit")) {
      return suit();
    } else if (searchParam.toLowerCase().contains("acak") || searchParam.toLowerCase().contains("random")) {
      return randomGimmick();
    }
    return "";
  }

  private static String randomGimmick() {
    Random random = new Random();
    int randomNumber = random.nextInt(100);
    return "Random Number: " + randomNumber;
  }

  private static String suit() {
    String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
    Random random = new Random();
    int randomIndex = random.nextInt(suits.length);
    return "Suit gimmick: " + suits[randomIndex];
  }
}
