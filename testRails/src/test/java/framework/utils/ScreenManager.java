package framework.utils;

import javax.imageio.ImageIO;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenManager {
    private static final Path DEFAULT_PATH_OF_SCREENSHOT = Paths.get(PropertiesReader.getValue("PATH_TO_SAVE_SCREENSHOT"));

    public static void takeScreenShot() {
        try {
            try {
                Files.createDirectory(DEFAULT_PATH_OF_SCREENSHOT.getParent());
            } catch (FileAlreadyExistsException ignored) {
            } finally {
                Robot r = new Robot();
                Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage image = r.createScreenCapture(capture);
                ImageIO.write(image, "png", DEFAULT_PATH_OF_SCREENSHOT.toFile());
            }
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }
}
