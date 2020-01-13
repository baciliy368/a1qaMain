package framework.utils;

import com.google.gson.internal.$Gson$Preconditions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
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
        System.out.println(percentage);
        System.out.println(expectedPercentage);
        return percentage>=expectedPercentage;
    }

    public static <T> BufferedImage getBufferedImage(T image) {
        try {
            System.out.println(image.getClass());
            if(image.getClass() == File.class) {
                return ImageIO.read((File) image);
            } else if (image.getClass() == String.class) {
                return ImageIO.read(new URL((String)image));
            } else {
                throw new Exception("error reading class object " + image.getClass().getName());
            }
        } catch (Exception e) {
            final ClassCastException classCastException = new ClassCastException(String.format("Can`t cast %s to BufferedImage", image.toString()));
            Log.error(String.format("%s\n%s", classCastException.getMessage(), Arrays.toString(e.getStackTrace())));
            throw classCastException;
        }
    }

//    public static BufferedImage getBufferedImage(File file) {
//        try {
//            return ImageIO.read(file);
//        } catch (Exception e) {
//            final ClassCastException classCastException = new ClassCastException(String.format("Can`t cast %s to BufferedImage", file.getName()));
//            Log.error(String.format("%s\n%s", classCastException.getMessage(), Arrays.toString(e.getStackTrace())));
//            throw classCastException;
//        }
//    }
//
//    public static BufferedImage getBufferedImage(String url) {
//        try {
//            return ImageIO.read(new URL(url));
//        } catch (IOException e) {
//            final ClassCastException classCastException = new ClassCastException(String.format("Can`t cast %s to BufferedImage", url));
//            Log.error(String.format("%s\n%s", classCastException.getMessage(), Arrays.toString(e.getStackTrace())));
//            throw classCastException;
//        }
//    }
}