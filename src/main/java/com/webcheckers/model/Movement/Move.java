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

    private Piece piece;
    private Square from;
    private Square to;

    // ------------
    // Constructors
    // ------------

    public Move(Piece piece, Square from, Square to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }

    // -------
    // Methods
    // -------

    public abstract boolean isValid(Board board);

    public void execute(Board board) {}
}

