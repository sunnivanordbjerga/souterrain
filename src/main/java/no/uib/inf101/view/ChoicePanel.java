package no.uib.inf101.view;

import no.uib.inf101.model.game.Choice;

import javax.swing.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * Renders any available {@link Choice}s.
 */
public class ChoicePanel extends JPanel {
    // Uses a callback pattern to decouple view and controller
    // Approach learnt through external discussion
    private Consumer<Choice> onSelect;

    public ChoicePanel(UITheme theme) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
        removeAll();

        if (choices.isEmpty()) {
            return;
        }

        for (Choice choice : choices) {
            JButton button = new JButton(choice.text());
            button.addActionListener(e -> {
                if (onSelect != null) {
                    onSelect.accept(choice);
                }
            });

            this.add(button);
        }
    }
}
