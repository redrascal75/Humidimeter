package de.school.humidimeter.logic;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONRequestData {

    private static final String TAG = "JSONRequestData";
//    String baseUrl = "http://10.0.2.2:8080";
    String baseUrl = "http://172.20.10.2:8080";

    private static HttpURLConnection connection;
    private BufferedReader reader;
    String line;
    StringBuffer responseContent = new StringBuffer();

    public MeasuredData getDataRequest() {
        MeasuredData measuredData = null;
        try {
            URL url = new URL( baseUrl + "/v1/data");
            connection = (HttpURLConnection) url.openConnection();

            //Request Setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            Log.d(TAG, "responseCode von getDataRequest: " + status);

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            measuredData = parseData(responseContent.toString());

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return measuredData;
    }


    private MeasuredData parseData(String responseBody) throws JSONException {
        MeasuredData measuredData = new MeasuredData();
        JSONArray albums = new JSONArray(responseBody);
        for (int i = 0; i < albums.length(); i++) {
            JSONObject album = albums.getJSONObject(i);
            measuredData.setId(album.getInt("id"));
            measuredData.setHumidity(album.getInt("humidity"));
            measuredData.setTemperature(album.getInt("temperature"));
            measuredData.setTimeStamp(album.getString("timeStamp"));
        }
        return measuredData;
    }
}
