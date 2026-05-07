package controller;

import model.game.Choice;
import view.GameView;

/**
 * Application controller.
 * Translates user input to {@link ControllableGame} actions and calls on the view to render the current state.
 */
public class GameController {
    private final ControllableGame game;
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

        this.view.setOnSelect(this::handleChoice);
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
            case USE, EQUIP -> choice.execute();
        }
        refreshUI();
    }

    /**
     * Updates the {@link GameView} based on the currently selected node.
     */
    private void refreshUI() {
        view.render();
    }

    /**
     * Closes the {@link GameView}, ending the application.
     */
    private void quitGame() {
        onClose.run();
    }
}
