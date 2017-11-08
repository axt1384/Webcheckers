package com.webcheckers.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Logger;

public class Row {

    // ----------
    // Attributes
    // ----------

    private int width; // Limit
    private String startColor;
    private Square[] row; // Series of Squares that Make up the Row
    private int index; // Number Representing Row

    // ------------
    // Constructors
    // ------------

    public Row(int width, String startColor, int index){
        this.width=width;
        this.startColor=startColor;
        this.index=index;
        this.row=new Square[width];
        rowInit();
    }

    // -------
    // Methods
    // -------

    /*
    Initializes the row array with square arrays and populates the squares with pieces
     */
    private void rowInit(){
        for(int i = 0; i < width; i++){

            Piece piece;
            if(index < 3){
                piece=new Piece("white","pawn");
            }else if (index > 4){
                piece=new Piece("red","pawn");
            }else{
                piece = null;
            }
            if(startColor.equals("white")){
                if(i%2 == 0){
                    piece = null;
                    row[i] = new Square("white",piece,i, this.index);
                }
                else{
                    row[i] = new Square("black",piece,i, this.index);
                }
            }
            if(startColor.equals("black")){
                if(i % 2 == 0){
                    row[i]=new Square("black",piece,i, this.index);
                }
                else{
                    piece = null;
                    row[i]=new Square("white",piece,i, this.index);
                }
            }
        }
    }

    /*
    Returns the index
     */
    public int getIndex(){
        return index;
    }

    /*
    Removes the piece at the row's column
     */
    public Piece removePiece(int col){
      return row[col].removePiece();
    }

    /*
    Sets the piece at the row's column
     */
    public void setPiece(Piece piece, int col){
      row[col].setPiece(piece);

    }

    public Square getSquare(int col) {
        return this.row[col];
    }


    /*
    Returns the row
     */
    public Square[] getRow(){
      return row;
    }

    /*
    Reverses the row
     */
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

}
