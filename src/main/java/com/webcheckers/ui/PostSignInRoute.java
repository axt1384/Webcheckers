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

    // -------
    // Methods
    // -------

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
        final Session httpSession = request.session();

        vm.put("title", "Welcome!");
        vm.put("username", request.queryParams("username"));


        Player newuser = new Player(request.queryParams("username"));
        String signMessage = playerlobby.SignIn(httpSession, newuser);
        if(!signMessage.equals("")) { // Username is Invalid
            vm.put("signInMessage", signMessage);
            return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
        }

        // Signed In Successfully, Now Go to Home Page
        httpSession.attribute("inGame", false);

        vm.put("gameError", "");
        vm.put("showPlayers", GetHomeRoute.addPlayersList(request.queryParams("username"), this.playerlobby));
        vm.put("numberUsers", GetHomeRoute.showNumber(this.playerlobby));
        vm.put("sign", "<a href=/SignedOut>Sign Out</a>");
        return templateEngine.render(new ModelAndView(vm , "home.ftl"));
    }
}
