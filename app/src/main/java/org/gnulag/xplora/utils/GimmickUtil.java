package org.gnulag.xplora.utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * GimmickUtil
 */
public class GimmickUtil {
    public static void checkGimmick(ArrayList<String> combinedOutput) {
        // Cek apakah ada hasil pencarian yang mengandung "suit"
        for (String item : combinedOutput) {
            if (item.toLowerCase().contains("suit")) {
                // Jika ada, tambahkan gimmick suit
                combinedOutput.add(0, suit());
                return; // Hanya satu gimmick boleh ditambahkan, sehingga kita keluar dari loop
            }
        }

        // Cek apakah ada hasil pencarian yang mengandung "acak" atau "random"
        for (String item : combinedOutput) {
            if (item.toLowerCase().contains("acak") || item.toLowerCase().contains("random")) {
                // Jika ada, tambahkan gimmick randomGimmick
                combinedOutput.add(0, randomGimmick());
                return; // Hanya satu gimmick boleh ditambahkan, sehingga kita keluar dari loop
            }
        }
    }

    private static String randomGimmick() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        return "Random Number: " + randomNumber;
    }

    private static String suit() {
        // Implementasi gimmick suit, menghasilkan string acak
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        Random random = new Random();
        int randomIndex = random.nextInt(suits.length);
        return "Suit gimmick: " + suits[randomIndex];
    }
}
