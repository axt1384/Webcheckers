package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.ui.GetSignOutRoute;
import com.webcheckers.ui.GetHomeRoute;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import spark.*;

import java.util.HashMap;

public class GetSignOutRouteTest
{
  private Session session;
  private Request request;
  private TemplateEngine engine;
  private PlayerLobby lobby;
  private Response response;

  //component under test
  private GetSignOutRoute CuT;

  @Before
  public void setup()
  {
    session = mock(Session.class);
    request = mock(Request.class);
    when(request.session()).thenReturn(session);
    response = mock(Response.class);
    engine = mock(TemplateEngine.class);
    lobby = mock(PlayerLobby.class);

    CuT = new GetSignOutRoute(engine, lobby);
  }

  @Test
  public void signOutTest(){

    final MyModelAndView myModelView = new MyModelAndView();
    when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

    CuT.handle(request, response);

    final Object model = myModelView.model;

    assertNotNull(model);
    assertTrue(model instanceof HashMap);

    HashMap<String, Object> vm = (HashMap<String, Object>) model;

    assertEquals("username should have no value", "", vm.get("username"));
    assertEquals("title should have value 'Welcome!'", "Welcome!", vm.get("title"));
    assertEquals("showPlayers should have value '<p>Please Sign In to see players.</p>'",
            "<p>Please Sign In to see players.</p>", vm.get("showPlayers"));
    assertEquals(GetHomeRoute.showNumber(lobby) , vm.get("numberUsers"));
    assertEquals("sign should have value '<a href=/SignIn>Sign In</a>'", "<a href=/SignIn>Sign In</a>",
            vm.get("sign"));
    assertEquals("gameError should have no value", "", vm.get("gameError"));
  }

}
