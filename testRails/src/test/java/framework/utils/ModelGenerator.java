package framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import java.io.File;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ModelGenerator {
    private static final NoSuchElementException exception = new NoSuchElementException("Problems with creating model");

    public static <T> T getModelByMapping(File file, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(file, clazz);
        } catch (Exception e) {
            Log.error(exception.getMessage(), Arrays.toString(e.getStackTrace()));
            throw exception;
        }
    }
}
