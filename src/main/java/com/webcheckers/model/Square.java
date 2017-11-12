package com.webcheckers.model;
import java.util.logging.Logger;

public class Square {

    // ----------
    // Attributes
    // ----------

    private int column;
    private int row;
    private String color;
    private Piece piece;

    // ------------
    // Constructors
    // ------------

    public Square(String color, Piece piece, int column, int row){
        this.row = row;
        this.column = column;
        this.piece = piece;
        this.color = color;
    }

    // -------
    // Methods
    // -------

    /*
    Returns the color of the square
     */
    public String getSquare(){
        return color;
    }

    /*
    Returns true if there's a piece on the square, false if not
     */
    public boolean hasPiece(){
        return piece != null;
    }

    /*
    Returns the piece at the square
     */
    public Piece getPiece(){
      return piece;
    }

    /*
    Sets a piece at the square
     */
    public void setPiece(Piece piece){
      this.piece = piece;
    }

    /*
    Removes the piece on the square and returns it
     */
    public Piece removePiece(){
      Piece p = this.piece;
      this.piece = null;
      return p;
    }

    /*
    Returns the piece type on the square
     */
    public String getPieceType(){
        return piece.getType();
    }

    /*
    Returns the piece color on the square
     */
    public String getPieceColor(){
        return piece.getColor();
    }

    /*
    Returns if the square is a valid square(black)
     */
    public boolean isValid(){
        return color.equals("black");
    }

    public int getRow(){
        return this.row;
    }

    // Get Possible is used due to issues with Algorithm
    public int getPossibleRow() {
        return this.row;
    }

    public int getPossibleColumn() {
        return this.column;
    }

    public void toggleCoordinate() {
        this.toggleColumn();
        this.toggleRow();
    }

    private void toggleColumn() {
        switch (this.column) {
            case 0:
                this.column = 7;
                break;
            case 1:
                this.column = 6;
                break;
            case 2:
                this.column = 5;
                break;
            case 3:
                this.column = 4;
                break;
            case 4:
                this.column = 3;
                break;
            case 5:
                this.column = 2;
                break;
            case 6:
                this.column = 1;
                break;
            case 7:
                this.column = 0;
                break;
        }
    }

    private void toggleRow() {
        switch (this.row) {
            case 0:
                this.row = 7;
                break;
            case 1:
                this.row = 6;
                break;
            case 2:
                this.row = 5;
                break;
            case 3:
                this.row = 4;
                break;
            case 4:
                this.row = 3;
                break;
            case 5:
                this.row = 2;
                break;
            case 6:
                this.row = 1;
                break;
            case 7:
                this.row = 0;
                break;
        }

    }

    public int getColumn() { return this.column; }
}
