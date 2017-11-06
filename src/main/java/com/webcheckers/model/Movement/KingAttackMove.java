package com.webcheckers.model.Movement;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Square;

/**
 * This move represents an attack made by a King.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class KingAttackMove extends Move {

    // ----------
    // Attributes
    // ----------

    // ------------
    // Constructors
    // ------------

    public KingAttackMove(Piece piece, Square from, Square to) {
        super(piece, from, to);
    }

    // -------
    // Methods
    // -------

    public boolean isValid(Board board) {
        return false;
    }
}
