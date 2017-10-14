package com.webcheckers.appl;

import com.webcheckers.model.StartGame;

/**
  * Each player is initialized and each one can make moves.
  */

public class Player{

  public enum Color {RED, BLACK}

  public static Color playerColor;

  private StartGame game;

  private GameBoard gameBoard;

  /**
   * Construct a new player but wait for him/her to start a game
   * @param gameBoard-the game board that has statewide responsibilities
   */
  public Player(GameBoard gameBoard){
    if ( game.value == game.Decide.WAITING ) {
      new Player(Color.RED, this.gameBoard);
      game.value = game.Decide.VALID;
    }
    else {
      new Player(Color.BLACK, gameBoard);
      game.value = game.Decide.WAITING;
    }
  }

  public Player(Color color, GameBoard gameBoard){
    this.playerColor = color;
    this.game = null;
    this.gameBoard = gameBoard;
  }

  public synchronized StartGame currentGame(){
    if(game==null){
      game=gameBoard.getGame();
    }
    return game;
  }

}
