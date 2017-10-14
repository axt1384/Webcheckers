package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

/**
 * The UI Handler to POST the Sign In page.
 *
 * @author <a href='mailto:edc8230@rit.edu'>Elijah Cantella</a>
 */
public class PostSignInRoute implements Route {
    // ----------
    // Attributes
    // ----------
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

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
    public PostSignInRoute(final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

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
        LOG.finer("GetSignInRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!"); // What is this?
        return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
    }
}
