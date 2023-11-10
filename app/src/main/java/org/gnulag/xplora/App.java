package org.gnulag.xplora;

import org.gnulag.xplora.models.RedBlackTreeMap;
import org.gnulag.xplora.utils.GameGimmick;
import org.gnulag.xplora.utils.JSONUtil;
import org.gnulag.xplora.utils.PrintsUtil;
import org.gnulag.xplora.utils.RandomGimmick;

class App {
  public static void main(String[] args) {
    // Create a Red-Black Tree Map
    RedBlackTreeMap<String, String> rbTree = new RedBlackTreeMap<>();

    // Insert nodes with different gimmicks
    rbTree.insert("random", null, new RandomGimmick<>());
    rbTree.insert("acak", null, new RandomGimmick<>());
    rbTree.insert("rock", null, new GameGimmick<>());
    rbTree.insert("paper", null, new GameGimmick<>());
    rbTree.insert("scissor", null, new GameGimmick<>());
    rbTree.insert("batu", null, new GameGimmick<>());
    rbTree.insert("gunting", null, new GameGimmick<>());
    rbTree.insert("kertas", null, new GameGimmick<>());

    // Load JSON data
    JSONUtil.loadJsonData(rbTree, "/data.json");

    String searchParam = "kertas";
    PrintsUtil.printRedBlackTreeResults(rbTree, searchParam);
  }
}
