package no.uib.inf101.view;

import javax.swing.*;
import java.awt.*;

/**
 * Renders the current game state by delegating to
 * the {@link StoryPanel}, {@link ChoicePanel}.
 */
public class GameView extends JPanel {
    private final ViewableGame game;
    private final UITheme theme;
    private final HUDPanel statPanel;
    private final ChoicePanel choicePanel;
    private final StoryPanel storyPanel;


    /**
     * Creates a new {@link GameView}.
     *
     * @param game the game to render
     */
    public GameView(ViewableGame game) {
        this.game = game;
        this.theme = UITheme.defaultTheme();
        this.statPanel = new HUDPanel(theme);
        this.choicePanel = new ChoicePanel(theme);
        this.storyPanel = new StoryPanel(theme);

        this.setLayout(new BorderLayout());

        this.add(statPanel, BorderLayout.PAGE_START);
        this.add(storyPanel, BorderLayout.CENTER);
        this.add(choicePanel, BorderLayout.PAGE_END);
    }

    @Override
    public void paintComponent(Graphics g) { //
        super.paintComponent(g);
        Graphics2D g2 =(Graphics2D) g;
        //TODO: draw background frame and title, else delete
    }

    /**
     * Renders the {@link ViewableGame}'s current state.
     */
    public void render() {
        storyPanel.update(game.getLog());
        choicePanel.update(game.getCurrentNode().getChoices());
        statPanel.update(game.getPlayerHp(), game.getPlayerMaxHp());

        revalidate();
        repaint();
    }
}
