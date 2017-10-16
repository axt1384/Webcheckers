package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.PlayerLobby;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 * @author Anorwen - - - edc8230@rit.edu
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private PlayerLobby playerlobby;
  private final TemplateEngine templateEngine;

  /**
   * Create the Spark Route (UI controller) for the
   * {@code GET /} HTTP request.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, PlayerLobby playerlobby) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");

    this.templateEngine = templateEngine;
    this.playerlobby = playerlobby;

    LOG.config("GetHomeRoute is initialized.");
  }

  private String showPlayers(String viewingUser) {
    String result = "";
    ArrayList<String> list = this.playerlobby.getUsers();
    if(list.contains(viewingUser)) {
      list.remove(viewingUser);
    }
    for(String player: list) {
      result +="<li>" + player + "</li>\n";
    }
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");

    Map<String, Object> vm = new HashMap<>();

    if(this.playerlobby.getUser(request.session()) == null) {
      vm.put("username", ""); // Place Holder for User
    }
    else {
      vm.put("username", this.playerlobby.getUser(request.session()).toString());
    }


    vm.put("title", "Welcome!");
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}