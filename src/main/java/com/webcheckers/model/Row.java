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
        //rowInit();
        emptyRow();
        if(this.index == 3) {
            this.row[4] = new Square ("black", new Piece("white", "pawn"), 4, this.index);
        }
        if(this.index == 4) {
            this.row[3] = new Square("black", new Piece("red", "pawn"), 3, this.index);
        }
        if(this.index == 1) {
          this.row[6] = new Square("black", new Piece("white", "pawn"), 3, this.index);
        }
    }

    // -------
    // Methods
    // -------

    /*
    Initializes the row array with square arrays and populates the squares with pieces
     */
    private void rowInit() {
        for (int i = 0; i < width; i++) {

            Piece piece;
            if (index < 3) {
                piece = new Piece("white", "pawn");
            } else if (index > 4) {
                piece = new Piece("red", "pawn");
            } else {
                piece = null;
            }
            if (startColor.equals("white")) {
                if (i % 2 == 0) {
                    piece = null;
                    row[i] = new Square("white", piece, i, this.index);
                } else {
                    row[i] = new Square("black", piece, i, this.index);
                }
            }
            if (startColor.equals("black")) {
                if (i % 2 == 0) {
                    row[i] = new Square("black", piece, i, this.index);
                } else {
                    piece = null;
                    row[i] = new Square("white", piece, i, this.index);
                }
            }
        }
    }

    /*
    Used to help create a simple end game board.
     */
    private void emptyRow() {
        for(int i = 0; i < width; i++){
            if(startColor.equals("white")){
                if(i%2 == 0){
                    row[i] = new Square("white",null,i, this.index);
                }
                else{
                    row[i] = new Square("black",null,i, this.index);
                }
            }
            if(startColor.equals("black")){
                if(i % 2 == 0){
                    row[i]=new Square("black",null,i, this.index);
                }
                else{
                    row[i]=new Square("white",null,i, this.index);
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
