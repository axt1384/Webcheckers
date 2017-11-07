package com.webcheckers.model;

import com.webcheckers.appl.GameCenter;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CheckersGame {
    private static final Logger LOG = Logger.getLogger(CheckersGame.class.getName());

    private Board board;
    private final GameCenter gameCenter;
    private int id;
    private Player summoner, opp;
    private boolean summonerTurn;

    public CheckersGame(GameCenter gameCenter, Player summoner, Player opp){
        this.gameCenter=gameCenter;
        this.board=new Board(8,8);
        this.summoner=summoner;
        this.opp=opp;
        this.summonerTurn=true;
    }

    public void updateBoard(String move, String oldPos){
      String[] moveCoords=move.split("-");
      String row=moveCoords[0];
      String col=moveCoords[1];
      String[] oldPosCoords=oldPos.split("-");
      String oldRow=oldPosCoords[0];
      String oldCol=oldPosCoords[1];
      Piece p=board.removePiece(Integer.parseInt(oldRow), Integer.parseInt(oldCol));
      board.setPiece(p, Integer.parseInt(row), Integer.parseInt(col));
    }

    public Player getOpp(){
      return opp;
    }

    public boolean isSummonerTurn(){
      return summonerTurn;
    }

    public Player getSummoner(){
      return summoner;
    }

    public void endTurn(){
      summonerTurn=!summonerTurn;
    }

    public Board getBoard(){
        return board;
    }

}
