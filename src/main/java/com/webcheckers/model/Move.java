package com.webcheckers.model;

/**
 * Represents a move one Piece (of a Player) can make.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class Move {

    // ----------
    // Attributes
    // ----------

    int fromRow;
    int fromCol;
    int toRow;
    int toCol;

    // ------------
    // Constructors
    // ------------

    public Move(int r1, int c1, int r2, int c2) {
        // Constructor.  Just set the values of the instance variables.
        fromRow = r1;
        fromCol = c1;
        toRow = r2;
        toCol = c2;
    }

    // -------
    // Methods
    // -------
    public boolean isJump() {
        // Test whether this move is a jump.  It is assumed that
        // the move is legal.  In a jump, the piece moves two
        // rows.  (In a regular move, it only moves one row.)
            return (fromRow - toRow == 2 || fromRow - toRow == -2);
        }
}
