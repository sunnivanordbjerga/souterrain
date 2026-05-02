package no.uib.inf101.controller;

import no.uib.inf101.view.GameView;

/**
 * Application controller.
 * Translates user input to {@link ControllableGame} actions and calls on the view to render the current state.
 */
public class GameController {
    private final ControllableGame game;
    private final GameView gameView;

    /**
     * Creates a {@link GameController}.
     *
     * @param game the {@link ControllableGame} to update
     * @param gameView the {@link GameView} to call on for rendering
     */
    public GameController(ControllableGame game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    /**
     * Starts the main game loop, translating received user input to {@link ControllableGame} events.
     */
    public void start() {
        if (game.isGameOver()) {
            return;
        }


        //TODO input-management + Game.choose()
    }
}
