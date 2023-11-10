package org.gnulag.xplora.utils;

public class RandomGimmick<K, V> implements GimmickAction<K, V> {
    @Override
    public V gimmick(K key, V value) {
        // Implement the gimmick for "random" node
        String randomNumbers = "";
        java.util.Random random = new java.util.Random();

        for (int i = 0; i < 10; i++) {
            int randomNumber = random.nextInt(10);
            randomNumbers += randomNumber;
        }

        value = (V) randomNumbers;
        return value;
    }
}

