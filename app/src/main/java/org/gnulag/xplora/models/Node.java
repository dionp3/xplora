package org.gnulag.xplora.models;
import org.gnulag.xplora.utils.GimmickAction;

public class Node<K, V> {
    public K key;
    public V value;
    public Node<K, V> parent;
    public Node<K, V> left;
    public Node<K, V> right;
    public boolean isRed;
    public GimmickAction<K, V> gimmick;
}
