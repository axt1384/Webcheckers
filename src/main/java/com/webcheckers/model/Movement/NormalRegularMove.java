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
        if(this.piece != this.origin.getPiece() || // The Origin Does not Match the Moving Piece
                this.origin.getPiece().getType() != "pawn") { // The Moving Piece Needs to be a pawn
            return false;
        } else if(this.destination.getPiece().getType() != null) { // Destination is Occupied
            return false;
        }

        int xChange = this.origin.getColumn() - this.destination.getColumn(); // X Translation
        int yChange = this.origin.getRow() - this.destination.getRow(); // Y Translation

        if((xChange == -1 || xChange == 1) && yChange == 1) { // Is Moving up Diagonally one Square
            return true;
        }
        return false;
    }
}
