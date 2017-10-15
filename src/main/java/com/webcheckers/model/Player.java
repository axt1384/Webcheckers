package com.webcheckers.model;

/**
 * Created by cante on 10/14/2017.
 */
public class Player {

    // ----------
    // Attributes
    // ----------
    private String username;

    // ------------
    // Constructors
    // ------------

    public Player(String username) {
        this.username = username;
    }

    // -------
    // Methods
    // -------

    public boolean equals(Object o) {
        if(o instanceof Player) {
            if(((Player) o).username == this.username) {
                return true;
            }
        }
        return false;
    }
}
