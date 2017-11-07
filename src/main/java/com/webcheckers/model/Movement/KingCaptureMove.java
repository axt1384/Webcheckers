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

    public KingCaptureMove(Piece piece, Square from, Square to) {
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
}
