package com.webcheckers.model;

import com.webcheckers.appl.GameCenter;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckersGameTest
{
  private GameCenter game;
  private Player p1 = new Player("p1");
  private Player p2 = new Player("p2");
  private CheckersGame checkers = new CheckersGame(game, p1, p2);

  /*
  Tests out the getters and boolean methods in CheckersGame
   */
  @Test
  public void testCheckersGame()
  {
    assertEquals(p1, checkers.getSummoner());

    assertEquals(p2, checkers.getOpp());

    assertTrue(checkers.isSummonerTurn());

    checkers.endTurn();
    assertFalse(checkers.isSummonerTurn());

    assertNotNull(checkers.getBoard());
    Board b = checkers.getBoard();
    assertEquals(b, checkers.getBoard());

    checkers.updateBoard("4-3","5-2","","");
    checkers.updateBoard("3-4","2-5","","");

    checkers.updateBoard("0-7","4-3","3-4","1-6");
    checkers.endTurn();
  }
}
