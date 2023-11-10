package org.gnulag.xplora.utils;
import org.gnulag.xplora.models.Node;

public class GameGimmick<K, V> implements GimmickAction<K, V> {
    @Override
    public void gimmick(Node<K, V> node) {
        // Implement the gimmick for "kertas," "gunting," "batu," "rock," "paper," and "scissor" nodes
        String[] options = {"rock", "paper", "scissor"};
        java.util.Random random = new java.util.Random();
        int index = random.nextInt(options.length);
        node.value = (V) options[index];
    }
}

