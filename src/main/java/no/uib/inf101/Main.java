package no.uib.inf101;

import no.uib.inf101.controller.GameController;
import no.uib.inf101.model.game.Game;
import no.uib.inf101.view.GameView;

import javax.swing.*;

/**
 * Application entry point.
 */
public class Main {
    private static final String WINDOW_TITLE = "Souterrain";

    /**
     * Initializes the {@link Game}, {@link GameView} and {@link GameController}
     * and starts the game.
     */
    public static void main(String[] args){
        Game game = new Game();
        GameView view = new GameView(game);
        GameController controller = new GameController(game, view);

        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }
}
