package no.uib.inf101;

import no.uib.inf101.controller.GameController;
import no.uib.inf101.model.game.Game;
import no.uib.inf101.view.GameView;

/**
 * Application entry point.
 */
public class Main {

    /**
     * Initializes the {@link Game}, {@link GameView} and {@link GameController}
     * and starts the game.
     */
    public static void main(String[] args){
        Game game = new Game();
        GameView gameView = new GameView(game);
        GameController gameController = new GameController(game, gameView);

        gameController.start();
    }
}
