package com.webcheckers.appl;


import com.webcheckers.model.CheckersGame;

public class PlayerServices {
    private CheckersGame game;
    private final GameCenter gameCenter;

    PlayerServices(GameCenter gameCenter) {
        game = null;
        this.gameCenter = gameCenter;
    }

    public synchronized CheckersGame currentGame() {
        if(game == null) {
            game = gameCenter.getGame();
        }
        return game;
    }
}
