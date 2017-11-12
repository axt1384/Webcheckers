package com.webcheckers.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Logger;

public class Board {

    // ----------
    // Attributes
    // ----------

    private int width;
    private int height;
    private Row[] board;

    // ------------
    // Constructors
    // ------------

    public Board(int width, int height){
        this.width=width;
        this.height=height;
        board=new Row[height];
        boardInit();
    }


    // -------
    // Methods
    // -------

    /*
    Initializes the board
     */
    private void boardInit(){
        for(int i=0; i<height; i++){
            if(i%2==0){
                board[i]=new Row(width, "white",i);
            }else{
                board[i]=new Row(width,"black",i);
            }
        }
    }


    /**
    Returns the opponent's board in their perspective - reverses the board

    public Row[] getOppBoard(){
      Row[] reverseBoard=new Row[height];
      System.arraycopy( board, 0, reverseBoard, 0, height );
      Collections.reverse(Arrays.asList(reverseBoard));
      for(int i=0; i<height; i++){
        reverseBoard[i].reverse();
      }
      return reverseBoard;
    }
    */

    public void toggleBoard() {
        for(Row row: this.board) {
            Collections.reverse(Arrays.asList(row.getRow()));
            int i = 0;
            while(i < 8) {
                row.getRow()[i].toggleCoordinate();
                i++;
            }
        }
        Collections.reverse(Arrays.asList(board));
    }

    public Row[] getBoard(String opponent,String summoner){
      if(opponent.equals(summoner)){
        Row[] reverseBoard=new Row[height];
        System.arraycopy( board, 0, reverseBoard, 0, height );
        Collections.reverse(Arrays.asList(reverseBoard));
        return reverseBoard;
      }
      return board;
    }

    /*
    Sets the board
     */
    public void setBoard(Row[] board){
      this.board=board;
    }

    public Piece removePiece(int row, int col){
        return board[row].removePiece(col);
    }

    /*
    Returns the board
     */
    public Row[] getBoard(){
      return board;
    }

    /*
    Sets the piece at the square location
     */
    public void setPiece(Piece piece, int row, int col){
      board[row].setPiece(piece ,col);
    }

    /*
    Gets the row at the index
     */
    public Row getRow(int index){
      return board[index];
    }

    /*
    Checks if there's a piece at the square location
     */
    public boolean piecePresent(int row, int col){
        if(board[row].getRow()[col].hasPiece()){
            return true;
        }
        else return false;


    }

    public int getHeight() {
        return this.height;
    }

    public Square getSquare(int row, int col) {
        return this.getRow(row).getSquare(col);
    }

    public int getWidth() {
        return this.width;
    }
}
