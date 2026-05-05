package no.uib.inf101.view;

import no.uib.inf101.model.game.Choice;
import no.uib.inf101.model.game.node.Node;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * Displays the {@link Node} text, {@link Choice}s and health points.
 */
public class StoryPanel extends JPanel {
    private final JTextArea textArea;

    public StoryPanel(UITheme theme) {
        this.textArea = new JTextArea();
        this.setBackground(theme.background());

        configureTextArea(theme);

        this.setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void update(List<String> log){
        textArea.setText(String.join("\n", log));
    }

    private void configureTextArea(UITheme theme) {
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setRows(40);
        textArea.setColumns(20);
        textArea.setCaretPosition(textArea.getDocument().getLength());

        textArea.setBackground(theme.background());
        textArea.setForeground(theme.textColor());
        textArea.setFont(theme.text());
        textArea.setFocusable(false);
    }
}
