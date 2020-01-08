package framework.baseelement;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import framework.utils.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.channels.NoConnectionPendingException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public abstract class BasicApi {
    public abstract HttpURLConnection getConnection();

    public int getResponseCode() {
        Log.info("taking response code");
        try {
            return getConnection().getResponseCode();
        } catch (IOException e) {
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new NoSuchElementException();
        }
    }

    public <T> T getModelsFromRoot(Class<T> clazz) {
        try (final InputStream inputStream = getConnection().getInputStream()) {
            return new XmlMapper().readValue(inputStream, clazz);
        } catch (Exception e) {
            Log.error(Arrays.toString(e.getStackTrace()));
            throw new NoSuchElementException();
        }
    }
}

