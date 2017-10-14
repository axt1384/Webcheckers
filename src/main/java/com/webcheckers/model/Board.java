package com.webcheckers.model;

import com.webcheckers.appl.Player;

public class Board{

  private Square[][] board;

  public Board() {
    this.board = new Square [8][8];
    populateBoard(board);
  }

  public void populateBoard(Square[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (((j % 2 == 0 && i % 2 == 1) || (j % 2 == 1 && i % 2 == 0))) {
          if (i < 3) {
            board[i][j] = new Square(Square.Playable.YES, Player.playerColor);
          }
          else if (i > 4) {
            board[i][j] = new Square(Square.Playable.YES, Player.playerColor);
          }
          else {
            board[i][j] = new Square(Square.Playable.YES, null);
          }
        }
        else {
          board[i][j] = new Square(Square.Playable.NO, null);
        }
      }
    }
  }

  public String toString() {
    String display = "";
    for (int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[i].length; j++) {
        display = display + "     " + board[i][j];
      }
      display += "\n";
    }
    return display;
  }

  public static void main(String[] args)
  {
      Board b = new Board();
      System.out.println(b);
  }
}
