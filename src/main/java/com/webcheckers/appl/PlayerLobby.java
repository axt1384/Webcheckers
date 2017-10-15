package com.webcheckers.appl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import com.webcheckers.model.Player;

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
    private ArrayList<Player> players; // Simply a List of Players

    // ------------
    // Constructors
    // ------------

    /**
     * Creates a new PlayerLobby. Begins with no players.
     */
    public PlayerLobby() {
        this.players = new ArrayList<Player>();
    }

    // -------
    // Methods
    // -------

    /**
     * Attempts to sign in a player with a specific username. Player must have a valid username. Checks to see
     * if a Player is already using the username of a player (two players are equal if they have the same username).
     * @param player Player that is attempting to sign in.
     * @return True if the player signed in successfully, false otherwise.
     */
    public synchronized boolean SignIn(Player player) {
        if(players.contains(player)) {
            return false;
        }
        this.players.add(player);
        return true;
    }

    /**
     * Attempts to sign out a player with a specific username. Player must have a valid username. Checks to see
     * if the player is still logged in. The player should be logged in already, if this function returns false
     * then the player logged out incorrectly.
     * @param player Player that is attempting to sign out.
     * @return True if the player signed out successfully, false otherwise.
     */
    public boolean SignOut(Player player) {
        if(players.contains(player)) {
            players.remove(player);
            return true;
        }
        return false;
    }
}
