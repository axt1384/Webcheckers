package com.webcheckers.appl;

import com.webcheckers.model.Board;
//import com.webcheckers.model.Moves;
import com.webcheckers.model.PossibleMoves;
import com.webcheckers.model.Player;

/**
 * Orchestrates the turns of the players. This class oscillates between each player
 * as they make their moves.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class TurnAdministrator {

    // ----------
    // Attributes
    // ----------

    private final Player red;
    private final Player white;
    private Player currentPlayer;
    private PossibleMoves possibleMoves;

    // ------------
    // Constructors
    // ------------

    public TurnAdministrator(Player red, Player white, Board board) {
        this.red = red;
        this.white = white;
        this.currentPlayer = red;
        this.possibleMoves = new PossibleMoves(this);
    }

    // -------
    // Methods
    // -------

    public void takeTurn() {}
}
