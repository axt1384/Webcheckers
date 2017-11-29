package com.webcheckers.ui;

import java.util.logging.Logger;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.PlayerServices;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameRoute implements Route {

    static final String VIEW_NAME = "game.ftl";
    static final String BOARD="board";
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private PlayerLobby playerlobby;
    private static final Logger LOG = Logger.getLogger(WebServer.class.getName());
    private static int counter=0;
    
    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     */
    GetGameRoute(final TemplateEngine templateEngine, final GameCenter gameCenter, PlayerLobby playerlobby) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.playerlobby = playerlobby;

    }

    private void updateSummoner(Session summonerSess){
        Player summoner= this.playerlobby.getUser(summonerSess);
        summoner.setSummoner(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        String enemyName, summoner;
        if(request.queryParams("summoner")==null){
            enemyName=httpSession.attribute("opponent");
            summoner=httpSession.attribute("summoner");
        }else{
            enemyName = request.queryParams("opponent");
            summoner=request.queryParams("summoner");
        }
        if((enemyName == null && !playerlobby.getUser(httpSession).isSummoner()) || enemyName.equals(summoner)) { // This Session was summoned.
            final PlayerServices playerServices = httpSession.attribute("playerServices");
            CheckersGame game = playerServices.currentGame();
            vm.put(BOARD, game.getBoard());
            vm.put("opponent",game.getSummoner().toString());
            vm.put("summoner",game.getSummoner().toString());
            vm.put("summonerView",false);
            vm.put("summonerTurn", game.isSummonerTurn());
            return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
        }
        else {
            Player opponent = new Player(enemyName,false);
            Session opponentSession = this.playerlobby.getSession(opponent);
            boolean oppInGame=opponentSession.attribute("inGame");
            PlayerServices playerServices=httpSession.attribute("playerServices");
            PlayerServices oppPlayerServices=opponentSession.attribute("playerServices");
            CheckersGame game, oppGame;
            boolean inGameStatus=false;
            if(playerServices!=null){
                game=playerServices.currentGame();
                oppGame=oppPlayerServices.currentGame();
                LOG.config("status:" + (!game.equals(oppGame)));
                inGameStatus=(!game.equals(oppGame));
            }
            if ((oppInGame && inGameStatus) || (oppInGame && playerServices==null)) { // This Player is Already in a Match
                vm.put("gameError", "<p>The player " + opponent.toString() + " or you are already in game; please wait or " +
                        "choose another opponent.</p>");
                vm.put("username", this.playerlobby.getUser(httpSession).toString());
                vm.put("sign", "<a href=/SignedOut>Sign Out</a>");
                vm.put("showPlayers", GetHomeRoute.addPlayersList(playerlobby.getUser(httpSession).toString(), playerlobby));
                vm.put("numberUsers", GetHomeRoute.showNumber(playerlobby));
                vm.put("title", "Welcome!");
                return templateEngine.render(new ModelAndView(vm, "home.ftl"));
            } else {
                if(httpSession.attribute("playerServices")==null){
                  httpSession.attribute("playerServices", gameCenter.newPlayerServices());
                  opponentSession.attribute("playerServices", httpSession.attribute("playerServices"));
                  httpSession.attribute("inGame", true);
                  opponentSession.attribute("inGame", true);

                  httpSession.attribute("summoner",summoner);
                  httpSession.attribute("opponent",opponent.toString());

                  opponentSession.attribute("summoner",summoner);
                  opponentSession.attribute("opponent",summoner);

                  playerServices=httpSession.attribute("playerServices");
                  updateSummoner(httpSession);
                  game = playerServices.newGame(new Player(this.playerlobby.getUser(httpSession).toString(),true), opponent);
                }else{
                  game = playerServices.currentGame();
                }
                vm.put(BOARD, game.getBoard());
                vm.put("opponent", opponent.toString());
                vm.put("summoner", summoner);
                vm.put("summonerView",true);
                vm.put("summonerTurn", game.isSummonerTurn());
                return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
            }
        }
    }
}
