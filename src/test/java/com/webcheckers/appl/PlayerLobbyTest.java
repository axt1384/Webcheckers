package com.webcheckers.appl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.Session;

import java.util.ArrayList;

/**
 * Tests PlayerLobby which is currently both and application and model tier class.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class PlayerLobbyTest {

    private PlayerLobby playerLobby; // The PlayerLobby currently acts as both an Application and Model Tier Class
    private Session session1, session2; // Sessions for Different Users
    private String username1, username2; // Usernames for Corresponding Sessions

    private static final String USER_NOT_FOUND = "The requested Player that should exist was not found.";
    private static final String USER_FOUND = "The requested Player that should not exist was found.";
    private static final String WRONG_SIZE = "The PlayerLobby is either missing or has extra Players.";

    /**
     * Initializes the PlayerLobby, and two players (both their usernames and sessions).
     */
    @Before
    public void testSetup() {
        playerLobby = new PlayerLobby();
        assertNotNull(playerLobby);

        username1 = "FirstPlayer";
        username2 = "SecondPlayer";

        // Need to Use mock as Session's Constructor is Private
        session1 = mock(Session.class);
        when(session1.id()).thenReturn("1");
        session2 = mock(Session.class);
        when(session2.id()).thenReturn("2");
    }

    /**
     * Tests that there are no users signed in.
     */
    @Test
    public void test_no_players() {
        ArrayList<String> players = playerLobby.getUsers();
        assertEquals("PlayerLobby is meant to be empty.", 0, players.size());
        assertEquals(USER_FOUND, null, this.playerLobby.getUser(this.session1));
    }

    /**
     * Sign in one player and confirm that they exist.
     */
    @Test
    public void test_sign_in_1_players() {
        Player player1 = new Player(this.username1);
        this.playerLobby.SignIn(this.session1, player1);

        assertEquals(USER_NOT_FOUND, player1, playerLobby.getUser(this.session1));
        assertEquals(WRONG_SIZE, 1, this.playerLobby.getUsers().size());
    }

    /**
     * Sign out the player and make sure they no longer exist.
     */
    @Test
    public void test_sign_out_player1() {
        Player player1 = new Player(this.username1);
        this.playerLobby.SignIn(this.session1, player1);

        assertEquals(true, this.playerLobby.SignOut(this.session1));

        assertEquals(WRONG_SIZE, 0, this.playerLobby.getUsers().size());
        assertEquals(USER_FOUND, null, this.playerLobby.getUser(this.session1));
    }

    /**
     * Sign in multiple users at the same time, make sure both exist.
     */
    @Test
    public void test_sign_in_2_players() {
        Player player1 = new Player(this.username1);
        Player player2 = new Player(this.username2);

        this.playerLobby.SignIn(this.session1, player1);
        this.playerLobby.SignIn(this.session2, player2);

        assertEquals(WRONG_SIZE, 2, this.playerLobby.getUsers().size());
        assertEquals(USER_NOT_FOUND, player1, playerLobby.getUser(this.session1));
        assertEquals(USER_NOT_FOUND, player2, playerLobby.getUser(this.session2));
    }

    /**
     * Sign out each player one at a time. They should no longer exist after being signed out.
     */
    @Test
    public void test_sign_out_players() {
        // Sign Players In
        Player player1 = new Player(this.username1);
        Player player2 = new Player(this.username2);

        this.playerLobby.SignIn(this.session1, player1);
        this.playerLobby.SignIn(this.session2, player2);

        // Sign Out Player 1
        assertEquals(true, this.playerLobby.SignOut(this.session1));

        // Was Only Player 1 Signed Out?
        assertEquals(WRONG_SIZE, 1, this.playerLobby.getUsers().size());
        assertEquals(USER_FOUND, null, this.playerLobby.getUser(this.session1));
        assertEquals(USER_NOT_FOUND, new Player(this.username2), this.playerLobby.getUser(this.session2));

        // Sign Out Player 2, Try 1
        assertEquals(false, this.playerLobby.SignOut(this.session1));
        assertEquals(true, this.playerLobby.SignOut(this.session2));

        // Is the Player Lobby at its Original State?
        assertEquals(WRONG_SIZE, 0, this.playerLobby.getUsers().size());
        assertEquals(USER_FOUND, null, this.playerLobby.getUser(this.session2));
    }

    /**
     * Checks to see if invalid usernames return an appropriate string (error).
     */
    @Test
    public void test_sign_in_errors() {
        this.playerLobby.SignIn(this.session1, new Player(this.username1));

        // Already in Use
        String error = this.playerLobby.SignIn(this.session2, new Player(this.username1));
        assertEquals("<p>The username '" + this.username1 + "' is already in use.", error);

        // Invalid Username
        error = this.playerLobby.SignIn(this.session2, new Player("\""));
        assertEquals("<p>The username \" is not allowed.<p>", error);

        // No Username
        error = this.playerLobby.SignIn(this.session2, new Player(""));
        assertEquals("<p>Please enter a username with at least one character.<p>", error);
    }

    /**
     * Check and see that we get the proper sessions.
     */
    @Test
    public void test_get_sessions() {
        Player player1 = new Player(this.username1);
        Player player2 = new Player(this.username2);

        this.playerLobby.SignIn(this.session1, player1);
        this.playerLobby.SignIn(this.session2, player2);

        assertEquals(this.session1, this.playerLobby.getSession(player1));
        assertEquals(this.session2, this.playerLobby.getSession(player2));
    }

    /**
     * Make sure getUsers() return the right strings.
     */
    @Test
    public void test_get_users() {
        // Sign Players In
        Player player1 = new Player(this.username1);
        Player player2 = new Player(this.username2);

        this.playerLobby.SignIn(this.session1, player1);
        this.playerLobby.SignIn(this.session2, player2);

        ArrayList<String> users = this.playerLobby.getUsers();
        for(String username: users) {
            if(!(username.equals(this.username1) || username.equals(this.username2))) {
                assertEquals(USER_FOUND, "", username);
            }
        }
    }
}
