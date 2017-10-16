package com.webcheckers.appl;

import com.webcheckers.model.CheckersGame;

import java.util.logging.Logger;

public class GameCenter {
    private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());
    public CheckersGame getGame() {
        return new CheckersGame(this);
    }
    public PlayerServices newPlayerServices() {
        return new PlayerServices(this);
    }
}
