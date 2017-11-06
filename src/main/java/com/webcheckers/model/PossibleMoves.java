package com.webcheckers.model;

import com.webcheckers.appl.TurnAdministrator;

import java.util.ArrayList;

/**
 * Represents all of the Possible Moves a Player may make.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class PossibleMoves {

    // ----------
    // Attributes
    // ----------

    private TurnAdministrator admin;

    // ------------
    // Constructors
    // ------------

    public PossibleMoves(TurnAdministrator admin) {
        this.admin = admin;
    }


    // -------
    // Methods
    // -------

    // These will Change TurnAdministrator
    public boolean hasCaptureMove() {
        return false;
    }

    public boolean hasMove() {
        return false;
    }

    public Move[] getLegalMoves(CheckersGame checkersGame, Square square) {

        if(checkersGame.getSummoner() == null && checkersGame.getOpp() == null){
            return null;
        }
        ArrayList Moves = new ArrayList();
        for(int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                //if(square.)
            }
        }
        return null;

    }




    private boolean canMove(Player player, Row r1, int col1, Row r2, int col2) {
        // This is called by the getLegalMoves() method to determine whether
        // the player can legally move from (r1,c1) to (r2,c2).  It is
        // assumed that (r1,r2) contains one of the player's pieces and
        // that (r2,c2) is a neighboring square.


        if (r2.getIndex() < 0 || r2.getIndex() >= 8 || col2 < 0 || col2 > 8) {
            return false;
        }

        if (r2.PiecePresent(col2)) {
            return false;
        }
//        if(player.isSummoner()){
//            if(r1.PiecePresent(col1)
//
//            }
//        }
        return false;
    }


//    private boolean canJump(Board board, int r1, int c1, int r2, int c2, int r3, int c3){
//
//        if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
//            return false;  // (r3,c3) is off the board.
//
//        if (board.getRow(r3).PiecePresent(c3))
//            return false;  // (r3,c3) already contains a piece.
//
//        if (player == RED) {
//            if (board[r1][c1] == RED && r3 > r1)
//                return false;  // Regular red piece can only move  up.
//            if (board[r2][c2] != BLACK && board[r2][c2] != BLACK_KING)
//                return false;  // There is no black piece to jump.
//            return true;  // The jump is legal.
//        }
//        else {
//            if (board[r1][c1] == BLACK && r3 < r1)
//                return false;  // Regular black piece can only move downn.
//            if (board[r2][c2] != RED && board[r2][c2] != RED_KING)
//                return false;  // There is no red piece to jump.
//            return true;  // The jump is legal.
//        }
//    }
//

}
