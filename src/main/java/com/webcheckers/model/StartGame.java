package com.webcheckers.model;

import java.util.logging.Logger;

import com.webcheckers.appl.GameBoard;

/**
  * This is a single game instance
  */
public class StartGame{
  private static final Logger LOG = Logger.getLogger(StartGame.class.getName());

  private Board board;
  private final GameBoard gameBoard;

  private int totalPieces,redPieces,blackPieces,totalMoves;

  /**
   * Create a new WebCheckers game
   * @param totalPieces- The total pieces that the players will be playing with
   * @param gameBoard- The Gameboard that has sitewide responsibilities
   */
  public StartGame(int totalPieces, GameBoard gameBoard){
    LOG.fine("Game Created");
    this.totalPieces=totalPieces;
    this.gameBoard=gameBoard;
    this.totalPieces=totalPieces;
    this.blackPieces=this.redPieces=totalPieces/2;
    this.totalMoves=0;
  }

  /**
   * Checks whether the game is in the beginning; basically if any moves  
   * @return
   */
  public synchronized boolean isGameBeginning(){
    return totalMoves==0;
  }

}
