package framework.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageComparator {

    public static boolean compareFileByUrl(File file, String url) {
        long diff = 0;
        long maxDiff = 0;
        try {
            BufferedImage img1 = ImageIO.read(file);
            BufferedImage img2 = ImageIO.read(new URL(url));
            int width = img1.getWidth();
            int height = img1.getHeight();
            int width2 = img2.getWidth();
            int height2 = img2.getHeight();
            if (width != width2 || height != height2) {
                return false;
            }
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
                }
            }
            maxDiff = 3L * 255 * width * height;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Math.floorDiv(diff, maxDiff) == 0.0;
    }

    private static int pixelDiff(int rgb1, int rgb2) {
        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >>  8) & 0xff;
        int b1 =  rgb1        & 0xff;
        int r2 = (rgb2 >> 16) & 0xff;
        int g2 = (rgb2 >>  8) & 0xff;
        int b2 =  rgb2        & 0xff;
        return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
    }
}
