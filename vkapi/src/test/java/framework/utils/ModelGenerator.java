package framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.response.Response;
import java.io.File;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ModelGenerator {
    private static final NoSuchElementException exception = new NoSuchElementException("Problems with creating model");

    public static <T> T getModelByMapping(File file, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(file, clazz);
        } catch (Exception e) {
            Log.error(String.format("%s\n%s",exception.getMessage(), Arrays.toString(e.getStackTrace())));
            throw exception;
        }
    }

    public static <T> T getModelByMapping(Response response, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(response.asByteArray(), clazz);
        } catch (Exception e) {
            Log.error(String.format("%s\n%s",exception.getMessage(), Arrays.toString(e.getStackTrace())));
            throw exception;
        }
    }

    public static <T> T getModelByMapping(String string, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(string, clazz);
        } catch (Exception e) {
            Log.error(String.format("%s\n%s",exception.getMessage(), Arrays.toString(e.getStackTrace())));
            throw exception;
        }
    }

    public static <T> T getModelByMappingXml(File file, Class<T> clazz) {
        try {
            return new XmlMapper().readValue(file, clazz);
        } catch (Exception e) {
            Log.error(String.format("%s\n%s",exception.getMessage(), Arrays.toString(e.getStackTrace())));
            throw exception;
        }
    }
}
