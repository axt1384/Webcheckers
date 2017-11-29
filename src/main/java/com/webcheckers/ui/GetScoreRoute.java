package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import spark.*;

import java.util.logging.Logger;

import static com.webcheckers.ui.InterfaceVariable.*;
/**
 * The UI Controller to GET the Score page.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class GetScoreRoute implements Route {

    // ----------
    // Attributes
    // ----------

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
    private PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;

    // ------------
    // Constructors
    // ------------

    /**
     * Creates the Spark Route (UI controller) for the
     * {@code GET /score} HTTP request.
     *
     * @param templateEngine
     *      the HTMl template rendering engine
     * @param playerLobby
     *      the lobby housing the players (for sign out purposes)
     */
    public GetScoreRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;

        LOG.config("GetHomeRoute is initialized.");
    }

    // -------
    // Methods
    // -------

    /**
     * Render the WebCheckers Score Screen.
     * @param request
     *      the HTTP request
     * @param response
     *      the HTTP response
     * @return
     *      the rendered HTML for the Score page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetScoreRoute is invoked.");

        Session httpSession = request.session();

        if(httpSession.attribute(PLAYER_IN_GAME)) { // Queued for a Game
            response.redirect("/game");
        }
        else {
            httpSession.attribute(PLAYER_SERVICES, null); // Reset the Game Status for Next Game
            playerLobby.getUser(httpSession).setSummoner(false);
        }

        Map<String, Object> vm = new HashMap<>();

        vm.put(USERNAME, this.playerLobby.getUser(httpSession));
        vm.put(SCORE_MESSAGE, request.session().attribute(SCORE_MESSAGE));
        vm.put(TITLE, "Score");

        return templateEngine.render(new ModelAndView(vm, SCORE_NAME));
    }
}
