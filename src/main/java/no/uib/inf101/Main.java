package no.uib.inf101;

import com.formdev.flatlaf.FlatDarkLaf;
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
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame(WINDOW_TITLE);

        Game game = new Game();
        GameView view = new GameView(game);
        GameController controller = new GameController(game, view, frame::dispose);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }
}
