package org.gnulag.xplora.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class GameGimmick<K> implements GimmickAction<K> {
  @Override
  public String gimmick(K key) {
    // Implement the gimmick for "kertas," "gunting," "batu," "rock," "paper,"
    // and "scissor" nodes
    List<String> choices =
    Arrays.asList("rock", "paper", "scissor");
    Random random = new Random();
    boolean isIndonesian = key.toString().equals("kertas") || key.toString().equals("gunting") || key.toString().equals("batu");
    String playerChoice = key.toString().equals("kertas") ? "paper" : key.toString().equals("gunting") ? "scissor" : key.toString().equals("batu") ? "rock" : key.toString();
    String computerChoice = choices.get(random.nextInt(choices.size()));
    String result = "";
    String matchResult = "";
    if (playerChoice.equals(computerChoice)) {
        matchResult = "draw";
    } else if (playerChoice.equals("rock")) {
        if (computerChoice.equals("paper")) {
            matchResult = "lose";
        } else {
            matchResult = "win";
        }
    } else if (playerChoice.equals("paper")) {
        if (computerChoice.equals("scissor")) {
            matchResult = "lose";
        } else {
            matchResult = "win";
        }
    } else if (playerChoice.equals("scissor")) {
        if (computerChoice.equals("rock")) {
            matchResult = "lose";
        } else {
            matchResult = "win";
        }
    }
    if (isIndonesian) {
        playerChoice = playerChoice.equals("rock") ? "batu" : playerChoice.equals("paper") ? "kertas" : "gunting";
        computerChoice = computerChoice.equals("rock") ? "batu" : computerChoice.equals("paper") ? "kertas" : "gunting";
        matchResult = matchResult.equals("draw") ? "seri" : matchResult.equals("win") ? "menang" : "kalah";
    }
    if (matchResult.equals("draw")|| matchResult.equals("seri")) {
        result = playerChoice + " vs " + computerChoice + ": " + matchResult;
    } else if (matchResult.equals("win") || matchResult.equals("menang")) {
        result = playerChoice + " vs " + computerChoice + ": " + matchResult;
    } else {
        result = playerChoice + " vs " + computerChoice + ": " + matchResult;
    }
    return result;
  }
}
