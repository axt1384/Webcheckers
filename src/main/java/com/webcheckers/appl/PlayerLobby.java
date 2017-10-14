package com.webcheckers.appl;

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
    private HashMap<Player, Boolean> players;

    // ------------
    // Constructors
    // ------------

    public PlayerLobby() {
        this.players = new HashMap<>();
    }

    // -------
    // Methods
    // -------

    public boolean signIn(Player player) {
        this.players.get(player);
    }
}
