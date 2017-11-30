package com.webcheckers.model;

import com.webcheckers.model.Board;
import org.junit.Assert;
import org.junit.Test;


import static org.junit.Assert.*;


public class BoardTest {

  private Board board = new Board(8,8);
  private Row[] reverse = board.getBoard("","");
  private Row[] row = board.getBoard();

  /*
  Tests the get board methods
   */
  @Test
  public void testGetBoard()
  {
    Row[] reverseBoard=new Row[8];
    assertEquals(row[0], board.getBoard()[0]);
  }

  /*
  Tests to see if the board is actually set
   */
  @Test
  public void testSetBoard()
  {
    Row[] row1 = new Row[3];
    row1[0] = new Row(1,"",1);
    board.setBoard(row1);
    assertEquals(row1[0],board.getBoard()[0]);
    assertEquals(row1[0],board.getBoard("", "1")[0]);
    assertEquals(8,board.getHeight());
  }

  /*
  Tests to see if the rows are gotten
   */
  @Test
  public void testGetRow()
  {
    assertEquals(board.getBoard()[0], board.getRow(0));
  }

  /*
  Tests the functionality of the pieces in the Board class
   */
  @Test
  public void testBoardPieces()
  {
    board.removePiece(0,1);
    assertFalse(board.piecePresent(0, 1));
    assertNull(board.getRow(0).getRow()[1].getPiece());
    Piece p = new Piece("red","pawn");
    board.setPiece(p, 0 , 1);
    assertTrue(board.piecePresent(0,1));
    assertEquals(p, board.getRow(0).getRow()[1].getPiece());
  }



}
