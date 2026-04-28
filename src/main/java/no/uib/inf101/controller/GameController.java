package no.uib.inf101.controller;

import no.uib.inf101.view.GameView;


public class GameController {
    private final ControllableGame game;
    private final GameView gameView;

    public GameController(ControllableGame game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    /**
     * Starts the main game loop.
     */
    public void start() {
        if(game.gameOver()){
            return;
        }


        //TODO input-management
    }
}
