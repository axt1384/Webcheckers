package com.webcheckers.model.Movement;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Square;

/**
 * This move represents a normal move made by a King (forwards or backwards).
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class KingRegularMove extends Move {

    // ----------
    // Attributes
    // ----------

    // ------------
    // Constructors
    // ------------

    public KingRegularMove(Piece piece, Square from, com.webcheckers.model.Square to) {
        super(piece, from, to);
    }

    // -------
    // Methods
    // -------

    public boolean isValid(Board board) {
        return false;
    }
}
