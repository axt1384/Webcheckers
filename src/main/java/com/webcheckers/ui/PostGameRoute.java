package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.PlayerServices;

import com.webcheckers.appl.TurnAdministrator;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import spark.*;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static com.webcheckers.ui.InterfaceVariable.*;

public class PostGameRoute implements Route {

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private static final Logger LOG = Logger.getLogger(WebServer.class.getName());
    private final PlayerLobby playerLobby;


    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param templateEngine The {@link TemplateEngine} used for rendering page HTML.
     */
    PostGameRoute(final TemplateEngine templateEngine, final GameCenter gameCenter, PlayerLobby playerlobby) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.playerLobby = playerlobby;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String handle(Request request, Response response) {

        final Session httpSession = request.session();

        String done = request.queryParams(FORFEIT);
        if(done.equals("done")) {
            request.session().attribute(SCORE_MESSAGE, "you have forfeited the match.");
            Player op = new Player(request.session().attribute(OPPONENT));
            playerLobby.getSession(op).attribute(PLAYER_IN_GAME, false);
            response.redirect("/score");
            httpSession.attribute(PLAYER_IN_GAME, false);
            return null;
        }
        String move = request.queryParams(MOVE);
        String oldPos = request.queryParams(OLD_POSISTION);
        String capture = request.queryParams(CAPTURE);

        final PlayerServices playerServices = httpSession.attribute(PLAYER_SERVICES);
        CheckersGame game = playerServices.currentGame();

        game.updateBoard(move, oldPos, capture);
        game.endTurn();

        Map<String, Object> vm = new HashMap<>();
        vm.put(SUMMONER_TURN, game.isSummonerTurn());

        response.redirect("/game");
        return null;
    }
}
