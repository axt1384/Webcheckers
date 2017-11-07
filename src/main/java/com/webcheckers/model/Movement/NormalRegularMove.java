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

    /**
     * Creates a movement with NormalRegularMove functionality.
     * @param piece The moving Piece.
     * @param from The origin Square.
     * @param to The destination Square.
     */
    public NormalRegularMove(Piece piece, Square from, Square to) {
        super(piece, from, to);
    }

    // -------
    // Methods
    // -------

    /**
     * A Pawn may move up to the left or up to the right. Must move diagonally. Destination must not be occupied.
     * @param board Board where the move will take place.
     * @return True if the Move is possible.
     */
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
