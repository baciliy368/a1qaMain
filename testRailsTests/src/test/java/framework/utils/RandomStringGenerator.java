package framework.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringGenerator {

    public static String getRandomString(String prefix, int numberOfStrings) {
        return String.format("%s%s", prefix, RandomStringUtils.randomAlphabetic(numberOfStrings));
    }
}
