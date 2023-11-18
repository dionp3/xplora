package org.gnulag.xplora.utils;

public class RandomGimmick<K> implements GimmickAction<K> {
    @Override
    public String gimmick(K key) {
        // Implement the gimmick for "random" node
        String randomNumbers = "";
        java.util.Random random = new java.util.Random();

        for (int i = 0; i < 10; i++) {
            int randomNumber = random.nextInt(10);
            randomNumbers += randomNumber;

            if (i < 9) {
                randomNumbers += ", ";
            }
        }
        if (key.toString().equals("random")) {
            return "10 random numbers: " + randomNumbers.toString();
        }
        return "10 angka acak: "+ randomNumbers.toString();
    }
}

