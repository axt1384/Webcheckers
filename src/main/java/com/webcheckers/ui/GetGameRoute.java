package com.webcheckers.ui;

import java.util.logging.Logger;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.PlayerServices;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import static com.webcheckers.ui.InterfaceVariable.*;

import spark.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameRoute implements Route {

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private PlayerLobby playerlobby;
    private static final Logger LOG = Logger.getLogger(WebServer.class.getName());
    //private static int counter=0; //Is this Needed?

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
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        String enemyName, summoner;
        if(request.queryParams(SUMMONER)==null){
            enemyName=httpSession.attribute(OPPONENT);
            summoner=httpSession.attribute(SUMMONER);
        }else{
            enemyName = request.queryParams(OPPONENT);
            summoner=request.queryParams(SUMMONER);
        }
        if((enemyName == null && !playerlobby.getUser(httpSession).isSummoner()) || enemyName.equals(summoner)) { // This Session was summoned.
            final PlayerServices playerServices = httpSession.attribute(PLAYER_SERVICES);
            CheckersGame game = playerServices.currentGame();
            vm.put(BOARD, game.getBoard());
            vm.put(OPPONENT,game.getSummoner().toString());
            vm.put(SUMMONER,game.getSummoner().toString());
            vm.put(SUMMONER_VIEW,false);
            vm.put(SUMMONER_TURN, game.isSummonerTurn());
            return templateEngine.render(new ModelAndView(vm, GAME_NAME));
        }
        else {
            Player opponent = new Player(enemyName,false);
            Session opponentSession = this.playerlobby.getSession(opponent);
            boolean oppInGame=opponentSession.attribute(PLAYER_IN_GAME);
            PlayerServices playerServices=httpSession.attribute(PLAYER_SERVICES);
            PlayerServices oppPlayerServices=opponentSession.attribute(PLAYER_SERVICES);
            CheckersGame game, oppGame;
            boolean inGameStatus=false;
            if(playerServices!=null){
                game=playerServices.currentGame();
                oppGame=oppPlayerServices.currentGame();
                LOG.config("status:" + (!game.equals(oppGame)));
                inGameStatus=(!game.equals(oppGame));
            }
            if ((oppInGame && inGameStatus) || (oppInGame && playerServices==null)) { // This Player is Already in a Match
                vm.put(HOME_MESSAGE, "<p>The player " + opponent.toString() + " or you are already in game; please wait or " +
                        "choose another opponent.</p>");
                vm.put(USERNAME, this.playerlobby.getUser(httpSession).toString());
                vm.put(SIGN, "<a href=/SignedOut>Sign Out</a>");
                vm.put(SHOW_PLAYERS, GetHomeRoute.addPlayersList(playerlobby.getUser(httpSession).toString(), playerlobby));
                vm.put(NUMBER_USERS, GetHomeRoute.showNumber(playerlobby));
                vm.put(TITLE, "Welcome!");
                return templateEngine.render(new ModelAndView(vm, HOME_NAME));
            } else {
                if(httpSession.attribute(PLAYER_SERVICES)==null){
                  httpSession.attribute(PLAYER_SERVICES, gameCenter.newPlayerServices());
                  opponentSession.attribute(PLAYER_SERVICES, httpSession.attribute(PLAYER_SERVICES));
                  httpSession.attribute(PLAYER_IN_GAME, true);
                  opponentSession.attribute(PLAYER_IN_GAME, true);

                  httpSession.attribute(SUMMONER,summoner);
                  httpSession.attribute(OPPONENT,opponent.toString());

                  opponentSession.attribute(SUMMONER,summoner);
                  opponentSession.attribute(OPPONENT,summoner);

                  playerServices=httpSession.attribute(PLAYER_SERVICES);
                  updateSummoner(httpSession);
                  game = playerServices.newGame(new Player(this.playerlobby.getUser(httpSession).toString(),true), opponent);
                }else{
                  game = playerServices.currentGame();
                }
                vm.put(BOARD, game.getBoard());
                vm.put(OPPONENT, opponent.toString());
                vm.put(SUMMONER, summoner);
                vm.put(SUMMONER_VIEW,true);
                vm.put(SUMMONER_TURN, game.isSummonerTurn());
                return templateEngine.render(new ModelAndView(vm, GAME_NAME));
            }
        }
    }
}
