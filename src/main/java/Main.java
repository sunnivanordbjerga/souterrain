import com.formdev.flatlaf.FlatDarkLaf;
import controller.GameController;
import model.game.Game;
import view.GameView;

import javax.swing.*;
import java.awt.*;

private static final String WINDOW_TITLE = "Souterrain";
private static final Dimension WINDOW_SIZE = new Dimension(450, 680);

/**
 * Application entry point
 * <p>
 * Initializes the {@link Game}, {@link GameView} and {@link GameController}
 * and displays the main window.
 */
public static void main(String[] args) {
    FlatDarkLaf.setup();
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame(WINDOW_TITLE);

    Game game = new Game();
    GameView view = new GameView(game);
    new GameController(game, view, frame::dispose);

    frame.setPreferredSize(WINDOW_SIZE);
    frame.setContentPane(view);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    view.render();
}
