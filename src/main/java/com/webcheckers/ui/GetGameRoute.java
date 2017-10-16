package com.webcheckers.ui;


import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerServices;
import com.webcheckers.model.CheckersGame;
import spark.*;

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
    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     */
    GetGameRoute(final TemplateEngine templateEngine, final GameCenter gameCenter) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.gameCenter=gameCenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String handle(Request request, Response response) {
        // retrieve the game object and start one if no game is in progress
        final Session httpSession = request.session();

        httpSession.attribute("playerServices", gameCenter.newPlayerServices());

        final PlayerServices playerServices =
                httpSession.attribute("playerServices");
        CheckersGame game = playerServices.currentGame();

        final Map<String, Object> vm = new HashMap<>();
        vm.put(BOARD, game.getBoard());
        // render the Game Form view
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
