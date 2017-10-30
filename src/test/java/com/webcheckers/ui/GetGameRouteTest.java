package com.webcheckers.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.PlayerServices;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import com.webcheckers.ui.GetGameRoute;

/**
 * The unit test suite for the {@link GetGameRoute} component.
 *
 */
public class GetGameRouteTest{
  /**
   * The component under test CuT.
   */
  private GetGameRoute CuT;

  private Request request;
  private Session session;
  private Session sessionOpp;
  private Response response;
  private TemplateEngine engine;
  private GameCenter gameCenter;
  private PlayerLobby playerLobby;

  /**
   * setup the new mock objects for each test
   */
   @Before
   public void setup(){
     request = mock(Request.class);
     session = mock(Session.class);
     sessionOpp = mock(Session.class);
     when(request.session()).thenReturn(session);
     response = mock(Response.class);
     engine = mock(TemplateEngine.class);
     gameCenter = new GameCenter();
     playerLobby = mock(PlayerLobby.class);

     //create a CuT for the tests
     CuT = new GetGameRoute(engine, gameCenter, playerLobby);
   }

   /**
    * Test that the Game view will display with a new game with no opponent
    */
    @Test
    public void new_game(){
      final PlayerServices services = gameCenter.newPlayerServices();
      when(session.attribute("playerServices")).thenReturn(services);
      final CheckersGame game = services.currentGame();

      // To analyze what the Route created in the View-Model map you need
      // to be able to extract the argument to the TemplateEngine.render method.
      // Mock up the 'render' method by supplying a Mockito 'Answer' object
      // that captures the ModelAndView data passed to the template engine
      final MyModelAndView myModelView = new MyModelAndView();
      when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

      //invoke the test and ignore the output
      CuT.handle(request, response);

      final Object model = myModelView.model;
      assertNotNull(model);
      assertTrue(model instanceof Map);

      //@SupressWarnings("unchecked")
      final Map<String,Object> vm = (Map<String,Object>) model;
      assertEquals(game.getBoard(), vm.get(GetGameRoute.BOARD));
      assertEquals("", vm.get("opponent"));

      assertEquals(GetGameRoute.VIEW_NAME, myModelView.viewName);
    }

    /**
     * Test that the Game view will display with a new game with a opponent
     */
    @Test
    public void new_game_opp(){
      final PlayerServices services = gameCenter.newPlayerServices();
      when(session.attribute("playerServices")).thenReturn(services);
      final CheckersGame game = services.currentGame();

      String enemyName = "bob";
      when(request.queryParams("opponent")).thenReturn(enemyName);
      final Player opponent = new Player(enemyName);
      //playerLobby.SignIn(sessionOpp, opponent);

      // To analyze what the Route created in the View-Model map you need
      // to be able to extract the argument to the TemplateEngine.render method.
      // Mock up the 'render' method by supplying a Mockito 'Answer' object
      // that captures the ModelAndView data passed to the template engine
      final MyModelAndView myModelView = new MyModelAndView();
      when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

      when(playerLobby.getSession(opponent)).thenReturn(sessionOpp);
      when(sessionOpp.attribute("inGame")).thenReturn(false);
      //when(any(PlayerLobby.class).getSession(opponent)).thenReturn(sessionOpp);
      CuT.handle(request, response);

      final Object model = myModelView.model;
      assertNotNull(model);
      assertTrue(model instanceof Map);

      //@SupressWarnings("unchecked")
      final Map<String,Object> vm = (Map<String,Object>) model;
      assertEquals(game.getBoard(), vm.get(GetGameRoute.BOARD));
      assertEquals("bob", vm.get("opponent"));

      assertEquals(GetGameRoute.VIEW_NAME, myModelView.viewName);
    }

    /**
     * Test that the Game view will not display since opponent is in game
     */
    @Test
    public void new_game_opp_in_game(){
      final PlayerServices services = gameCenter.newPlayerServices();
      when(session.attribute("playerServices")).thenReturn(services);
      final CheckersGame game = services.currentGame();

      String enemyName = "bob";
      when(request.queryParams("opponent")).thenReturn(enemyName);
      final Player opponent = new Player(enemyName);
      final Player user = new Player("user");
      //playerLobby.SignIn(sessionOpp, opponent);

      // To analyze what the Route created in the View-Model map you need
      // to be able to extract the argument to the TemplateEngine.render method.
      // Mock up the 'render' method by supplying a Mockito 'Answer' object
      // that captures the ModelAndView data passed to the template engine
      final MyModelAndView myModelView = new MyModelAndView();
      when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

      when(playerLobby.getSession(opponent)).thenReturn(sessionOpp);
      when(sessionOpp.attribute("inGame")).thenReturn(true);
      when(playerLobby.getUser(session)).thenReturn(user);
      //when(any(PlayerLobby.class).getSession(opponent)).thenReturn(sessionOpp);
      CuT.handle(request, response);

      final Object model = myModelView.model;
      assertNotNull(model);
      assertTrue(model instanceof Map);

      //@SupressWarnings("unchecked")
      final Map<String,Object> vm = (Map<String,Object>) model;
      assertEquals("<p>The player bob is already in game; please wait or " +
              "choose another opponent.</p>", vm.get("gameError"));
      assertEquals("user", vm.get("username"));
      assertEquals("<a href=/SignedOut>Sign Out</a>", vm.get("sign"));
      assertEquals("<ul></ul>", vm.get("showPlayers"));
      assertEquals("0",vm.get("numberUsers"));
      assertEquals("Welcome!", vm.get("title"));

      assertEquals("home.ftl", myModelView.viewName);
    }
}
