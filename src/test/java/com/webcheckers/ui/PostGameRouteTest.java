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
import com.webcheckers.appl.TurnAdministrator;
import com.webcheckers.model.Movement.PossibleMoves;
import com.webcheckers.ui.PostGameRoute;

/**
 * The unit test suite for the {@link PostGameRoute} component.
 *
 */
 public class PostGameRouteTest{
   /**
    * The component under test CuT.
    */
    private PostGameRoute CuT;

    private Request request;
    private Session session;
    private Session sessionOpp;
    private Response response;
    private TemplateEngine engine;
    private GameCenter gameCenter;
    private PlayerLobby playerLobby;
    private PlayerServices services;
    private Player player;
    private CheckersGame game;
    private TurnAdministrator admin;
    private PossibleMoves moves;

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
       services = mock(PlayerServices.class);
       player = mock(Player.class);
       game = mock(CheckersGame.class);
       admin = mock(TurnAdministrator.class);
       moves = mock(PossibleMoves.class);

       //create a CuT for the tests
       CuT = new PostGameRoute(engine, gameCenter, playerLobby);
     }

     @Test
     public void postThings(){
       when(request.queryParams("move")).thenReturn("0-7");
       when(request.queryParams("oldPos")).thenReturn("4-3");
       when(request.queryParams("capture")).thenReturn("3-4");
       when(request.queryParams("capture2")).thenReturn("1-6");

       //final PlayerServices services = gameCenter.newPlayerServices();
       when(session.attribute("playerServices")).thenReturn(services);
       String myName = "user";
       String enemyName = "bob";
       final Player user = new Player(myName);
       final Player opponent = new Player(enemyName,false);
       //final CheckersGame game = services.newGame(user,opponent);
       when(services.currentGame()).thenReturn(game);
       when(game.getSummoner()).thenReturn(user);
       when(game.getOpp()).thenReturn(opponent);
       when(admin.isOver()).thenReturn(user);

       this.CuT.handle(request,response);
     }
 }
