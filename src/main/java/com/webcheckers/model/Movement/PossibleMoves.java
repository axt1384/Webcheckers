package com.webcheckers.model.Movement;

import com.webcheckers.appl.TurnAdministrator;
import com.webcheckers.model.Board;
import com.webcheckers.model.Square;

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
    private Board board;
    private ArrayList<Move> capture;
    private ArrayList<Move> normal;

    // ------------
    // Constructors
    // ------------

    public PossibleMoves(TurnAdministrator admin, Board board) {
        this.admin = admin;
        this.board = board;
    }


    // -------
    // Methods
    // -------

    private ArrayList<Square> getPieces(String color, String type) {
        int height = this.board.getBoard().length;
        int width = this.board.getRow(0).getRow().length;

        ArrayList<Square> targetSquares = new ArrayList<>();
        int i = 0, j = 0;

        while(j < height) {
            Square pointer = board.getRow(j).getSqueare(i);
            if(pointer.getPiece().getColor().equals(color) &&
                    pointer.getPiece().getType().equals(type)) {
                targetSquares.add(pointer);
            }
            i++;
            if(i >= width) {
                i = 0;
                j++;
            }
        }

        return targetSquares;
    }


    // -------------
    // Capture Moves
    // -------------

    private void generateNormalCaptureMoves(String color) {
        ArrayList<Square> alliedRegular = this.getPieces(color, "pawn");
        for(Square regular: alliedRegular) {
            if(regular.getRow() + 2 < this.board.getHeight() && regular.getColumn() - 2 >= 0) {
                Square destination = this.board.getSquare(regular.getRow() + 2, regular.getColumn() - 2);
                Move upperLeft = new NormalCaptureMove(regular.getPiece(), regular, destination);
                if (upperLeft.isValid(this.board)) {
                    this.capture.add(upperLeft);
                }
            }
            if(regular.getRow() + 2 < this.board.getHeight() && regular.getColumn() + 2 < this.board.getWidth()) {
                Square destination = this.board.getSquare(regular.getRow() + 2, regular.getColumn() + 2);
                Move upperRight = new NormalCaptureMove(regular.getPiece(), regular, destination);
                if(upperRight.isValid(this.board)) {
                    this.capture.add(upperRight);
                }
            }
        }
    }

    private void generateKingCaptureMoves(String color) {
        ArrayList<Square> alliedKing = this.getPieces(color, "king");
        for(Square king: alliedKing) {
            if(king.getRow() + 2 < this.board.getHeight() && king.getColumn() - 2 >= 0) {
                Square destination = this.board.getSquare(king.getRow() + 2, king.getColumn() - 2);
                Move upperLeft = new KingCaptureMove(king.getPiece(), king, destination);
                if (upperLeft.isValid(this.board)) {
                    this.capture.add(upperLeft);
                }
            }
            if(king.getRow() + 2 < this.board.getHeight() && king.getColumn() + 2 < this.board.getWidth()) {
                Square destination = this.board.getSquare(king.getRow() + 2, king.getColumn() + 2);
                Move upperRight = new KingCaptureMove(king.getPiece(), king, destination);
                if(upperRight.isValid(this.board)) {
                    this.capture.add(upperRight);
                }
            }
            if(king.getRow() - 2 >= 0 && king.getColumn() - 2 >= 0) {
                Square destination = this.board.getSquare(king.getRow() - 2, king.getColumn() - 2);
                Move lowerLeft = new KingCaptureMove(king.getPiece(), king, destination);
                if(lowerLeft.isValid(this.board)) {
                    this.capture.add(lowerLeft);
                }
            }
            if(king.getRow() - 2 >= 0 && king.getColumn() + 2 < this.board.getWidth()) {
                Square destination = this.board.getSquare(king.getRow() - 2, king.getColumn() + 2);
                Move lowerRight = new KingCaptureMove(king.getPiece(), king, destination);
                if(lowerRight.isValid(this.board)) {
                    this.capture.add(lowerRight);
                }
            }
        }
    }

    private void generateCaptureMoves(String color) {
        this.capture = new ArrayList<>();
        this.generateNormalCaptureMoves(color);
        this.generateKingCaptureMoves(color);
    }

    // -------------
    // Regular Moves
    // -------------

    private void generateNormalRegularMoves(String color) {
        ArrayList<Square> alliedRegular = this.getPieces(color, "pawn");
        for(Square regular: alliedRegular) {
            if(regular.getRow() + 1 < this.board.getHeight() && regular.getColumn() - 1 >= 0) {
                Square destination = this.board.getSquare(regular.getRow() + 1, regular.getColumn() - 1);
                Move upperLeft = new NormalRegularMove(regular.getPiece(), regular, destination);
                if (upperLeft.isValid(this.board)) {
                    this.capture.add(upperLeft);
                }
            }
            if(regular.getRow() + 1 < this.board.getHeight() && regular.getColumn() + 1 < this.board.getWidth()) {
                Square destination = this.board.getSquare(regular.getRow() + 1, regular.getColumn() + 1);
                Move upperRight = new NormalRegularMove(regular.getPiece(), regular, destination);
                if(upperRight.isValid(this.board)) {
                    this.capture.add(upperRight);
                }
            }
        }
    }

    private void generateKingRegularMoves(String color) {
        ArrayList<Square> alliedKing = this.getPieces(color, "king");
        for(Square king: alliedKing) {
            if(king.getRow() + 1 < this.board.getHeight() && king.getColumn() - 1 >= 0) {
                Square destination = this.board.getSquare(king.getRow() + 1, king.getColumn() - 1);
                Move upperLeft = new KingRegularMove(king.getPiece(), king, destination);
                if (upperLeft.isValid(this.board)) {
                    this.capture.add(upperLeft);
                }
            }
            if(king.getRow() + 1 < this.board.getHeight() && king.getColumn() + 1 < this.board.getWidth()) {
                Square destination = this.board.getSquare(king.getRow() + 1, king.getColumn() + 1);
                Move upperRight = new KingRegularMove(king.getPiece(), king, destination);
                if(upperRight.isValid(this.board)) {
                    this.capture.add(upperRight);
                }
            }
            if(king.getRow() - 1 >= 0 && king.getColumn() - 1 >= 0) {
                Square destination = this.board.getSquare(king.getRow() - 1, king.getColumn() - 1);
                Move lowerLeft = new KingRegularMove(king.getPiece(), king, destination);
                if(lowerLeft.isValid(this.board)) {
                    this.capture.add(lowerLeft);
                }
            }
            if(king.getRow() - 1 >= 0 && king.getColumn() + 1 < this.board.getWidth()) {
                Square destination = this.board.getSquare(king.getRow() - 1, king.getColumn() + 1);
                Move lowerRight = new KingRegularMove(king.getPiece(), king, destination);
                if(lowerRight.isValid(this.board)) {
                    this.capture.add(lowerRight);
                }
            }
        }
    }

    private void generateNormalMoves(String color) {
        this.normal = new ArrayList<>();
        this.generateNormalRegularMoves(color);
        this.generateKingRegularMoves(color);
    }


    // --------------
    // Public Methods
    // --------------

    public boolean hasCaptureMove(String color) {
        this.generateCaptureMoves(color);
        if(this.capture.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean hasMove(String color) {
        this.generateCaptureMoves(color);
        this.generateNormalMoves(color);

        if(this.capture.size() > 0 || this.normal.size() > 0) {
            return true;
        }
        return false;
    }
}
