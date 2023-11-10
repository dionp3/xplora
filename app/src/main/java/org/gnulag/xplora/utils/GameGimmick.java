package org.gnulag.xplora.utils;

public class GameGimmick<K, V> implements GimmickAction<K, V> {
  @Override
  public V gimmick(K key, V value) {
    // Implement the gimmick for "kertas," "gunting," "batu," "rock," "paper,"
    // and "scissor" nodes
    String[] options = {"rock", "paper", "scissor"};
    java.util.Random random = new java.util.Random();
    int index = random.nextInt(options.length);
    value = (V) options[index];
    return value;
  }
}
