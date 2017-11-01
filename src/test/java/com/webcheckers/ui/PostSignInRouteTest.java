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

import java.util.HashMap;

/**
 * Created by jay on 10/31/17.
 */
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

        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));
        final Object model = myModelView.model;

        HashMap<String, Object> vm = (HashMap<String, Object>) model;
        this.CuT.handle(this.request, this.response);

        assertNotNull(model);
        assertTrue(model instanceof HashMap);



        assertEquals("title should have value 'Welcome!'", "Welcome!", vm.get("title"));
        assertNotEquals(NO_INPUT, null, vm.get("signInMessage"));


    }


}
