package com.webcheckers.appl;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import java.util.logging.Logger;

public class GameCenter {

    private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());
    private TurnAdministrator turnAdministrator;
    private Player summoner;
    private Player opponent;
    final static String SUMMONER_WON_MESSAGE = "The %s has won the Game!";
    final static String THE_OPPONENT_LOST = "The %s has lost the game.";



    public CheckersGame getGame(Player summoner, Player opp) {
        return new CheckersGame(this, summoner, opp);
    }


    public PlayerServices newPlayerServices() {
        return new PlayerServices(this);
    }

    public synchronized String getGameStatsMessage() {
        Player victor = this.turnAdministrator.isOver();
        if (victor != null && victor.isSummoner()) {
            String message =  String.format(SUMMONER_WON_MESSAGE,summoner)+String.format(THE_OPPONENT_LOST,opponent);
            return message;

        }
        else if(victor != null && !victor.isSummoner()){
            String message =  String.format(SUMMONER_WON_MESSAGE,opponent)+String.format(THE_OPPONENT_LOST,summoner);
            return message;
        }
        else {
            return "No one has won the game!";
        }
    }

}
