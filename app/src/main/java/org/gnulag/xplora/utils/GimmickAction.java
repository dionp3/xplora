package org.gnulag.xplora.utils;

public interface GimmickAction<K, V> {
    V gimmick(K key, V value);
}

