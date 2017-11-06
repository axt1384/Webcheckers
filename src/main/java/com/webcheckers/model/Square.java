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

    public Square(String color, Piece piece, int row, int column){
        this.column = column;
        this.row = row;
        this.piece = piece;
        this.color = color;
    }

    // -------
    // Methods
    // -------

    public String getSquare(){
        return color;
    }

    public boolean hasPiece(){
        return piece!=null;
    }

    public Piece getPiece(){
      return piece;
    }

    public void setPiece(Piece piece){
      this.piece=piece;
    }

    public Piece removePiece(){
      Piece p=this.piece;
      this.piece=null;
      return p;
    }

    public String getPieceType(){
        return piece.getType();
    }

    public String getPieceColor(){
        return piece.getColor();
    }

    public boolean isValid(){
        return color.equals("black");
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn() { return this.column; }
}
