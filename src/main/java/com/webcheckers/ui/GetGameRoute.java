package com.webcheckers.ui;

import com.webcheckers.appl.Player;
import com.webcheckers.model.StartGame;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@code GET /game} route handler.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 * @author <a href='mailto:jrv@se.rit.edu'>Jim Vallino</a>
 */
public class GetGameRoute implements Route {

  // Values used in the view-model map for rendering the game view.
  static final String GAME_BEGINS_ATTR = "isFirstGuess";
  static final String GUESSES_LEFT_ATTR = "guessesLeft";
  static final String TITLE = "Number Guess Game";

  static final String BOARD="board";
  static final String VIEW_NAME = "testGame.ftl";

  private final TemplateEngine templateEngine;

  /**
   * The constructor for the {@code GET /game} route handler.
   *
   * @param templateEngine
   *    The {@link TemplateEngine} used for rendering page HTML.
   */
  GetGameRoute(final TemplateEngine templateEngine) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    //
    this.templateEngine = templateEngine;
  }

  /**
   * {@inheritDoc}
   */

  @Override
  public String handle(Request request, Response response) {
    // retrieve the game object and start one if no game is in progress

    final Session httpSession = request.session();
    final Player player =
            httpSession.attribute("Player");
    StartGame game = player.currentGame();

    // build the View-Model
    final Map<String, Object> vm = new HashMap<>();
    vm.put(BOARD, game.getBoard());
    // render the Game Form view
    return templateEngine.render(new ModelAndView(vm, VIEW_NAME));

  }

}
