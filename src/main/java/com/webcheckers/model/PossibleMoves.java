package com.webcheckers.model;

import com.webcheckers.appl.TurnAdministrator;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

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

    public  Move[] getLegalMoves(Piece player){

        if(!Objects.equals(player.getColor(),"Red") || !Objects.equals(player.getColor(),"White")){
            return null;
        }
        Vector possiblemoves = new Vector();
        Move[] Moves = new Move[possiblemoves.size()];
        return Moves;

    }
}
