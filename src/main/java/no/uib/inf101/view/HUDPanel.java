package no.uib.inf101.view;

import javax.swing.*;
import java.awt.*;

public class HUDPanel extends JPanel {
    private final JLabel playerHpLabel;

    /**
     * Displays the game HUD (heads-up display), showing player-related UI like HP.
     *
     * @param theme the {@link UITheme} used for colors and text
     */
    public HUDPanel(UITheme theme){
        this.setLayout( new FlowLayout(FlowLayout.RIGHT));
        this.setBackground(theme.background());

        this.playerHpLabel = new JLabel();
        configureLabel(theme);

        this.add(playerHpLabel);
    }

    /**
     * Updates the HUD.
     *
     * @param playerHP the player's current HP
     * @param playerMaxHP the player's max HP
     */
    public void update(int playerHP, int playerMaxHP){
        playerHpLabel.setText("HP: " + playerHP + " / " + playerMaxHP);
    }

    private void configureLabel(UITheme theme){
        playerHpLabel.setFont(theme.small());
        playerHpLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        playerHpLabel.setForeground(theme.textColor());
    }
}
