package no.uib.inf101.view;

import no.uib.inf101.model.game.Choice;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * Renders any available {@link Choice}s.
 */
public class ChoicePanel extends JPanel {
    // Uses a callback pattern to decouple view and controller
    // Approach learnt through external discussion
    private Consumer<Choice> onSelect;
    private final JPanel buttonPanel;
    private final UITheme theme;

    public ChoicePanel(UITheme theme) {
        this.theme = theme;
        this.buttonPanel = new JPanel();

        this.setBackground(theme.background());
        this.setLayout(new GridBagLayout());
        buttonPanel.setLayout(new GridLayout(0, 1, 0, 20));
        buttonPanel.setBackground(theme.background());
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(buttonPanel);
    }

    public void setOnSelect(Consumer<Choice> onSelect) {
        this.onSelect = onSelect;
    }

    /**
     * Updates the {@link ChoicePanel} by removing the old buttons
     * and adding a new button for each {@link Choice} available from the current node.
     *
     * @param choices the available {@link Choice}s
     */
    public void update(List<Choice> choices) {
        buttonPanel.removeAll();

        if (choices.isEmpty()) {
            return;
        }
        for (Choice choice : choices) {
            JButton button = new JButton(choice.text());
            configureButton(button);
            button.addActionListener(e -> {
                if (onSelect != null) {
                    onSelect.accept(choice);
                }
            });

            buttonPanel.add(button);
        }
    }

    private void configureButton(JButton button) {
        button.setBackground(theme.button());
        button.setForeground(theme.textColor());
        button.setBorder(new EmptyBorder(15, 15, 15, 15));
        button.setFont(theme.small());
        button.setAlignmentX(CENTER_ALIGNMENT);
    }
}
