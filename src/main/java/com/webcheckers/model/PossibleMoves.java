package com.webcheckers.model;

import com.webcheckers.appl.TurnAdministrator;

/**
 * Represents all of the Possible Moves a Player may make.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class PossibleMoves {

    // ----------
    // Attributes
    // ----------

    private TurnAdministrator admin;

    // ------------
    // Constructors
    // ------------

    public PossibleMoves(TurnAdministrator admin) {
        this.admin = admin;
    }


    // -------
    // Methods
    // -------

    // These will Change TurnAdministrator
    public boolean hasCaptureMove() {
        return false;
    }

    public boolean hasMove() {
        return false;
    }

    public boolean isValidMove() {
        return false;
    }
}
