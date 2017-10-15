package com.webcheckers.appl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import com.webcheckers.model.Player;

/**
 * Created by cante on 10/14/2017.
 */
public class PlayerLobby {

    // ----------
    // Attributes
    // ----------
    private ArrayList<Player> players;

    // ------------
    // Constructors
    // ------------

    public PlayerLobby() {
        this.players = new ArrayList<Player>();
    }

    // -------
    // Methods
    // -------

    public synchronized boolean signIn(Player player) {
        if(players.contains(player)) {
            return false;
        }
        this.players.add(player);
        return true;
    }
}
