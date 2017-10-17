package com.webcheckers.ui;

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
 * Created by cante on 10/15/2017.
 */
public class GetSignOutRoute implements Route {

    // ----------
    // Attributes
    // ----------

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
    private PlayerLobby playerlobby;
    private final TemplateEngine templateEngine;

    // ------------
    // Constructors
    // ------------

    public GetSignOutRoute(final TemplateEngine templateEngine, PlayerLobby playerlobby) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;
        this.playerlobby = playerlobby;

        LOG.config("GetSignOutRoute is initialized.");
    }

    // -------
    // Methods
    // -------

    /**
     * Render the WebCheckers Home page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSignOutRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();

        this.playerlobby.SignOut(request.session()); // SignOut
        vm.put("username", ""); // No Longer Have Username

        vm.put("title", "Welcome!");

        vm.put("showPlayers", "<p>Please Sign In to see players.</p>");
        vm.put("numberUsers", GetHomeRoute.showNumber(this.playerlobby));

        vm.put("signURL", "/SignIn");
        vm.put("signLabel", "Sign In");

        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }
}

