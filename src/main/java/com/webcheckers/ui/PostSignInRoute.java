package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;

import spark.*;

/**
 * The UI Handler to POST the Sign In page.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class PostSignInRoute implements Route {

    // ----------
    // Attributes
    // ----------
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    private final PlayerLobby playerlobby; // Shows who is Online.

    // ------------
    // Constructors
    // ------------

    /**
     * Create the Spark Route (UI handler) for the
     * {@code POST /} HTTP request.
     *
     * @param templateEngine
     *   the HTML template rendering engine.
     */
    public PostSignInRoute(final TemplateEngine templateEngine, PlayerLobby playerlobby) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.playerlobby = playerlobby;
        this.templateEngine = templateEngine;
        LOG.config("PostSignInRoute is initialized.");
    }

    public String addPlayersList(String viewingUser) {
        ArrayList<String> list = this.playerlobby.getUsers();
        if(list.contains(viewingUser)) {
            list.remove(viewingUser);
        }
        String result = "";
        for(String user: list) {
            result += "<li>" + user + "</li>";
        }
        return "<ul>" + result + "</ul>";
    }

    // -------
    // Methods
    // -------

    private String showNumber() {
        return Integer.toString(this.playerlobby.getUsers().size());
    }

    /**
     * Render the WebCheckers lobby page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   the rendered HTML for the Home page.
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignInRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");
        vm.put("username", request.queryParams("username"));


        Player newuser = new Player(request.queryParams("username"));
        if(!playerlobby.SignIn(request.session(), newuser)) { // UserName is Already Taken
            return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
        }
        vm.put("showPlayers", this.addPlayersList(request.queryParams("username")));
        vm.put("numberUsers", this.showNumber());
        return templateEngine.render(new ModelAndView(vm , "home.ftl"));
    }
}
