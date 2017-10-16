package com.webcheckers.model;

public class Board {

    private int width;
    private int height;

    private Row[] board;

    public Board(int width, int height){
        this.width=width;
        this.height=height;
        board=new Row[height];
        boardInit();
    }

    private void boardInit(){
        for(int i=0; i<height; i++){
            if(i%2==0){
                board[i]=new Row(width, "white",i);
            }else{
                board[i]=new Row(width,"black",i);
            }
        }
    }

    public Row[] getBoard(){
        return board;
    }


}
