package framework.baseelement;

import exceptions.ErrorOfConnectionWithRestAli;
import exceptions.ErrorOfTakingResponseCode;
import exceptions.ErrorOfTransformationResponseToText;
import framework.utils.LoggerOfTests;
import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BasicApi {
    private String url;

    public BasicApi(String url) {
        this.url = url;
    }

    public HttpURLConnection getConnection() {
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/xml");
            return connection;
        } catch (IOException e) {
            LoggerOfTests.logger.error(e.getMessage());
            throw new ErrorOfConnectionWithRestAli();
        }
    }

    public int getResponseCode() {
        LoggerOfTests.logger.info("taking response code");
        try {
            return getConnection().getResponseCode();
        } catch (IOException e) {
            LoggerOfTests.logger.error(e.getMessage());
            throw new ErrorOfTakingResponseCode();
        }
    }

    protected String getTextOfResponse() {
        StringBuilder sb = new StringBuilder();
        BufferedReader br;
        String inputLine;
        try {
            br = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(getConnection().getInputStream())));
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
        } catch (Exception e) {
            LoggerOfTests.logger.error(e.getMessage());
            throw new ErrorOfTransformationResponseToText();
        }
        return sb.toString();
    }
}

