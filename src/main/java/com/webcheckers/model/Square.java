package com.webcheckers.model;

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
