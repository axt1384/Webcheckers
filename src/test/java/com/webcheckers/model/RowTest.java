package com.webcheckers.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.webcheckers.model.Row;
import com.webcheckers.model.Square;
import com.webcheckers.model.Piece;

public class RowTest{

  private int width = 8;
  private String black = "black";
  private String white = "white";
  private int index = 0;
  private Square[] row = new Square[width];

  @Test
  public void cons_even(){
    // this also tests out the rowInit() since it is in the constructor.
    // if there is no errors, this is good (:
    new Row(width, white, index);
  }

  @Test
  public void cons_odd(){
    new Row(width, black, index+1);
  }

  // tests the rest of the methods
  @Test
  public void theRest(){
    final Row CuT = new Row(width, white, index);
    final int testIndex = CuT.getIndex();
    Piece p = CuT.removePiece(0);
    assertEquals(0, testIndex);
    assertFalse(CuT.getRow()[0].hasPiece());
    CuT.setPiece(p,0);
    //assertTrue(CuT.getRow()[0].hasPiece());

    row = CuT.getRow();
    final Square[] testRow = CuT.getRow("bob", "user");
    final Square[] reverseRow = CuT.getRow("bob", "bob");
    assertEquals(row, testRow);
  }
}
