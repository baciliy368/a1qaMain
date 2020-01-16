package framework.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class ImageManager {

    public static boolean compareImage(BufferedImage fileA, BufferedImage fileB, double expectedPercentage) {
        float percentage;
        DataBuffer dbA = fileA.getData().getDataBuffer();
        int sizeA = dbA.getSize();
        DataBuffer dbB = fileB.getData().getDataBuffer();
        int sizeB = dbB.getSize();
        int count = 0;
        if (sizeA == sizeB) {
            for (int i = 0; i < sizeA; i++) {
                if (dbA.getElem(i) == dbB.getElem(i)) {
                    count++;
                }
            }
            percentage = (float)(count * 100) / sizeA;
        } else {
            return false;
        }
        return percentage>=expectedPercentage;
    }

    public static BufferedImage getBufferedImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (Exception e) {
            final ClassCastException classCastException = new ClassCastException(String.format("Can`t cast %s to BufferedImage", file.getName()));
            Log.error(String.format("%s\n%s", classCastException.getMessage(), Arrays.toString(e.getStackTrace())));
            throw classCastException;
        }
    }

    public static BufferedImage getBufferedImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            final ClassCastException classCastException = new ClassCastException(String.format("Can`t cast %s to BufferedImage", url));
            Log.error(String.format("%s\n%s", classCastException.getMessage(), Arrays.toString(e.getStackTrace())));
            throw classCastException;
        }
    }
}