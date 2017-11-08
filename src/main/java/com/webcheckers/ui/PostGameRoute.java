package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.PlayerServices;

import com.webcheckers.appl.TurnAdministrator;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import spark.*;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PostGameRoute implements Route {

    static final String VIEW_NAME = "game.ftl";
    static final String BOARD="board";
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private PlayerLobby playerlobby;
    private static final Logger LOG = Logger.getLogger(WebServer.class.getName());
    private static int counter=0;
    private TurnAdministrator turnAdministrator = null;
    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     */
    PostGameRoute(final TemplateEngine templateEngine, final GameCenter gameCenter, PlayerLobby playerlobby) {
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

        final Session httpSession = request.session();
        String turn=request.queryParams("turn");
        LOG.config("TURN STATUS: "+turn);
        String summoner=request.queryParams("summoner");
        String move= request.queryParams("move");
        String oldPos= request.queryParams("oldPos");
        LOG.config("params:"+request.queryParams());
        String capture=request.queryParams("capture");
        final Map<String, Object> vm = new HashMap<>();
        final PlayerServices playerServices = httpSession.attribute("playerServices");
        CheckersGame game = playerServices.currentGame();

        game.updateBoard(move, oldPos,capture);
        if(summoner.equals(playerlobby.getUser(httpSession).toString())){
          vm.put("opponent", game.getOpp().toString());
          vm.put("summoner", game.getSummoner().toString());
        }else{
          vm.put("opponent", game.getSummoner().toString());
          vm.put("summoner", game.getSummoner().toString());
        }
        vm.put(BOARD, game.getBoard());
        LOG.config("VALIDATE TURN STATUS: "+game.isSummonerTurn());
        game.endTurn();

        if(this.turnAdministrator == null) {
            this.turnAdministrator = new TurnAdministrator(game.getSummoner(), game.getOpp(), game);
        }
        Player victor = this.turnAdministrator.isOver();
        if(victor != null) {
            System.out.println(victor);
        }

        vm.put("summonerTurn",game.isSummonerTurn());
        response.redirect("/game");
        return null;
    }
}
