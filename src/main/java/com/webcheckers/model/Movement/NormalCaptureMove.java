package com.webcheckers.model.Movement;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Square;

/**
 * Represents an attack move made by an ordinary piece.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class NormalCaptureMove extends Move {


    // ----------
    // Attributes
    // ----------

    // ------------
    // Constructors
    // ------------


    /**
     * Creates a movement with NormalCaptureMove functionality.
     * @param piece The moving Piece.
     * @param from The origin Square.
     * @param to The destination Square.
     */
    public NormalCaptureMove(Piece piece, Square from, Square to) {
        super(piece, from, to);
    }

    // -------
    // Methods
    // -------

    /**
     * A Pawn may move up to the left or up to the right. It must have an enemy in between the origin and destination
     * Squares. The destination Square must not be occupied.
     * @param board Board where the move will take place.
     * @return True if the Move is possible.
     */
    public boolean isValid(Board board) {
        if(this.piece != this.origin.getPiece() || // The Origin Does not Match the Moving Piece
                this.origin.getPiece().getType() != "pawn") { // The Moving Piece Needs to be a Pawn
            return false;
        } else if(this.destination.getPiece() != null) { // Destination is Occupied
            return false;
        }

        int xChange = this.destination.getPossibleColumn() - this.origin.getPossibleColumn(); // X Translation
        int yChange = this.destination.getPossibleRow() - this.origin.getPossibleRow(); // Y Translation

        Square target = null; // Pointer Toward Middle Square


        // Find the Target Square if Matching a Pawns's Capture Movement
         if(xChange == -2 && yChange == -2) { // Up to the Left
            target = board.getRow(this.origin.getPossibleRow() - 1).getSquare(this.origin.getPossibleColumn() - 1);
        }else if(xChange == 2 && yChange == -2) { // Up to the Right
            target = board.getRow(this.origin.getPossibleRow() - 1).getSquare(this.origin.getPossibleColumn() + 1);
        }

        if(target != null) { // Movement is Possible - Correct
            Piece inQuestion = target.getPiece();
            if(inQuestion == null) { // No Piece to Capture
                return false;
            }
            if(inQuestion.getColor() == this.piece.getColor()) { // Can't Jump Over an Allied Piece
                return false;
            }
            return true; // Space and Enemy Piece Exist
        }

        return false; // Pieces are Missing or Too Far Apart
    }

    /**
     * Executes the Move. If the move is invalid nothing will happen.
     * @param board Board where the move is to take place.
     */
    @Override
    public void execute(Board board) {
        if(this.isValid(board)) {
            int xChange = this.origin.getPossibleColumn() - this.destination.getPossibleColumn(); // X Translation
            int yChange = this.origin.getPossibleRow() - this.destination.getPossibleRow(); // Y Translation

            Square enemy = board.getSquare(this.origin.getPossibleRow() + yChange, this.origin.getPossibleColumn() + xChange);

            this.origin.removePiece();
            enemy.removePiece();
            this.destination.setPiece(this.piece);
        }
    }
}
