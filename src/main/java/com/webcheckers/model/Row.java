package com.webcheckers.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Logger;

public class Row {
    private int width;
    private String startColor;
    private Square[] row;

    private int index;

    public Row(int width, String startColor, int index){
        this.width=width;
        this.startColor=startColor;
        this.index=index;
        this.row=new Square[width];
        rowInit();
    }

    private void rowInit(){
        for(int i=0; i<width; i++){
            Piece piece;
            if(index<3){
                piece=new Piece("white","pawn");
            }else if (index >4){
                piece=new Piece("red","pawn");
            }else{
                piece=null;
            }
            if(startColor.equals("white")){
                if(i%2==0){
                    row[i]=new Square("white",piece,i);
                }else{
                    row[i]=new Square("black",piece,i);
                }
            }
            if(startColor.equals("black")){
                if(i%2==0){
                    row[i]=new Square("black",piece,i);
                }else{
                    row[i]=new Square("white",piece,i);
                }
            }
        }
    }

    public int getIndex(){
        return index;
    }

    public Piece removePiece(int col){
      return row[col].removePiece();
    }

    public void setPiece(Piece piece, int col){
      row[col].setPiece(piece);
    }

    public Square[] getRow(){
      return row;
    }

    public void reverse(){
      Collections.reverse(Arrays.asList(row));
    }

    public Square[] getRow(String opponent, String summoner){
      if(opponent.equals(summoner)){
          Square[] reverseRow= new Square[width];
          System.arraycopy( row, 0, reverseRow, 0, width );
          Collections.reverse(Arrays.asList(reverseRow));
          return reverseRow;
      }
      return row;
    }

    public boolean PiecePresent(int col){
        return row[col].hasPiece();
    }
}
