package com.webcheckers.model;

import com.webcheckers.appl.GameCenter;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CheckersGame {
    private static final Logger LOG = Logger.getLogger(CheckersGame.class.getName());

    private Board board;
    private final GameCenter gameCenter;
    private int index=0;

    public CheckersGame(GameCenter gameCenter){
        this.gameCenter=gameCenter;
        this.board=new Board(8,8);
    }

    public Board getBoard(){
        return board;
    }

}
