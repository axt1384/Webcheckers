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

    enum MoveType { // Necessary?
        KINGCAPTURE,
        KINGREGULAR,
        NORMALCAPTURE,
        NORMALREGULAR
    }

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

    /**
     * Determines if a Move is possible.
     * @param board Board where the Move is to take place.
     * @return True if the Move is possible.
     */
    public abstract boolean isValid(Board board);

    /**
     * Executes the Move.
     * @param board Board where the move is to take place.
     */
    public abstract void execute(Board board);
}

