package com.webcheckers.model;

/**
 * Represents a Player in the WebCheckers application.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class Player {

    // ----------
    // Attributes
    // ----------
    private String username; // A Player only has a Username
    private boolean isSummoner;
    // ------------
    // Constructors
    // ------------

    /**
     * Creates a new Player provided a username to represent them.
     * @param username String that represents the player.
     */
    public Player(String username){
        this.username=username;
        this.isSummoner=false;
    }
    public Player(String username, boolean isSummoner) {
        this.username = username;
        this.isSummoner=isSummoner;
    }

    // -------
    // Methods
    // -------

    public boolean isSummoner(){
      return isSummoner;
    }

    public void setSummoner(boolean status){
        this.isSummoner=status;
    }

    public String toString() {
        return this.username;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o Object being compared to.
     * @return True if the object is a Player and they have the same
     *          username, false otherwise.
     */
    public boolean equals(Object o) {
        if(o instanceof Player) {
            if(((Player) o).username.equals(this.username)) {
                return true;
            }
        }
        return false;
    }
}
