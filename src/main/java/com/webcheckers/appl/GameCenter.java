package com.webcheckers.appl;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import java.util.logging.Logger;

public class GameCenter {
    private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());
    public CheckersGame getGame(Player summoner, Player opp) {
        return new CheckersGame(this, summoner, opp);
    }
    public PlayerServices newPlayerServices() {
        return new PlayerServices(this);
    }
}
