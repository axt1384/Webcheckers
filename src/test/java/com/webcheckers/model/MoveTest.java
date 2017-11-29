package com.webcheckers.model;


import com.webcheckers.model.Move;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;


public class MoveTest {

    private Move m1 = new Move(1,2,2,3);
    private Move m2 = new Move(2,1,4,3);
    private Move m3 = new Move(5,5,7,7);
    private Move m4 = new Move(1,3,1,3);
    private Move m5 = new Move(6,7,6,7);
    private Move m6 = new Move(3,4,5,6);


    /*
    Tests the get Move methods
   */
    @Test
    public void testisJump() {

        assertFalse(m1.isJump());
        assertTrue(m2.isJump());
        assertTrue(m3.isJump());
        assertFalse(m4.isJump());
        assertFalse(m5.isJump());
        assertTrue(m6.isJump());
    }


    /*
     Tests the get Move methods
   */
    @Test
    public void testisEquals() {

        assertFalse(m1.fromRow == m1.toRow);
        assertFalse(m2.fromRow == m2.toRow);
        assertTrue(m4.fromRow == m4.toRow);
        assertTrue(m5.fromCol == m5.toCol);

    }

}
