package com.webcheckers.model;

import com.webcheckers.model.Piece;
import com.webcheckers.model.Row;
import com.webcheckers.model.Square;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Jesse Chen
 */
public class SquareTest {
    private Square square;
    private Square square2;
    private Piece red;

    @Before
    public void setup(){
        red=new Piece("red","King");
    }

    /**
     * Tests attributes of square
     */
    @Test
    public void test_Square_Properties(){
        square=new Square("white",red,0);
        square.removePiece();
        square.setPiece(red);
        assertEquals("white",square.getSquare());
        assertEquals(red, square.getPiece());
        assertEquals("King", square.getPieceType());
        assertEquals("red", square.getPieceColor());
        assertEquals(0,square.getIndex());
    }

    /**
     * Tests the Square based on the piece on it
     */
    @Test
    public void test_Square_Piece(){
        square=new Square("white",red,0);
        square2=new Square("white",null,2);
        assertTrue(square.hasPiece());
        assertFalse(square2.hasPiece());
        assertEquals("red",square.getPieceColor());
    }

    /**
     * Tests the validity of a square
     */
    @Test
    public void test_Validity(){
        square=new Square("white",null,0);
        square2=new Square("black",null,1);
        assertTrue(square2.isValid());
        assertFalse(square.isValid());
    }

}
