package de.school.humidimeter.gui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import de.school.humidimeter.R;
import de.school.humidimeter.logic.JSONRequestUser;
import de.school.humidimeter.logic.Person;

public class SettingsEditActivity extends AppCompatActivity {

    private static final String TAG = "SettingsEditActivity";
    EditText firstName;
    EditText country;
    EditText postalCode;
    EditText city;
    Switch coldRes;
    Switch heatRes;
    Switch coronaMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_edit);

        initializeTextFields();

        setTextFields();


        Button btnSave = findViewById(R.id.btnSettingSave);
        btnSave.setOnClickListener(v -> {
            sendDataToRest();
            goToSettingsShowActivity();
            //TODO: Senden der Daten an den Raspi
        });

        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v -> {
            goToSettingsShowActivity();
        });

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
        jsonRequestUser.putUserAccount(jsonObject);
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

    /**
     * Das erhaltene Bundle mit den Daten wird in die TextFelder gesetzt.
     */
    private void setTextFields() {
        JSONRequestUser jsonRequestUser = new JSONRequestUser();
        Person person = jsonRequestUser.getUserRequest();

        firstName.setText(person.getFirstName());
        country.setText(person.getCountryCode());
        postalCode.setText(String.valueOf(person.getPostalCode()));
        city.setText(person.getCity());
        coldRes.setChecked(person.getColdSensitive());
        heatRes.setChecked(person.getHeatSensitive());
        coronaMode.setChecked(person.getCoronaMode());
    }

    private void goToSettingsShowActivity() {
        Intent intent = new Intent(this, SettingsShowActivity.class);
        startActivity(intent);
    }

}
