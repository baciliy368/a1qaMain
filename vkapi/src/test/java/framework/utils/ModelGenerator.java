package framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.response.Response;
import java.io.File;
import java.util.Arrays;

public class ModelGenerator {
    public static <T> T getModelByMapping(File file, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(file, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new NullPointerException();
        }
    }

    public static <T> T getModelByMapping(Response response, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(response.asByteArray(), clazz);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new NullPointerException();
        }
    }

    public static <T> T getModelByMapping(String json, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new NullPointerException();
        }
    }

    public static <T> T getModelByMappingXml(File file, Class<T> clazz) {
        try {
            return new XmlMapper().readValue(file, clazz);
        } catch (Exception e) {
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new NullPointerException();
        }
    }
}
