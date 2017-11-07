package com.webcheckers.model;

import com.webcheckers.appl.TurnAdministrator;

import java.util.Objects;
import java.util.Vector;

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

    public static final int
            RED = 1,
            RED_KING = 2,
            BLACK = 3,
            BLACK_KING = 4;

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

//    public Move[] getLegalMoves(Piece player, Square board, Row row) {
//
//        if (!Objects.equals(player.getColor(), "Red") || !Objects.equals(player.getColor(), "White")) {
//            return null;
//        }
//        int playerKing;  // The constant representing a King belonging to player.
//        if (Objects.equals(player.getColor(), "Red"))
//            playerKing = RED_KING;
//        else
//            playerKing = BLACK_KING;
//
//        Vector<Move> possiblemoves = new Vector<>();
//
//        for (int r = 0; r < 7; r++) {
//            for (int col = 0; col < 7; col++) {
//                if (board.hasPiece() || Objects.equals(board.getPieceType(), "King")) {
//                    if (canJump(player, row.getIndex(), col, row.getIndex() + 1, col + 1, row.getIndex() + 2, col + 2))
//                        possiblemoves.addElement(new Move(row.getIndex(), col, row.getIndex() + 2, col + 2));
//                    if (canJump(player, row.getIndex(), col, row.getIndex() - 1, col + 1, row.getIndex() - 2, col + 2))
//                        possiblemoves.addElement(new Move(row.getIndex(), col, row.getIndex() - 2, col + 2));
//                    if (canJump(player, row.getIndex(), col, row.getIndex() + 1, col - 1, row.getIndex() + 2, col - 2))
//                        possiblemoves.addElement(new Move(row.getIndex(), col, row.getIndex() + 2, col - 2));
//                    if (canJump(player, row.getIndex(), col, row.getIndex() - 1, col - 1, row.getIndex() - 2, col - 2))
//                        possiblemoves.addElement(new Move(row.getIndex(), col, row.getIndex() - 2, col - 2));
//                }
//
//            }
//        }
//        if (possiblemoves.size() == 0) {
//            for (int r = 0; r < 8; r++) {
//                for (int col = 0; col < 8; col++) {
//                    if (board.hasPiece() || Objects.equals(board.getPieceType(), "King")) {
//                        if (canMove(player, row.getIndex(), col, row.getIndex() + 1, col + 1))
//                            possiblemoves.addElement(new Move(row.getIndex(), col, row.getIndex() + 1, col + 1));
//                        if (canMove(player, row.getIndex(), col, row.getIndex() - 1, col + 1))
//                            possiblemoves.addElement(new Move(row.getIndex(), col, row.getIndex() - 1, col + 1));
//                        if (canMove(player, row.getIndex(), col, row.getIndex() + 1, col - 1))
//                            possiblemoves.addElement(new Move(row.getIndex(), col, row.getIndex() + 1, col - 1));
//                        if (canMove(player, row.getIndex(), col, row.getIndex() - 1, col - 1))
//                            possiblemoves.addElement(new Move(row.getIndex(), col, row.getIndex() - 1, col - 1));
//                    }
//                }
//            }
//        }
//        if (possiblemoves.size() == 0)
//            return null;
//        else {
//            Move[] Moves = new Move[possiblemoves.size()];
//            for (int i = 0; i < possiblemoves.size(); i++)
//                Moves[i] = (Move)possiblemoves.elementAt(i);
//            return Moves;
//        }
//    }


//    private boolean canJump(Piece player, int r1, int c1, int r2, int c2, int r3, int c3) {
//        // This is called by the two previous methods to check whether the
//        // player can legally jump from (r1,c1) to (r3,c3).  It is assumed
//        // that the player has a piece at (r1,c1), that (r3,c3) is a position
//        // that is 2 rows and 2 columns distant from (r1,c1) and that
//        // (r2,c2) is the square between (r1,c1) and (r3,c3).
//
//        if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
//            return false;  // (r3,c3) is off the board.
//
//        if (board[r3][c3] != EMPTY)
//            return false;  // (r3,c3) already contains a piece.
//
//        if (player == RED) {
//            if (board[r1][c1] == RED && r3 > r1)
//                return false;  // Regular red piece can only move  up.
//            if (board[r2][c2] != BLACK && board[r2][c2] != BLACK_KING)
//                return false;  // There is no black piece to jump.
//            return true;  // The jump is legal.
//        } else {
//            if (board[r1][c1] == BLACK && r3 < r1)
//                return false;  // Regular black piece can only move downn.
//            if (board[r2][c2] != RED && board[r2][c2] != RED_KING)
//                return false;  // There is no red piece to jump.
//            return true;  // The jump is legal.
//        }
//
//    }
//
//
//    private boolean canMove(Piece player, int r1, int c1, int r2, int c2) {
//        // This is called by the getLegalMoves() method to determine whether
//        // the player can legally move from (r1,c1) to (r2,c2).  It is
//        // assumed that (r1,r2) contains one of the player's pieces and
//        // that (r2,c2) is a neighboring square.
//
//        if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
//            return false;  // (r2,c2) is off the board.
//
//        if (board[r2][c2] != EMPTY)
//            return false;  // (r2,c2) already contains a piece.
//
//        if (player == RED) {
//            if (board[r1][c1] == RED && r2 > r1)
//                return false;  // Regualr red piece can only move down.
//            return true;  // The move is legal.
//        } else {
//            if (board[r1][c1] == BLACK && r2 < r1)
//                return false;  // Regular black piece can only move up.
//            return true;  // The move is legal.
//        }
//    }
}
