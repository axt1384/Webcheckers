package com.webcheckers.ui;

// import com.sun.istack.internal.NotNull;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.ui.GetGameRoute;
import com.webcheckers.ui.GetHomeRoute;
import org.junit.Before;
import org.junit.Test;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Jesse Chen
 */
public class GetHomeRouteTest {

    private GetHomeRoute CuT;

    // mock objects
    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;

    @Before
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);
        response = mock(Response.class);
        playerLobby=mock(PlayerLobby.class);
    }

    /**
     * Tests the home page upon a new session
     */
    @Test
    public void new_session() {
        when(playerLobby.getUser(request.session())).thenReturn(null);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));
        CuT = new GetHomeRoute(engine,playerLobby);
        CuT.handle(request, response);
        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);
        @SuppressWarnings("unchecked")
        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals("Welcome!", vm.get("title"));
        assertEquals("", vm.get("gameError"));
        assertEquals("0", vm.get("numberUsers"));
        assertEquals("", vm.get("username"));
        assertEquals("<a href=/SignIn>Sign In</a>", vm.get("sign"));
        assertEquals("<p>Please Sign In to see players.</p>", vm.get("showPlayers"));
        assertEquals("home.ftl", myModelView.viewName);
    }

    /**
     * Tests home page on old session
     */
    @Test
    public void old_session(){
        Player player = new Player("Billybob");
        playerLobby.SignIn(request.session(),player);
        when(playerLobby.getUser(request.session())).thenReturn(player);
        when(request.session().attribute("inGame")).thenReturn(false);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));
        CuT = new GetHomeRoute(engine,playerLobby);
        CuT.handle(request, response);
        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);
        @SuppressWarnings("unchecked")
        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals("Welcome!", vm.get("title"));
        assertEquals("", vm.get("gameError"));
        assertEquals(playerLobby.getUser(request.session()).toString(), vm.get("username"));
        assertEquals("<a href=/SignedOut>Sign Out</a>", vm.get("sign"));
        assertEquals("<ul></ul>", vm.get("showPlayers"));
        assertEquals("home.ftl", myModelView.viewName);
    }

}
