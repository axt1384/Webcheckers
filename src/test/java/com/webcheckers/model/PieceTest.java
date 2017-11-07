package com.webcheckers.model;

import com.webcheckers.model.Piece;
import org.junit.Test;

import static org.junit.Assert.*;


public class PieceTest
{
  private Piece p1 = new Piece("red", "pawn");
  private Piece p2 = new Piece ("Blue", "king");
  private Piece p3 = new Piece("white", "pawn");
  private Piece p4 = new Piece("white", "king");

  /*
   Tests the colors of the pieces
   */
  @Test
  public void testColor() {
    assertEquals("Color should be red", "red", p1.getColor());
    assertNotEquals("Color should be red", "red", p2.getColor());
    assertEquals("Color should be white", "white", p3.getColor());
    assertEquals("Color should be black", "white", p4.getColor());
  }

  /*
  Tests the types of the pieces
   */
  @Test
  public void testType() {
    assertEquals("Type should be pawn", "pawn", p1.getType());
    assertEquals("Type should be pawn", "pawn", p3.getType());
    assertEquals("Type should be king", "king", p2.getType());
    assertEquals("Type should be king", "king", p4.getType());
  }

  /*
  Tests the types of pieces that were pawns and set to kings
   */
  @Test
  public void testKing() {
    p1.setKing();
    p3.setKing();
    assertTrue(p1.getType().equals("king"));
    assertTrue(p3.getType().equals("king"));
  }

}
