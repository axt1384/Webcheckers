package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.Moves;
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
    // private Moves moves;

    // ------------
    // Constructors
    // ------------

    public TurnAdministrator(Player red, Player white, Board board) {
        this.red = red;
        this.white = white;
        this.currentPlayer = red;
        // this.moves = new Moves(board);
    }

    // -------
    // Methods
    // -------

    public boolean hasCaptureMove() {
        return false;
    }

    public boolean hasMove() {
        return false;
    }

    public boolean isValid() {
        return false;
    }

    public void makeMove() {}
}
