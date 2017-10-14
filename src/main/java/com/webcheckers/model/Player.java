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

    public String getUsername() {
        return this.username;
    }
}
