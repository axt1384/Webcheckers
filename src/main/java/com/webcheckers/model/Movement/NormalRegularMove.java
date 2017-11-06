package com.webcheckers.model.Movement;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Square;

/**
 * Represents a normal move made by an ordinary piece.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class NormalRegularMove extends Move {

    // ----------
    // Attributes
    // ----------

    // ------------
    // Constructors
    // ------------

    public NormalRegularMove(Piece piece, Square from, Square to) {
        super(piece, from, to);
    }

    // -------
    // Methods
    // -------

    public boolean isValid(Board board) {
        return false;
    }
}
