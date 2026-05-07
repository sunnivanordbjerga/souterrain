package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private final JLabel titleLabel;
    private final JLabel playerHpLabel;
    private static final String TITLE = "SOUTERRAIN";

    /**
     * Displays the header, including a title and the game HUD (heads-up display),
     * showing player-related UI like HP.
     *
     * @param theme the {@link UITheme} used for colors and text
     */
    public HeaderPanel(UITheme theme) {
        this.setLayout(new BorderLayout());
        this.setBackground(theme.background());
        this.setBorder(new EmptyBorder(15, 10, 10, 15));

        this.titleLabel = new JLabel();
        this.playerHpLabel = new JLabel();
        configureTitleLabel(theme);
        configureHPLabel(theme);
        this.add(titleLabel, BorderLayout.CENTER);
        this.add(playerHpLabel, BorderLayout.LINE_END);
    }

    /**
     * Updates the HUD.
     *
     * @param playerHP    the player's current HP
     * @param playerMaxHP the player's max HP
     */
    public void update(int playerHP, int playerMaxHP) {
        titleLabel.setText(TITLE);
        playerHpLabel.setText("HP: " + playerHP + " / " + playerMaxHP);
    }

    private void configureHPLabel(UITheme theme) {
        playerHpLabel.setFont(theme.small());
        playerHpLabel.setForeground(theme.textColor());
        playerHpLabel.setHorizontalAlignment(JLabel.CENTER);
        playerHpLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
    }

    private void configureTitleLabel(UITheme theme) {
        titleLabel.setFont(theme.title());
        titleLabel.setForeground(theme.textColor());
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
    }
}
