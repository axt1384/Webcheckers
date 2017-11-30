package com.webcheckers.ui;


import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.ui.GetSignOutRoute;
import com.webcheckers.ui.GetHomeRoute;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import spark.*;

import java.util.Map;

public class PostSignInRouteTest {

    private Session session;
    private Request request;
    private TemplateEngine engine;
    private PlayerLobby lobby;
    private Response response;
    private GetSignInRoute signedIn;

    private PostSignInRoute CuT;

    private static final String TITLE = "Welcome!";
    private static final String NO_ERROR = "";
    private static final String IN_USE = "<p>The username 'ReadyPlayer1' is already in use.";
    private static final String NO_INPUT = "<p>Please enter a username with at least one character.<p>";
    private static final String INVALID_INPUT = "<p>The username \" is not allowed.<p>";


    @Before
    public void setup() {
        signedIn = mock(GetSignInRoute.class);
        session = mock(Session.class);
        request = mock(Request.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        lobby = mock(PlayerLobby.class);


        CuT = new PostSignInRoute(engine, lobby);
    }

    @Test
    public void PostSignInTest(){
        when(request.queryParams("username")).thenReturn("user");
        when(lobby.SignIn(session,new Player("user"))).thenReturn("");
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        this.CuT.handle(request, response);

        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);

        final Map<String,Object> vm = (Map<String,Object>) model;

        assertEquals("Welcome!", vm.get("title"));
        assertEquals("user",vm.get("username"));
        assertEquals(null, vm.get("gameError"));
        assertEquals("<ul></ul>", vm.get("showPlayers"));
        assertEquals("0", vm.get("numberUsers"));
        assertEquals("<a href=/SignedOut>Sign Out</a>", vm.get("sign"));

        assertEquals("home.ftl", myModelView.viewName);
    }

    @Test
    public void invalid(){
      when(request.queryParams("username")).thenReturn("user");
      when(lobby.SignIn(session,new Player("user"))).thenReturn("error");
      final MyModelAndView myModelView = new MyModelAndView();
      when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

      this.CuT.handle(request,response);

      final Object model = myModelView.model;
      assertNotNull(model);
      assertTrue(model instanceof Map);

      final Map<String,Object> vm = (Map<String,Object>) model;

      assertEquals("Welcome!", vm.get("title"));
      assertEquals("user",vm.get("username"));
      assertEquals("error",vm.get("signInMessage"));

      assertEquals("signin.ftl",myModelView.viewName);
    }
}
