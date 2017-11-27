package com.webcheckers.model.Movement;

import com.webcheckers.appl.TurnAdministrator;
import com.webcheckers.model.Board;
import com.webcheckers.model.CheckersGame;
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
    private CheckersGame checkersGame;
    private ArrayList<Move> capture;
    private ArrayList<Move> normal;

    // ------------
    // Constructors
    // ------------

    public PossibleMoves(TurnAdministrator admin, CheckersGame checkersGame) {
        this.admin = admin;
        this.checkersGame = checkersGame;
        this.board = checkersGame.getBoard();
    }


    // -------
    // Methods
    // -------

    private ArrayList<Square> getPieces(String color, String type) {
        //int height = this.board.getBoard().length;
        int height = 8;
        //int width = this.board.getRow(0).getRow().length;
        int width = 8;

        ArrayList<Square> targetSquares = new ArrayList<>();
        int i = 0, j = 0;

        while(j < height) {
            Square pointer = board.getRow(j).getSquare(i);
            if(pointer.getPiece() != null) {
                if(pointer.getPiece().getColor().equals(color) &&
                        pointer.getPiece().getType().equals(type)) {
                    targetSquares.add(pointer);
                }
            }
            i++;
            if (i >= width) {
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
        int z = 2; // Movement
        for(Square regular: alliedRegular) {
            if(regular.getPossibleRow() - z >= 0 && regular.getPossibleColumn() - z >= 0) {
                Square destination = this.board.getSquare(regular.getPossibleRow() - z, regular.getPossibleColumn() - z);
                Move upperLeft = new NormalCaptureMove(regular.getPiece(), regular, destination);
                if (upperLeft.isValid(this.board)) {
                    this.capture.add(upperLeft);
                }
            }
            if(regular.getPossibleRow() - z >= 0 && regular.getPossibleColumn() + z < this.board.getWidth()) {
                Square destination = this.board.getSquare(regular.getPossibleRow() - z, regular.getPossibleColumn() + z);
                Move upperRight = new NormalCaptureMove(regular.getPiece(), regular, destination);
                if(upperRight.isValid(this.board)) {
                    this.capture.add(upperRight);
                }
            }
        }
    }

    private void generateKingCaptureMoves(String color) {
        ArrayList<Square> alliedKing = this.getPieces(color, "king");
        int z = 2; // Movement
        for(Square king: alliedKing) {
            if(king.getPossibleRow() - z >= 0 && king.getPossibleColumn() - z >= 0) {
                Square destination = this.board.getSquare(king.getPossibleRow() - z, king.getPossibleColumn() - z);
                Move upperLeft = new KingCaptureMove(king.getPiece(), king, destination);
                if (upperLeft.isValid(this.board)) {
                    this.capture.add(upperLeft);
                }
            }
            if(king.getPossibleRow() - z >= 0 && king.getPossibleColumn() + z < this.board.getWidth()) {
                Square destination = this.board.getSquare(king.getPossibleRow() - z, king.getPossibleColumn() + z);
                Move upperRight = new KingCaptureMove(king.getPiece(), king, destination);
                if(upperRight.isValid(this.board)) {
                    this.capture.add(upperRight);
                }
            }
            if(king.getPossibleRow() + z < this.board.getHeight() && king.getPossibleColumn() - z >= 0) {
                Square destination = this.board.getSquare(king.getPossibleRow() + z, king.getPossibleColumn() - z);
                Move lowerLeft = new KingCaptureMove(king.getPiece(), king, destination);
                if(lowerLeft.isValid(this.board)) {
                    this.capture.add(lowerLeft);
                }
            }
            if(king.getPossibleRow() + z < this.board.getHeight() && king.getPossibleColumn() + z < this.board.getWidth()) {
                Square destination = this.board.getSquare(king.getPossibleRow() + z, king.getPossibleColumn() + z);
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
        int z = 1; // Movement
        for(Square regular: alliedRegular) {
            if(regular.getPossibleRow() - z >= 0 && regular.getPossibleColumn() - z >= 0) {
                Square destination = this.board.getSquare(regular.getPossibleRow() - z, regular.getPossibleColumn() - z);
                Move upperLeft = new NormalRegularMove(regular.getPiece(), regular, destination);
                if (upperLeft.isValid(this.board)) {
                    this.normal.add(upperLeft);
                }
            }
            if(regular.getPossibleRow() - z >= 0 && regular.getPossibleColumn() + z < this.board.getWidth()) {
                Square destination = this.board.getSquare(regular.getPossibleRow() - z, regular.getPossibleColumn() + z);
                Move upperRight = new NormalRegularMove(regular.getPiece(), regular, destination);
                if(upperRight.isValid(this.board)) {
                    this.normal.add(upperRight);
                }
            }
        }
    }

    private void generateKingRegularMoves(String color) {
        ArrayList<Square> alliedKing = this.getPieces(color, "king");
        int z = 1; // Movement
        for(Square king: alliedKing) {
            if(king.getPossibleRow() - z >= 0 && king.getPossibleColumn() - z >= 0) {
                Square destination = this.board.getSquare(king.getPossibleRow() - z, king.getPossibleColumn() - z);
                Move upperLeft = new KingRegularMove(king.getPiece(), king, destination);
                if (upperLeft.isValid(this.board)) {
                    this.normal.add(upperLeft);
                }
            }
            if(king.getPossibleRow() - z >= 0 && king.getPossibleColumn() + z < this.board.getWidth()) {
                Square destination = this.board.getSquare(king.getPossibleRow() - z, king.getPossibleColumn() + z);
                Move upperRight = new KingRegularMove(king.getPiece(), king, destination);
                if(upperRight.isValid(this.board)) {
                    this.normal.add(upperRight);
                }
            }
            if(king.getPossibleRow() + z < this.board.getHeight() && king.getPossibleColumn() - z >= 0) {
                Square destination = this.board.getSquare(king.getPossibleRow() + z, king.getPossibleColumn() - z);
                Move lowerLeft = new KingRegularMove(king.getPiece(), king, destination);
                if(lowerLeft.isValid(this.board)) {
                    this.normal.add(lowerLeft);
                }
            }
            if(king.getPossibleRow() + z < this.board.getHeight() && king.getPossibleColumn() + z < this.board.getWidth()) {
                Square destination = this.board.getSquare(king.getPossibleRow() + z, king.getPossibleColumn() + z);
                Move lowerRight = new KingRegularMove(king.getPiece(), king, destination);
                if(lowerRight.isValid(this.board)) {
                    this.normal.add(lowerRight);
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
        if(color.equals("white")) {
            this.board.toggleBoard();
        }
        this.generateCaptureMoves(color);
        this.generateNormalMoves(color);

        if(color.equals("white")) {
            this.board.toggleBoard();
        }

        if(this.capture.size() > 0 || this.normal.size() > 0) {
            return true;
        }
        return false;
    }
}
