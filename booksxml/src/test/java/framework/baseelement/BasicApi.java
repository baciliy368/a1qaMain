package framework.baseelement;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import exceptions.ErrorOfTakingResponseCode;
import exceptions.ErrorOfTransformationResponseToText;
import framework.utils.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public abstract class BasicApi {
    public abstract HttpURLConnection getConnection();

    public int getResponseCode() {
        Log.info("taking response code");
        try {
            return getConnection().getResponseCode();
        } catch (IOException e) {
            Log.error(e.getMessage());
            throw new ErrorOfTakingResponseCode();
        }
    }

    public <T> T getModelsFromRoot(Class<T> clazz) {
        try (final InputStream inputStream = getConnection().getInputStream()) {
            return new XmlMapper().readValue(inputStream, clazz);
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw new ErrorOfTransformationResponseToText();
        }
    }
}

