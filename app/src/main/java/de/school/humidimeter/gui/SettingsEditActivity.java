package de.school.humidimeter.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import de.school.humidimeter.R;

public class SettingsEditActivity extends AppCompatActivity {

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

        Bundle b = getIntent().getExtras();
        setTextFields(b);

        Button btnSave = findViewById(R.id.btnSettingSave);
        btnSave.setOnClickListener(v -> {
            goToSettingsShowActivity(getChangedTextFields());
            //TODO: Senden der Daten an den Raspi
            //TODO: Übergeben der Daten an den Show-Screen
        });

        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v -> {
            goToSettingsShowActivity(b);
            //TODO: Übergeben der Ursprünglichen-Daten an den Show-Screen
        });
    }

    private Bundle getChangedTextFields() {
        Bundle b = new Bundle();
        b.putCharSequence("firstName", firstName.getText().toString());
        b.putCharSequence("country", country.getText().toString());
        b.putCharSequence("postalCode", postalCode.getText().toString());
        b.putCharSequence("city", city.getText().toString());
        b.putBoolean("coldRes", coldRes.isChecked());
        b.putBoolean("heatRes", heatRes.isChecked());
        b.putBoolean("coronaMode", coronaMode.isChecked());
        b.putBoolean("changed", true);
        return b;
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
     * @param b Bundle mit den Daten
     */
    private void setTextFields(Bundle b) {
        firstName.setText(b.getCharSequence("firstName"));
        country.setText(b.getCharSequence("country"));
        postalCode.setText(b.getCharSequence("postalCode"));
        city.setText(b.getCharSequence("city"));
        coldRes.setChecked(b.getBoolean("coldRes"));
        heatRes.setChecked(b.getBoolean("heatRes"));
        coronaMode.setChecked(b.getBoolean("coronaMode"));
    }

    private void goToSettingsShowActivity(Bundle b) {
        Intent intent = new Intent(this, SettingsShowActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

}
