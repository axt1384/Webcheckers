package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.PlayerServices;

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
        // retrieve the game object and start one if no game is in progress
        final Session httpSession = request.session();
        String move= request.queryParams("move");
        String oldPos= request.queryParams("oldPos");
        final Map<String, Object> vm = new HashMap<>();
        final PlayerServices playerServices = httpSession.attribute("playerServices");
        CheckersGame game = playerServices.currentGame();
        game.updateBoard(move, oldPos);
        String turn=request.queryParams("turn");
        LOG.config("bounter"+counter+"");
        counter++;
        if(turn.equals("true")){
          LOG.config("turn status: "+turn);
          vm.put("opponent", game.getOpp().toString());
          vm.put("summoner", game.getSummoner().toString());
        }else{
          LOG.config("turn status: balse");
          vm.put("opponent", game.getSummoner().toString());
          vm.put("summoner", game.getSummoner().toString());
        }
        game.endTurn();
        vm.put(BOARD, game.getBoard());
        vm.put("summonerTurn",game.isSummonerTurn());
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
