package de.school.humidimeter.logic;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JSONRequestUser {

    private static final String TAG = "JSONRequestUser";
    String baseUrl = "http://10.0.2.2:8080";

    private static HttpURLConnection connection;
    private BufferedReader reader;
    String line;
    StringBuffer responseContent = new StringBuffer();


    public Person getUserRequest() {
        Person person = null;
        try {
            URL url = new URL(baseUrl + "/v1/show-users");
            connection = (HttpURLConnection) url.openConnection();

            //Request Setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);


            int status = connection.getResponseCode();
            Log.d(TAG, "responseCode von getUserRequest: " + status);

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
            person = parseUser(responseContent.toString());

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return person;
    }

    public int getUserRegisteredRequest() {
        try {
            URL url = new URL(baseUrl + "/v1/show-users");
            connection = (HttpURLConnection) url.openConnection();

            //Request Setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            Log.d(TAG, "responseCode von getUserRegisteredRequest: " + status);

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }


        Log.d(TAG, "Länge responseContent: " + responseContent.length());
        return responseContent.length();
    }

    public void postUserAccount(JSONObject jsonObject) {
        OutputStreamWriter out;

        try {
            URL url = new URL(baseUrl + "/v1/create-user");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setChunkedStreamingMode(0);
            connection.setDoOutput(true);

            out = new OutputStreamWriter(connection.getOutputStream());
            out.write(String.valueOf(jsonObject));
            out.close();

            connection.connect();

            int status = connection.getResponseCode();
            Log.d(TAG, "responseCode von postUserAccount: " + status);

            if (status > 299) {
                Log.d(TAG, "deleteUserAccount: Fehlermeldung");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    public void putUserAccount(JSONObject jsonObject) {
        OutputStreamWriter out;

        try {
            URL url = new URL(baseUrl + "/v1/change-user");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("PUT");
            connection.setChunkedStreamingMode(0);
            connection.setDoOutput(true);

            out = new OutputStreamWriter(connection.getOutputStream());
            out.write(String.valueOf(jsonObject));
            out.close();

            connection.connect();

            int status = connection.getResponseCode();
            Log.d(TAG, "responseCode von putUserAccount: " + status);

            if (status > 299) {
                Log.d(TAG, "deleteUserAccount: Fehlermeldung");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    public void changeVentilation() {
        try {
            URL url = new URL(baseUrl + "/v1/change-ventilation-user");
            connection = (HttpURLConnection) url.openConnection();

            //Request Setup
            connection.setRequestMethod("PATCH");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            Log.d(TAG, "responseCode von changeVentilation: " + status);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    public int getVentilationRecommendation() {
        int ventRecommondation = 0;

        try {
            URL url = new URL(baseUrl + "/v1/ventilation-recommendation");
            connection = (HttpURLConnection) url.openConnection();

            //Request Setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            Log.d(TAG, "responseCode von getVentilationRecommendation: " + status);

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
            Log.d(TAG, "####################getVentilationRecommendation: " + responseContent.toString());

            ventRecommondation = parseVentilation(responseContent.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return ventRecommondation;
    }

    private int parseVentilation(String toString) {
        int lastBracket = toString.lastIndexOf("]");
        String ventRecom = toString.substring(lastBracket + 1);

        Log.d(TAG, "##################parseVentilation: " + ventRecom);
        return Integer.parseInt(ventRecom);
    }

    public void deleteUserAccount() {
        OutputStreamWriter out;
        Person person = getUserRequest();
        String deleteText = "{\"firstName\": \"" + person.getFirstName() + "\"}";


        try {
            URL url = new URL(baseUrl + "/v1/delete-user");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            out = new OutputStreamWriter(connection.getOutputStream());
            out.write(deleteText);
            out.close();

            int status = connection.getResponseCode();
            Log.d(TAG, "responseCode von deleteUserAccount: " + status);

            //getResponseCode / getResponseMessage / getInputStream - triggen die Anfrage

            if (status > 299) {
                Log.d(TAG, "deleteUserAccount: ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    private Person parseUser(String responseBody) throws JSONException {
        Person person = new Person();
        JSONArray albums = new JSONArray(responseBody);
        for (int i = 0; i < albums.length(); i++) {
            JSONObject album = albums.getJSONObject(i);
            person.setId(album.getInt("id"));
            person.setFirstName(album.getString("firstName"));
            person.setPostalCode(album.getInt("postalCode"));
            person.setCountryCode(album.getInt("countryCode"));
            person.setCity(album.getString("city"));
            person.setCoronaMode(album.getBoolean("coronaMode"));
            person.setColdSensitive(album.getBoolean("coldSensitive"));
            person.setHeatSensitive(album.getBoolean("heatSensitive"));
            person.setVentilationSkip(album.getBoolean("ventilationSkip"));
//            Log.d(TAG, "###########parseUser - album.getString: " + album.getString("lastVentilation"));
            person.setLastVentilation(parseDate(album.getString("lastVentilation")));
        }
        return person;
    }

    private String parseDate(String strLastVentilation) {
        if (strLastVentilation == null || strLastVentilation.equals("null")) {
            return null;
        }
        int year = Integer.parseInt(strLastVentilation.substring(0,4));
        int month = Integer.parseInt(strLastVentilation.substring(5,7));
        int day = Integer.parseInt(strLastVentilation.substring(8,10));
        int hour = Integer.parseInt(strLastVentilation.substring(11,13));
        int min = Integer.parseInt(strLastVentilation.substring(14,16));

        String formatTime = "hh:mm";
        String formatDate = "dd.MM.yyyy";
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern(formatTime);
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern(formatDate);

        String time = LocalTime.of(hour,min).format(formatterTime);
        String date = LocalDate.of(year, month,day).format(formatterDate);

//        Log.d(TAG, "year: " + year);
//        Log.d(TAG, "month: " + month);
//        Log.d(TAG, "day: " + day);
//        Log.d(TAG, "hour: " + hour);
//        Log.d(TAG, "min: " + min);
        return time + " Uhr, " + date;
    }
}
