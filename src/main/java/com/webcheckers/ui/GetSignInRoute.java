package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static com.webcheckers.ui.InterfaceVariable.*;

import spark.*;

/**
 * The UI Controller to GET the Sign In page.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class GetSignInRoute implements Route {

    // ----------
    // Attributes
    // ----------

    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());
    private final TemplateEngine templateEngine;

    // ------------
    // Constructors
    // ------------

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /} HTTP request.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     * @param templateEngine
     */
    public GetSignInRoute(final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;

        LOG.config("GetSignInRoute is initialized.");
    }

    // -------
    // Methods
    // -------

    /**
     * Render the WebCheckers Sign in page.
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
        LOG.finer("GetSignInRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();

        vm.put(TITLE, "Welcome!");
        vm.put(SIGN_IN_MESSAGE, "");

        return templateEngine.render(new ModelAndView(vm , SIGN_IN_NAME));
    }
}
