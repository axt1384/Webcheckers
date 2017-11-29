package com.webcheckers.model;

import com.webcheckers.appl.GameCenter;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CheckersGame {

    // ----------
    // Attributes
    // ----------

    private static final Logger LOG = Logger.getLogger(CheckersGame.class.getName());

    private Board board;
    private final GameCenter gameCenter;
    private int id;
    private Player summoner, opp;
    private boolean summonerTurn;

    // ------------
    // Constructors
    // ------------

    public CheckersGame(GameCenter gameCenter, Player summoner, Player opp){
        this.gameCenter=gameCenter;
        this.board=new Board(8,8);
        this.summoner=summoner;
        this.opp=opp;
        this.summonerTurn=true;
    }

    // -------
    // Methods
    // -------

    /*
    Updates the board based on the move the player or opponent has done during his turn
     */
    public void updateBoard(String move, String oldPos, String capture, String capture2){
      String[] moveCoords=move.split("-");
      String row=moveCoords[0];
      String col=moveCoords[1];
      String[] oldPosCoords=oldPos.split("-");
      String oldRow=oldPosCoords[0];
      String oldCol=oldPosCoords[1];
      Piece p=board.removePiece(Integer.parseInt(oldRow), Integer.parseInt(oldCol));
      board.setPiece(p, Integer.parseInt(row), Integer.parseInt(col));

      if (capture != ""){
        String[] capCoords=capture.split("-");
        String capRow=capCoords[0];
        String capCol=capCoords[1];
        Piece temp = board.removePiece(Integer.parseInt(capRow), Integer.parseInt(capCol));
      }
      if (capture2 != ""){
        String[] capCoords2=capture2.split("-");
        String capRow2=capCoords2[0];
        String capCol2=capCoords2[1];
        Piece temp = board.removePiece(Integer.parseInt(capRow2), Integer.parseInt(capCol2));
      }
      if (!p.getType().equals("king") && ((p.getColor().equals("white") && Integer.parseInt(row) == 7) ||
              (p.getColor().equals("red") && Integer.parseInt(row) == 0))){
        p.setKing();
      }
    }

    /*
    Returns the opponent of the game
     */
    public Player getOpp(){
      return opp;
    }

    /*
    Returns true if it's the player's turn
     */
    public boolean isSummonerTurn(){
      return summonerTurn;
    }

    /*
    Returns the player of the game
     */
    public Player getSummoner(){
      return summoner;
    }

    public boolean equals(CheckersGame game){
        return this.summoner.equals(game.getSummoner()) && this.opp.equals(game.getOpp());
    }

    /*
    Ends the turn of the player
     */
    public void endTurn(){
      summonerTurn = !summonerTurn;
    }

    public Board getBoard(){
        return board;
    }
}
