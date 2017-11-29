package com.webcheckers.ui;

import com.google.gson.Gson;
import spark.*;
import java.util.logging.Logger;

import static com.webcheckers.ui.PostPauseRoute.isPaused;

public class GetPauseRoute implements Route {

  private final Gson gson = new Gson();
  private static final Logger LOG = Logger.getLogger(PostPauseRoute.class.getName());
  /**
   * {@inheritDoc}
   */
  @Override
  public Object handle(Request request, Response response) {
    return gson.toJson(""+isPaused);
  }
}
