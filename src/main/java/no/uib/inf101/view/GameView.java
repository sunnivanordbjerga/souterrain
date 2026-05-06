package no.uib.inf101.view;

import no.uib.inf101.model.game.Choice;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Renders the current game state by delegating to
 * the appropriate panels.
 */
public class GameView extends JPanel {
    private final ViewableGame game;
    private final HeaderPanel statPanel;
    private final ChoicePanel choicePanel;
    private final StoryPanel storyPanel;

    /**
     * Creates a new {@link GameView}.
     *
     * @param game the game to render
     */
    public GameView(ViewableGame game) {
        this.game = game;
        UITheme theme = UITheme.defaultTheme();
        this.statPanel = new HeaderPanel(theme);
        this.choicePanel = new ChoicePanel(theme);
        this.storyPanel = new StoryPanel(theme);

        this.setLayout(new BorderLayout());

        this.add(statPanel, BorderLayout.PAGE_START);
        this.add(storyPanel, BorderLayout.CENTER);
        this.add(choicePanel, BorderLayout.PAGE_END);
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

    /**
     * Sets the {@link ChoicePanel} choice listener.
     *
     * @param choiceListener the choice listener,
     *                       returning selected choices to the GameController
     */
    public void setOnSelect(Consumer<Choice> choiceListener) {
        choicePanel.setOnSelect(Objects.requireNonNull(choiceListener, "ChoiceListener cannot be null"));
    }
}
