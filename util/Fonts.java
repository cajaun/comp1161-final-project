package util;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Fonts {
    public static final Font OPEN_RUNDE = loadFont();

    private static Font loadFont() {
        try {
            Path fontPath = Paths.get("assets", "OpenRunde-Semibold.otf");
            return Font.createFont(Font.TRUETYPE_FONT, new File(fontPath.toString())).deriveFont(20f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, 20);
        }
    }
}

