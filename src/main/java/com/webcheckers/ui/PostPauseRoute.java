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

public class PostPauseRoute implements Route {

  private final Gson gson = new Gson();
  private static final Logger LOG = Logger.getLogger(PostPauseRoute.class.getName());
  public static boolean isPaused=false;
  /**
   * {@inheritDoc}
   */
  @Override
  public Object handle(Request request, Response response) {
    String paused=request.queryParams("paused");
    LOG.config(paused);
    if(paused.equals("false")){
      isPaused=true;
    }else{
      isPaused=false;
    }
    return gson.toJson(""+isPaused);
  }
}
