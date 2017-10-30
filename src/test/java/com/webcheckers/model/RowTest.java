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

  @Test
  public void getIndex(){
    final Row CuT = new Row(width, white, index);
    final int testIndex = CuT.getIndex();
    assertEquals(0, testIndex);
  }
}
