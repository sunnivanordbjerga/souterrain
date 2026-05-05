package no.uib.inf101.view;

import java.awt.*;

/**
 * Defines a color theme for the game.
 *
 * @param background the background color
 * @param text the text color
 * @param button the button color
 * @param frame the frame color
 */
public record ColorTheme(Color background, Color text, Color button, Color frame) {

    /**
     * Returns the default {@link ColorTheme}.
     *
     * @return the default theme
     */
    public static ColorTheme defaultTheme() {
        return new ColorTheme(
          Color.BLACK,
          Color.WHITE,
          Color.DARK_GRAY,
          Color.LIGHT_GRAY
        );
    }
}