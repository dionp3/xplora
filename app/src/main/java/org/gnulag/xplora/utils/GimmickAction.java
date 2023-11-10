package org.gnulag.xplora.utils;
import org.gnulag.xplora.models.Node;

public interface GimmickAction<K, V> {
    void gimmick(Node<K, V> node);
}

