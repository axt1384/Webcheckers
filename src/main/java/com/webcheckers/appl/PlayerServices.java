package com.webcheckers.appl;


import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
public class PlayerServices {
    private CheckersGame game;
    private final GameCenter gameCenter;

    PlayerServices(GameCenter gameCenter) {
        this.game = null;
        this.gameCenter = gameCenter;
    }

    public synchronized CheckersGame currentGame() {
        return this.game;
    }

    public synchronized CheckersGame newGame(Player summoner, Player opp){
          this.game = gameCenter.getGame(summoner, opp);
          return this.game;
    }

}
