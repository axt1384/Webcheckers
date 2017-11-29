package com.webcheckers.ui;

import com.google.gson.Gson;
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

public class PostSubmitRoute implements Route {

    private final Gson gson=new Gson();
    private static final Logger LOG = Logger.getLogger(PostChatRoute.class.getName());
    /**
     * {@inheritDoc}
     */
    @Override
    public Object handle(Request request, Response response) {
      final Session httpSession = request.session();
      String move= request.queryParams("move");
      String oldPos= request.queryParams("oldPos");
      String capture = request.queryParams("capture");
      final PlayerServices playerServices = httpSession.attribute("playerServices");
      CheckersGame game = playerServices.currentGame();
      game.updateBoard(move, oldPos,capture);
      game.endTurn();
      response.redirect("/game");
      return gson.toJson("howdy");
    }
}
