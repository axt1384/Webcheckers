package com.webcheckers.appl;

import com.webcheckers.model.StartGame;

/**
  * Each player is initialized and each one can make moves.
  */

public class Player{

  public enum Color {RED, BLACK}

  private StartGame game;

  private final GameBoard gameBoard;

  /**
   * Construct a new player but wait for him/her to start a game
   * @param gameBoard-the game board that has statewide responsibilities
   */
  Player(GameBoard gameBoard){
    this.game=null;
    this.gameBoard=gameBoard;
  }

  public synchronized StartGame currentGame(){
    if(game==null){
      game=gameBoard.getGame();
    }
    return game;
  }



}
