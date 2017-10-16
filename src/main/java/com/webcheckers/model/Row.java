package com.webcheckers.model;

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

    public Square[] getRow(){
        return row;
    }
}
