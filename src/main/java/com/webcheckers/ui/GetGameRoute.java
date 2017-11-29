package com.webcheckers.ui;

import java.util.logging.Logger;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.PlayerServices;
import com.webcheckers.appl.TurnAdministrator;
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

    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param templateEngine The {@link TemplateEngine} used for rendering page HTML.
     */
    GetGameRoute(final TemplateEngine templateEngine, final GameCenter gameCenter, PlayerLobby playerlobby) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.playerlobby = playerlobby;

    }

    private void updateSummoner(Session summonerSess) {
        Player summoner = this.playerlobby.getUser(summonerSess);
        summoner.setSummoner(true);
    }

    private String summonedHandle(Request request, Response response, Map<String, Object> vm) {
        Session httpSession = request.session();
        final PlayerServices playerServices = httpSession.attribute(PLAYER_SERVICES);
        CheckersGame game = playerServices.currentGame();
        TurnAdministrator turnAdmin = new TurnAdministrator(game.getSummoner(), game.getOpp(), game);
        Player victor = turnAdmin.isOver();
        if (turnAdmin.isOver() != null) {
            httpSession.attribute(SCORE_MESSAGE,victor.toString() + " won the game!");
            response.redirect("/score");
            httpSession.attribute(PLAYER_IN_GAME, false);
            return null;
        }
        if(!(Boolean) request.session().attribute(PLAYER_IN_GAME)) { // The Opponent has Forfeited and Ended the Game
            String opponent = request.session().attribute(OPPONENT);
            httpSession.attribute(SCORE_MESSAGE, opponent + " forfeited the match!");
            response.redirect("/score");
            httpSession.attribute(PLAYER_IN_GAME, false);
            return null;
        }
        vm.put(BOARD, game.getBoard());
        vm.put(OPPONENT, game.getSummoner().toString());
        vm.put(SUMMONER, game.getSummoner().toString());
        vm.put(SUMMONER_VIEW, false);
        vm.put(SUMMONER_TURN, game.isSummonerTurn());
        vm.put(HAS_CAPTURE_MOVE, turnAdmin.hasCapture(game.getOpp()));
        return templateEngine.render(new ModelAndView(vm, GAME_NAME));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        String enemyName, summoner;

        if (request.queryParams(SUMMONER) == null) {
            enemyName = GetHomeRoute.returnSpaces(httpSession.attribute(OPPONENT));
            summoner =  GetHomeRoute.returnSpaces(httpSession.attribute(SUMMONER));
        } else {
            enemyName =  GetHomeRoute.returnSpaces(request.queryParams(OPPONENT));
            summoner =  GetHomeRoute.returnSpaces(request.queryParams(SUMMONER));
        }
        if ((enemyName == null && !playerlobby.getUser(httpSession).isSummoner())||  enemyName.equals(summoner)) { // This Session was summoned.
            return summonedHandle(request, response, vm);
        } else {
            Player opponent = new Player(enemyName, false);
            Session opponentSession = this.playerlobby.getSession(opponent);

            if(playerlobby.getUser(opponentSession).isSummoner()) { // They Clicked Right After being Summoned
                return summonedHandle(request, response, vm);
            }

            boolean oppInGame = opponentSession.attribute(PLAYER_IN_GAME);
            PlayerServices playerServices = httpSession.attribute(PLAYER_SERVICES);
            PlayerServices oppPlayerServices = opponentSession.attribute(PLAYER_SERVICES);
            CheckersGame game, oppGame;
            boolean inGameStatus = false;

            if (playerServices != null && oppPlayerServices != null) {

                if(!(Boolean) request.session().attribute(PLAYER_IN_GAME)) { // The Opponent has Forfeited and Ended the Game
                    String op = request.session().attribute(OPPONENT);
                    httpSession.attribute(SCORE_MESSAGE, op + " forfeited the match!");
                    response.redirect("/score");
                    httpSession.attribute(PLAYER_IN_GAME, false);
                    return null;
                }

                game = playerServices.currentGame();
                oppGame = oppPlayerServices.currentGame();
                LOG.config("status:" + (!game.equals(oppGame)));
                inGameStatus = (!game.equals(oppGame));
            }
            if ((oppInGame && inGameStatus) || (oppInGame && playerServices == null)) { // This Player is Already in a Match
                vm.put(HOME_MESSAGE, "<p>The player " + opponent.toString() + " or you are already in game; please wait or " +
                        "choose another opponent.</p>");
                vm.put(USERNAME, this.playerlobby.getUser(httpSession).toString());
                vm.put(SIGN, "<a href=/SignedOut>Sign Out</a>");
                vm.put(SHOW_PLAYERS, GetHomeRoute.addPlayersList(playerlobby.getUser(httpSession).toString(), playerlobby));
                vm.put(NUMBER_USERS, GetHomeRoute.showNumber(playerlobby));
                vm.put(TITLE, "Welcome!");
                return templateEngine.render(new ModelAndView(vm, HOME_NAME));
            } else { // A new Game is Being Started
                if (httpSession.attribute(PLAYER_SERVICES) == null) { // Need to Create New Service for Players

                    // Create and Store the Service for Both Players
                    httpSession.attribute(PLAYER_SERVICES, gameCenter.newPlayerServices());
                    opponentSession.attribute(PLAYER_SERVICES, httpSession.attribute(PLAYER_SERVICES));

                    // Both Players are Now to be in Game
                    httpSession.attribute(PLAYER_IN_GAME, true);
                    opponentSession.attribute(PLAYER_IN_GAME, true);

                    // Store the Players for Later
                    httpSession.attribute(SUMMONER, summoner);
                    httpSession.attribute(OPPONENT, opponent.toString());
                    opponentSession.attribute(SUMMONER, summoner);
                    opponentSession.attribute(OPPONENT, summoner);

                    playerServices = httpSession.attribute(PLAYER_SERVICES);
                    updateSummoner(httpSession);
                    game = playerServices.newGame(new Player(this.playerlobby.getUser(httpSession).toString(), true), opponent);
                } else {
                    game = playerServices.currentGame();
                }
                TurnAdministrator turnAdmin = new TurnAdministrator(game.getSummoner(), game.getOpp(), game);
                Player victor = turnAdmin.isOver();
                turnAdmin = new TurnAdministrator(game.getSummoner(), game.getOpp(), game);
                if (turnAdmin.isOver() != null) {

                    httpSession.attribute(SCORE_MESSAGE,victor.toString() + " won the game!");
                    response.redirect("/score");
                    httpSession.attribute(PLAYER_IN_GAME, false);
                    return null;
                }
                turnAdmin = new TurnAdministrator(game.getSummoner(), game.getOpp(), game);
                if(turnAdmin.isOver() != null){
                  vm.put(HOME_MESSAGE, victor.toString() + " won the game!");
                  vm.put(TITLE, "Welcome!");
                  return templateEngine.render(new ModelAndView(vm, HOME_NAME));
                }

                vm.put(BOARD, game.getBoard());
                vm.put(OPPONENT, opponent.toString());
                vm.put(SUMMONER, summoner);
                vm.put(SUMMONER_VIEW, true);
                vm.put(SUMMONER_TURN, game.isSummonerTurn());
                vm.put(HAS_CAPTURE_MOVE, turnAdmin.hasCapture(game.getSummoner()));
                return templateEngine.render(new ModelAndView(vm, GAME_NAME));
            }
        }
    }
}
