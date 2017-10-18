package com.webcheckers.ui;


import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.PlayerServices;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameRoute implements Route {

    // Values used in the view-model map for rendering the game view.
    //static final String GAME_BEGINS_ATTR = "isFirstGuess";
    //static final String GUESSES_LEFT_ATTR = "guessesLeft";
    //static final String TITLE = "Number Guess Game";
    static final String VIEW_NAME = "game.ftl";
    static final String BOARD="board";
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private PlayerLobby playerlobby;


    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     */
    GetGameRoute(final TemplateEngine templateEngine, final GameCenter gameCenter, PlayerLobby playerlobby) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.playerlobby = playerlobby;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String handle(Request request, Response response) {
        // retrieve the game object and start one if no game is in progress
        final Session httpSession = request.session();

        httpSession.attribute("playerServices", gameCenter.newPlayerServices());
        final Map<String, Object> vm = new HashMap<>();

        String enemyName = request.queryParams("opponent");
        if(enemyName == null) { // This Session was summoned.
            final PlayerServices playerServices =
                    httpSession.attribute("playerServices");
            CheckersGame game = playerServices.currentGame();
            vm.put(BOARD, game.getBoard());
            vm.put("opponent","");
            // render the Game Form view
            return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
        }
        else {
            Player opponent = new Player(enemyName);
            Session opponentSession = this.playerlobby.getSession(opponent);
            if (opponentSession.attribute("inGame")) { // This Player is Already in a Match
                vm.put("gameError", "<p>The player " + opponent.toString() + " is already in game; please wait or choose" +
                        "another opponent.</p>");
                vm.put("username", this.playerlobby.getUser(httpSession).toString());
                vm.put("sign", "<a href=/SignedOut>Sign Out</a>");
                vm.put("showPlayers", GetHomeRoute.addPlayersList(playerlobby.getUser(httpSession).toString(), playerlobby));
                vm.put("numberUsers", GetHomeRoute.showNumber(playerlobby));
                vm.put("title", "Welcome!");
                return templateEngine.render(new ModelAndView(vm, "home.ftl"));
            } else {
                httpSession.attribute("inGame", true);
                opponentSession.attribute("inGame", true);

                final PlayerServices playerServices =
                        httpSession.attribute("playerServices");
                CheckersGame game = playerServices.currentGame();
                vm.put(BOARD, game.getBoard());
                vm.put("opponent", opponent.toString());
                // render the Game Form view
                return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
            }
        }
    }
}
