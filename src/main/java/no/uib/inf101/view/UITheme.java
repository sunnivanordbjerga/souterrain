package no.uib.inf101.view;

import java.awt.*;

/**
 * Defines a UI theme for the game, setting colors and fonts.
 *
 * @param background the background color
 * @param button     the button color
 * @param frame      the frame color
 * @param textColor  the text color
 * @param title      the title font
 * @param regular    the regular font
 * @param small      the small font
 */
public record UITheme(Color background,
                      Color button,
                      Color frame,
                      Color textColor,
                      Font title,
                      Font regular,
                      Font small) {

    /**
     * Returns the default {@link UITheme}.
     *
     * @return the default theme
     */
    public static UITheme defaultTheme() {
        return new UITheme(
                new Color(18, 18, 18),
                new Color(45, 45, 50),
                new Color(30, 30, 30),
                new Color(220, 220, 220),
                new Font("Serif", Font.BOLD, 26),
                new Font("Serif", Font.PLAIN, 18),
                new Font("Serif", Font.PLAIN, 14)
        );
    }
}
