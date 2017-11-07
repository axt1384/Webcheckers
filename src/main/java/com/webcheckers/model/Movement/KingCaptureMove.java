package com.webcheckers.model.Movement;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Square;

/**
 * This move represents an attack made by a King.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class KingCaptureMove extends Move {


    // ----------
    // Attributes
    // ----------

    // ------------
    // Constructors
    // ------------

    /**
     * Creates a movement with KingCaptureMove functionality.
     * @param piece The moving Piece.
     * @param from The origin Square.
     * @param to The destination Square.
     */
    public KingCaptureMove(Piece piece, Square from, Square to) {
        super(piece, from, to);
    }

    // -------
    // Methods
    // -------

    /**
     * A King may move up to the left, up to the right, down to the left, and down to the right. There must be an enemy
     * piece in between the origin Square and Destination Square. Must move 2 units vertically and 2 units horizontally.
     * @param board Board where the move will take place.
     * @return True if the Move is possible.
     */
    @Override
    public boolean isValid(Board board) {
        if(this.piece != this.origin.getPiece() || // The Origin Does not Match the Moving Piece
                this.origin.getPiece().getType() != "king") { // The Moving Piece Needs to be a King
            return false;
        } else if(this.destination.getPiece().getType() != null) { // Destination is Occupied
            return false;
        }

        int xChange = this.origin.getColumn() - this.destination.getColumn(); // X Translation
        int yChange = this.origin.getRow() - this.destination.getRow(); // Y Translation

        Square target = null; // Pointer Toward Middle Square


        // Find the Target Square if Matching a King's Capture Movement
        if(xChange == -2 && yChange == -2) { // Down to the Left
            target = board.getRow(this.origin.getRow() - 1).getSqueare(this.origin.getColumn() - 1);
        }
        else if(xChange == -2 && yChange == 2) { // Up to the Left
            target = board.getRow(this.origin.getRow() - 1).getSqueare(this.origin.getColumn() + 1);
        }
        else if(xChange == 2 && yChange == -2) { // Down to the Right
            target = board.getRow(this.origin.getRow() + 1).getSqueare(this.origin.getColumn() - 1);
        }
        else if(xChange == 2 && yChange == 2) { // Up to the Right
            target = board.getRow(this.origin.getRow() + 1).getSqueare(this.origin.getColumn() + 1);
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
            int xChange = this.origin.getColumn() - this.destination.getColumn(); // X Translation
            int yChange = this.origin.getRow() - this.destination.getRow(); // Y Translation

            Square enemy = board.getSquare(this.origin.getRow() + yChange, this.origin.getColumn() + xChange);

            this.origin.removePiece();
            enemy.removePiece();
            this.destination.setPiece(this.piece);
        }
    }
}
