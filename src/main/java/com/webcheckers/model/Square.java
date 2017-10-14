package com.webcheckers.model;

import com.webcheckers.appl.Player;

public class Square {
  public enum Playable {YES, NO};
  private Playable play;
  private Player.Color color;

  public Square (Playable play, Player.Color color) {
    this.play = play;
    this.color = color;
  }


  public String toString() {
    if (play == Playable.YES) {
      if (color == Player.Color.BLACK) {
        return "b";
      }
      else if (color == Player.Color.RED) {
        return "r";
      }
      else {
        return "@";
      }
    }
    else {
      return "x";
    }
  }
}
