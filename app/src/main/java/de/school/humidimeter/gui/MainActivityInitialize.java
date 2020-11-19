package de.school.humidimeter.gui;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import de.school.humidimeter.R;
import de.school.humidimeter.logic.JSONRequestUser;

public class MainActivityInitialize extends AppCompatActivity {

    private static final String TAG = "MainActivityInitialize";
    EditText firstName;
    EditText country;
    EditText postalCode;
    EditText city;
    Switch coldRes;
    Switch heatRes;
    Switch coronaMode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: Daten aus Datenbank empfangen

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONRequestUser jsonRequestUser = new JSONRequestUser();
        int request = jsonRequestUser.getUserRegisteredRequest();

        Log.d(TAG, "Grösse: " + request);


        if (request > 2) {
            goToHomeActivity();
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialize);

        initializeTextFields();

        Button button = findViewById(R.id.btnInitSave);
        button.setOnClickListener(v -> {
            sendDataToRest();
            goToHomeActivity();
        });
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void initializeTextFields() {
        firstName = findViewById(R.id.textFieldFirstName);
        country = findViewById(R.id.textFieldCountry);
        postalCode = findViewById(R.id.textFieldPostalCode);
        city = findViewById(R.id.textFieldCity);
        coldRes = findViewById(R.id.switchColdRes);
        heatRes = findViewById(R.id.switchHeatRes);
        coronaMode = findViewById(R.id.switchCoronaMode);
    }

    private void sendDataToRest() {
        JSONRequestUser jsonRequestUser = new JSONRequestUser();


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("firstName", firstName.getText().toString());
            jsonObject.put("postalCode", postalCode.getText().toString());
            jsonObject.put("city", city.getText().toString());
            jsonObject.put("coronaMode", coronaMode.isChecked());
            jsonObject.put("coldSensitive", coldRes.isChecked());
            jsonObject.put("heatSensitive", heatRes.isChecked());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "sendDataToRest: " + jsonObject.toString());
        jsonRequestUser.postUserAccount(jsonObject);
    }
}