package com.webcheckers.model;


import com.webcheckers.model.Player;
import org.junit.Assert;
import org.junit.Test;


import static org.junit.Assert.*;


public class PlayerTest {

    private Player p1 = new Player("Happy",false);
    private Player p2 = new Player("Sad",true);
    private Player p3 = new Player("Happy",false);
    private Player p4 = new Player("Furious",true);
    private Player p5 = new Player("Furious",false);
    private Player p6 = new Player(null);


    /*
  Tests the get Player methods
   */
    @Test
    public void testPlayerIsSummoner() {

        assertFalse(p1.isSummoner());
        assertTrue(p2.isSummoner());
        assertFalse(p3.isSummoner());
        assertTrue(p4.isSummoner());
        assertFalse(p5.isSummoner());
    }

    /*
    Test to see if player is null or not
     */
    @Test
    public void testUsername(){

        assertNotNull(p1.toString());
        assertNotNull(p2.toString());
        assertNotNull(p3.toString());
        assertNotNull(p4.toString());
        assertNotNull(p5.toString());
        assertNull(p6.toString());

    }

    /*
    Test to see if 1 player is equal to another player
     */
    @Test
    public void testPlayernotNull(){

        assertNotNull(p1.toString());
        assertNotNull(p2.toString());
        assertNotNull(p3.toString());
        assertNotNull(p4.toString());
        assertNotNull(p5.toString());
        assertNull(p6.toString());

    }

    /*
    Test to see if 1 player is equal to another player
     */
    @Test
    public void testPlayerEqualsAnotherPlayer(){

        assertNotEquals(p1.toString(),p2.toString());
        assertEquals(p1.toString(),p3.toString());
        assertEquals(p4.toString(),p5.toString());
        assertNotEquals(p2.toString(),p5.toString());

    }

    @Test
    public void testValidUsername(){

        assertEquals("Name should be Happy", "Happy", p1.toString());
        assertEquals("Name should be Sad", "Sad", p2.toString());
        assertEquals("Name should be Happy", "Happy", p3.toString());
        assertEquals("Name should be Furious", "Furious", p4.toString());
        assertEquals("Name should be Furious", "Furious", p5.toString());
        assertNotEquals("Name should not be Happy", "Happy", p2.toString());
    }

}
