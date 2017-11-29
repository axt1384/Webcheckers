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

    /**
     * Creates a movement with KingRegularMove functionality.
     * @param piece The moving Piece.
     * @param from The origin Square.
     * @param to The destination Square.
     */
    public KingRegularMove(Piece piece, Square from, com.webcheckers.model.Square to) {
        super(piece, from, to);
    }

    // -------
    // Methods
    // -------

    /**
     * A King may move up to the left, up to the right, down to the left or down to the right. The King must move
     * diagonally and the destination can't be occupied.
     * @param board Board where the move will take place.
     * @return True if the Move is possible.
     */
    public boolean isValid(Board board) {
        if(this.piece != this.origin.getPiece() || // The Origin Does not Match the Moving Piece
                this.origin.getPiece().getType() != "king") { // The Moving Piece Needs to be a King
            return false;
        } else if(this.destination.getPiece() != null) { // Destination is Occupied
            return false;
        }

        int xChange = this.destination.getPossibleColumn() - this.origin.getPossibleColumn(); // X Translation
        int yChange = this.destination.getPossibleRow() - this.origin.getPossibleRow(); // Y Translation

        if((xChange == -1 || xChange == 1) && (yChange == -1 || yChange == 1)) { // Is Moving Diagonally one Square
            return true;
        }
        return false;
    }

    /**
     * Executes the Move. If the move is invalid nothing will happen.
     * @param board Board where the move is to take place.
     */
    @Override
    public void execute(Board board) {
        if(this.isValid(board)) {
            this.origin.removePiece();
            this.destination.setPiece(this.piece);
        }
    }
}
