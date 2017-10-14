package com.webcheckers.appl;

import java.util.logging.Logger;

import com.webcheckers.model.StartGame;
import com.webcheckers.appl.Player;

/**
  * This class is to organize the state of the Web part.
  */
public class GameBoard{
  private static final Logger LOG = Logger.getLogger(GameBoard.class.getName());

  public Player initializePlayer(){
    return new Player(Player.Color.BLACK);
  }
}
