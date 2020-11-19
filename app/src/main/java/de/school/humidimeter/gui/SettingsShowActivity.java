package de.school.humidimeter.gui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import de.school.humidimeter.R;
import de.school.humidimeter.logic.JSONRequestUser;
import de.school.humidimeter.logic.Person;

public class SettingsShowActivity extends AppCompatActivity {

    private static final String TAG = "SettingsShowActivity";

    TextView firstName;
    TextView country;
    TextView postalCode;
    TextView city;
    Switch coldRes;
    Switch heatRes;
    Switch coronaMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_show);

        initializeTextFields();
        setTextFields();

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> goToHomeActivity());

        Button btnSave = findViewById(R.id.btnSettingEdit);
        btnSave.setOnClickListener(v -> goToSettingsEditActivity());

        Button btnDelete = findViewById(R.id.btnDeleteAcc);
        btnDelete.setOnClickListener(v -> {
            Toast.makeText(SettingsShowActivity.this, "Account gelöscht!", Toast.LENGTH_LONG).show();

            JSONRequestUser jsonRequestUser = new JSONRequestUser();
            jsonRequestUser.deleteUserAccount();
            goToMainActivityInitialize();
        });
    }

    private void goToMainActivityInitialize() {
        Intent intent = new Intent(this, MainActivityInitialize.class);
        startActivity(intent);
    }

    private void goToSettingsEditActivity() {
        Intent intent = new Intent(this, SettingsEditActivity.class);
        startActivity(intent);
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    /**
     * Die TextFelder werden initialisiert und als globale Variable gespeichert.
     */
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
     * Ein Request auf den Rasperry wird angefordert und die Daten in die TextFelder gesetzt.
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
}
