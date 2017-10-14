package com.webcheckers.appl;

import com.webcheckers.model.StartGame;

/**
  * Each player is initialized and each one can make moves.
  */

public class Player{

  public enum Color {RED, BLACK}
  public Color playerColor;
  ///private StartGame game;

  //private final GameBoard gameBoard;

  public Player(Color color){
    this.playerColor = color;
  }
}
