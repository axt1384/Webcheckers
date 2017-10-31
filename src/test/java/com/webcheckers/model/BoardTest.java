package com.webcheckers.model;

import com.webcheckers.appl.GameCenter;
import org.junit.Test;
import static org.junit.Assert.*;


public class BoardTest {

        private Board board = new Board(8,8);
        private GameCenter gameCenter;



        @Test
        public void testLength() {

            assertEquals("The width should be 8",8,board.getBoard().length);
            assertNotEquals("The width should be 8",9,board.getBoard().length);
            assertEquals("The length should be 8",8,board.getBoard().length);
            assertNotEquals("The lenth should be 8",9,board.getBoard().length);

        }

        @Test
        public void testBoardNotNull(){
            assertEquals("The board is not null",board,board);
            assertNotEquals("The board is not null!",null,board.getBoard());
        }



}
