package com.webcheckers.model;

public class Square {
  public enum Playable {YES, NO};
  private Playable play;

  public Square (Playable play) {
    this.play = play;
  }


  public String toString()
  {
    if (play == Playable.YES)
    {
      return "@";
    }
    else
    {
      return "x";
    }
  }
}
