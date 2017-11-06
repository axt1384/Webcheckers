package com.webcheckers.model.Movement;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Square;

/**
 * Represents an attack move made by an ordinary piece.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class NormalAttackMove extends Move {

    // ----------
    // Attributes
    // ----------

    // ------------
    // Constructors
    // ------------

    public NormalAttackMove(Piece piece, Square from, Square to) {
        super(piece, from, to);
    }

    // -------
    // Methods
    // -------

    public boolean isValid(Board board) {
        return false;
    }
}
