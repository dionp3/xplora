package org.gnulag.xplora.utils;
import org.gnulag.xplora.models.Node;

public class RandomGimmick<K, V> implements GimmickAction<K, V> {
    @Override
    public void gimmick(Node<K, V> node) {
        // Implement the gimmick for "random" node
        String randomNumbers = "";
        java.util.Random random = new java.util.Random();

        for (int i = 0; i < 10; i++) {
            int randomNumber = random.nextInt(10);
            randomNumbers += randomNumber;
        }

        node.value = (V) randomNumbers;
    }
}

