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
        if(this.piece != this.origin.getPiece() || // The Origin Does not Match the Moving Piece
                this.origin.getPiece().getType() != "king") { // The Moving Piece Needs to be a King
            return false;
        } else if(this.destination.getPiece().getType() != null) { // Destination is Occupied
            return false;
        }

        int xChange = this.origin.getColumn() - this.destination.getColumn(); // X Translation
        int yChange = this.origin.getRow() - this.destination.getRow(); // Y Translation

        if((xChange == -1 || xChange == 1) && (yChange == -1 || yChange == 1)) { // Is Moving Diagonally one Square
            return true;
        }
        return false;
    }
}
