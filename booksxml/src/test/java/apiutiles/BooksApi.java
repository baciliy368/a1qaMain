package apiutiles;

import exceptions.ErrorOfConnectionWithRestApi;
import framework.baseelement.BasicApi;
import framework.utils.Log;
import framework.utils.PropertiesReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class BooksApi extends BasicApi {
    private static final String URL_TO_GET_BOOKS = PropertiesReader.getValue("URL_TO_GET_BOOKS");

    @Override
    public HttpURLConnection getConnection() {
        try {
            URL obj = new URL(URL_TO_GET_BOOKS);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/xml");
            return connection;
        } catch (IOException e) {
            Log.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
            throw new ErrorOfConnectionWithRestApi();
        }
    }
}

