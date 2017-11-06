package com.webcheckers.model;

/**
 * Created by yoits on 10/15/2017.
 */
public class Piece {

    // ----------
    // Attributes
    // ----------

    private String color;
    private String type;

    // ------------
    // Constructors
    // ------------

    public Piece(String color, String type){
        this.color=color;
        this.type=type;
    }

    // -------
    // Methods
    // -------

    public String getType(){
        return type;
    }

    public String getColor(){
        return color;
    }
}
