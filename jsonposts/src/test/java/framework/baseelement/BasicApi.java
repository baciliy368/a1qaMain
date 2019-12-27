package framework.baseelement;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ErrorOfTakingResponseCode;
import exceptions.ErrorOfTransformationResponseToText;
import framework.utils.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;

public abstract class BasicApi {

    public abstract HttpURLConnection getConnection();

    public int getResponseCode() {
        Log.info("taking response code");
        try {
            return getConnection().getResponseCode();
        } catch (IOException e) {
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new ErrorOfTakingResponseCode(e);
        }
    }

    public <T> T getModelByMapping(Class<T> clazz) {
        try (final InputStream inputStream = getConnection().getInputStream()) {
            return new ObjectMapper().readValue(inputStream, clazz);
        } catch (Exception e) {
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new ErrorOfTransformationResponseToText(e);
        }
    }

    public boolean isResponseEmpty() {
        try {
            getConnection().getInputStream();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}

