package com.webcheckers.appl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import com.webcheckers.model.Player;
import spark.Session;

/**
 * The lobby where the players are inside. A player can sign in/sign out
 * through the lobby only.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class PlayerLobby {

    // ----------
    // Attributes
    // ----------
    private HashMap<String, Player> players; // Simply a List of Players
    private HashMap<String, Session> sessions; // Players have a Session
    private ArrayList<Player> occupied; // Players that are in Game

    // ------------
    // Constructors
    // ------------

    /**
     * Creates a new PlayerLobby. Begins with no players.
     */
    public PlayerLobby() {
        this.players = new HashMap();
        this.sessions = new HashMap();
    }

    // -------
    // Methods
    // -------

    /**
     * Attempts to sign in a player with a specific username. Player must have a valid username. Checks to see
     * if a Player is already using the username of a player (two players are equal if they have the same username).
     * @param player Player that is attempting to sign in.
     * @return String representing a message for the SignIn page. Empty String means success.
     */
    public synchronized String SignIn(Session session, Player player) {
        String message = "";

        switch (player.toString()) { // Username Not Allowed
            case "":
                return "<p>Please enter a username with at least one character.<p>";
        }
        if(player.toString().contains("\"")){
          return "<p>The username is not allowed to have any quotes.<p>";
        }
        if(message != "") {
            return "<p>The username " + message + " is not allowed.<p>";
        }

        if(players.values().contains(player)) { // Username in Use
            return "<p>The username '" + player.toString() + "' is already in use.";
        }
        if(message != "") {
            return "<p>The username " + message + " is not allowed.<p>";
        }
        if(message == "") {
            this.players.put(session.id(), player);
            this.sessions.put(player.toString(), session);
        }

        return message;
    }

    /**
     * Attempts to sign out a player with a specific username. Player must have a valid username. Checks to see
     * if the player is still logged in. The player should be logged in already, if this function returns false
     * then the player logged out incorrectly.
     * @param player Player that is attempting to sign out.
     * @return True if the player signed out successfully, false otherwise.
     */
    public boolean SignOut(Session session) {
        if(this.players.keySet().contains(session.id())) {
            Player player = this.players.get(session.id());
            this.players.remove(session.id());
            this.sessions.remove(player.toString());
            return true;
        }
        return false;
    }

    /**
     * Gets the Player of the provided session.
     * @param session Session of the current User.
     * @return Player if the current session exists in the key, null otherwise.
     */
    public Player getUser(Session session) {
        return this.players.get(session.id());
    }

    /**
     * Gets the session of the player.
     * @param player The player whose session is needed.
     * @return HTTP session of the player.
     */
    public Session getSession(Player player) {
        return this.sessions.get(player.toString());
    }

    /**
     * Gets a list of Strings of the current usernames being used.
     * @return ArrayList<String> of the usernames being used.
     */
    public ArrayList<String> getUsers() {
        ArrayList<String> result = new ArrayList<>();
        for(String session: this.players.keySet()) {
            result.add(this.players.get(session).toString());
        }
        return result;
    }

    /**
     * Adds the Player to the list of Players in game.
     * @param player Player to go into a game.
     * @return True if the Player exists and was added; false otherwise.
     */
    public boolean toGame(Player player) {
        if(!this.getUsers().contains(player.toString())) { // Player Does not Exist
            return false;
        }
        else if(this.occupied.contains(player)) { // Player was Already in Game
            return false;
        }
        else {
            this.occupied.add(player);
            return true;
        }
    }

    /**
     * Removes the Player from the list of Players in game.
     * @param player Player to exit a game.
     * @return True if the Player exists and was removed; false otherwise.
     */
    public boolean exitGame(Player player) {
        if(!this.getUsers().contains(player.toString())) { // Player Does not Exist
            return false;
        }
        else if(!this.occupied.contains(player)) { // Player is not in Game
            return false;
        }
        else {
            this.occupied.remove(player);
            return true;
        }
    }

    /**
     * Determines wheter or not a Player is in game (occupied).
     * @param player Player to check on.
     * @return True if the Player is in a game, false otherwise.
     */
    public boolean inGame(Player player) {
        return this.occupied.contains(player);
    }
}
