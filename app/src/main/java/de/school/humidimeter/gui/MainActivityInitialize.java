package de.school.humidimeter.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import de.school.humidimeter.R;

public class MainActivityInitialize extends AppCompatActivity {

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
        if (1 == 1) {
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
        Bundle b = new Bundle();
        b.putCharSequence("firstName", firstName.getText().toString());
        b.putCharSequence("country", country.getText().toString());
        b.putCharSequence("postalCode", postalCode.getText().toString());
        b.putCharSequence("city", city.getText().toString());
        b.putBoolean("coldRes", coldRes.getShowText());
        b.putBoolean("heatRes", heatRes.getShowText());
        b.putBoolean("coronaMode", coronaMode.getShowText());
    }
}