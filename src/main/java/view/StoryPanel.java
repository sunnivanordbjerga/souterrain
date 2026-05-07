package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;


/**
 * Displays the game log.
 */
public class StoryPanel extends JPanel {
    private final JTextArea textArea;

    /**
     * Creates a {@link StoryPanel} holding a scrollable text area.
     *
     * @param theme the {@link UITheme} used to set colors and fonts
     */
    public StoryPanel(UITheme theme) {
        this.textArea = new JTextArea();
        this.setBackground(theme.background());

        configureTextArea(theme);

        this.setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    /**
     * Updates the log text.
     *
     * @param log the game log
     */
    public void update(List<String> log) {
        textArea.setText(String.join("\n\n", log));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    private void configureTextArea(UITheme theme) {
        textArea.setEnabled(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setRows(15);
        textArea.setColumns(60);
        textArea.setMargin(new Insets(20, 20, 20, 20));
        textArea.setBorder(new EmptyBorder(20, 30, 20, 30));

        textArea.setBackground(theme.background());
        textArea.setForeground(theme.textColor());
        textArea.setFont(theme.regular());
    }
}
