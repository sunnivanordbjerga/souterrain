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
    private final Runnable onClose;

    /**
     * Creates a {@link GameController}.
     *
     * @param game the {@link ControllableGame} to update
     * @param view the {@link GameView} to call on for rendering
     */
    public GameController(ControllableGame game, GameView view, Runnable onClose) {
        this.game = game;
        this.view = view;
        this.onClose = onClose;
    }

    /**
     * Triggers game and system events based on the selected {@link Choice}'s type.
     *
     * @param choice the selected choice
     */
    public void handleChoice(Choice choice) {
        switch (choice.type()) {
            case NORMAL -> game.choose(choice);
            case RESTART -> game.reset();
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
        view.render();
    }

    /**
     * Closes the {@link GameView}, ending the application.
     */
    private void quitGame() {
       onClose.run();
    }
}
