package no.uib.inf101.controller;

import no.uib.inf101.model.game.Game;
import no.uib.inf101.model.game.Choice;
import no.uib.inf101.view.GameView;

/**
 * Application controller.
 * Translates user input to {@link ControllableGame} actions and calls on the view to render the current state.
 */
public class GameController {
    private ControllableGame game;
    private final GameView view;

    /**
     * Creates a {@link GameController}.
     *
     * @param game the {@link ControllableGame} to update
     * @param view the {@link GameView} to call on for rendering
     */
    public GameController(ControllableGame game, GameView view) {
        this.game = game;
        this.view = view;
    }

    /**
     * Starts the game by calling on the {@link GameView} to render.
     */
    public void start() {
       refreshUI();
    }

    /**
     * Triggers game and system events based on the selected {@link Choice}'s type.
     *
     * @param index the index of the selected choice
     */
    public void handleChoice(int index) {
        Choice choice = game.getCurrentNode().getChoices().get(index);

        switch (choice.type()) {
            case PROGRESS_STORY -> game.choose(index);
            case RESTART -> newGame();
            case QUIT -> {
                quitGame();
                return;
            }
        }
        refreshUI();
    }

    /**
     * Updates the {@link GameView} based on the currently selected node.
     */
    private void refreshUI(){
        view.render(game.getCurrentNode());
    }

    /**
     * Resets the game.
     */
    private void newGame() {
        this.game = new Game();
    }

    /**
     * Closes the {@link GameView}, ending the application.
     */
    private void quitGame() {
        view.close();
    }
}
