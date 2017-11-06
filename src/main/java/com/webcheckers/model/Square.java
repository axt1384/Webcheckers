package com.webcheckers.model;
import java.util.logging.Logger;

public class Square {

    private int index;
    private String color;
    private Piece piece;


    public Square(String color, Piece piece, int index){
        this.index=index;
        this.piece=piece;
        this.color=color;
    }

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

    public int getIndex(){
        return index;
    }
}
