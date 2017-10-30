package com.webcheckers.model;

import com.webcheckers.model.Piece;
import org.junit.Test;

import static org.junit.Assert.*;


public class PieceTest
{
  private Piece p1 = new Piece("Red", "Pawn");
  private Piece p2 = new Piece ("Blue", "King");
  private Piece p3 = new Piece("Black", "Pawn");
  private Piece p4 = new Piece("Black", "King");
  @Test
  public void testColor()
  {
    assertEquals("Color should be red", "Red", p1.getColor());
    assertNotEquals("Color should be red", "Red", p2.getColor());
    assertEquals("Color should be black", "Black", p3.getColor());
    assertEquals("Color should be black", "Black", p4.getColor());
  }
  @Test
  public void testType()
  {
    assertEquals("Type should be pawn", "Pawn", p1.getType());
    assertEquals("Type should be pawn", "Pawn", p3.getType());
    assertEquals("Type should be king", "King", p2.getType());
    assertEquals("Type should be king", "King", p4.getType());
  }

}
