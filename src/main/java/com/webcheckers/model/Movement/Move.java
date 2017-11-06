package com.webcheckers.model.Movement;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Square;

/**
 * Represents a move one Piece (of a Player) can make.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public abstract class Move {

    // ----------
    // Attributes
    // ----------

    protected Piece piece;
    protected Square origin;
    protected Square destination;

    // ------------
    // Constructors
    // ------------

    public Move(Piece piece, Square origin, Square destination) {
        this.piece = piece;
        this.origin = origin;
        this.destination = destination;
    }

    // -------
    // Methods
    // -------

    public abstract boolean isValid(Board board);

    public void execute() {}
}

