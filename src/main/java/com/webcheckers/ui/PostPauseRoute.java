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
  public static String playerPaused="";
  /**
   * {@inheritDoc}
   */
  @Override
  public Object handle(Request request, Response response) {
    String paused=request.queryParams("paused");
    String player=request.queryParams("view");
    LOG.config(paused);
    if(paused.equals("false")){
      isPaused=true;
    }else{
      isPaused=false;
    }
    if(player.equals("true")){
      playerPaused="red";
    }else{
      playerPaused="white";
    }
    return gson.toJson(""+isPaused+","+playerPaused);
  }
}
