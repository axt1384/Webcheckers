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

    public void updateBoard(String move, String oldPos, String capture){
      String[] moveCoords=move.split("-");
      String row=moveCoords[0];
      String col=moveCoords[1];
      String[] oldPosCoords=oldPos.split("-");
      String oldRow=oldPosCoords[0];
      String oldCol=oldPosCoords[1];
      LOG.config("move: "+row+" "+col+" old: "+oldRow+" "+oldCol);
      Piece p=board.removePiece(Integer.parseInt(oldRow), Integer.parseInt(oldCol));
      board.setPiece(p, Integer.parseInt(row), Integer.parseInt(col));
      if (capture != ""){
        String[] capCoords=capture.split("-");
        String capRow=capCoords[0];
        String capCol=capCoords[1];
        Piece temp = board.removePiece(Integer.parseInt(capRow), Integer.parseInt(capCol));
      }
      if (!p.getType().equals("king") && ((p.getColor().equals("white") && Integer.parseInt(row) == 7) ||
              (p.getColor().equals("red") && Integer.parseInt(row) == 0))){
        p.setKing();
      }
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

    public Board getOppBoard(){
      Board b=new Board(8,8);
      b.setBoard(board.getOppBoard());
      return b;
    }

    public Board getBoard(){
        return board;
    }

}
