package de.school.humidimeter.gui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import de.school.humidimeter.R;

public class SettingsShowActivity  extends AppCompatActivity {

    private static final String TAG = "ZeigeDaten";
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
        setContentView(R.layout.activity_settings_show);

        Bundle b = getIntent().getExtras();
        if (b == null) {
            initializeTextFields();
            setTextFields();

        } else {

            if (b.getBoolean("changed")) {
                setChangedTextFields(b);

            } else {
                initializeTextFields();
                setTextFields();
            }
        }


        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> goToHomeActivity());

        Button btnSave = findViewById(R.id.btnSettingEdit);
        btnSave.setOnClickListener(v -> goToSettingsEditActivity(getTextFields()));

        Button btnDelete = findViewById(R.id.btnDeleteAcc);
        btnDelete.setOnClickListener(v -> Toast.makeText(SettingsShowActivity.this, "Account gelöscht!", Toast.LENGTH_LONG).show());
    }


    private void goToSettingsEditActivity(Bundle b) {
        Intent intent = new Intent(this, SettingsEditActivity.class);
        intent.putExtras(b);
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
        //TODO: Empfangen der Daten
        firstName.setText("Hans");
        country.setText("Deutschland");
        postalCode.setText("22767");
        city.setText("Hamburg");
        coldRes.setChecked(true);
        heatRes.setChecked(true);
        coronaMode.setChecked(true);
    }

    /**
     * Die TextFelder werden gemäss dem Bundle neu gesetzt.
     * @param b Das geänderte Bundle aus dem Editier-Fenster
     */
    private void setChangedTextFields(Bundle b) {
        //TODO: Ersetzen durch "setTextFields", wenn die JSON-Anbindung erfogreich ist.
        Log.i(TAG, "bundleSize: " + b.size());
        Log.i(TAG, "bundle: " + b.getCharSequence("firstName"));
        firstName.setText(b.getCharSequence("firstName"));
        country.setText(b.getCharSequence("country"));
        postalCode.setText(b.getCharSequence("postalCode"));
        city.setText(b.getCharSequence("city"));
        coldRes.setChecked(b.getBoolean("coldRes"));
        heatRes.setChecked(b.getBoolean("heatRes"));
        coronaMode.setChecked(b.getBoolean("coronaMode"));
    }

    /**
     * Die eingegebenen Daten werden in ein Bundle gepackt und übergeben an das Editier-Fenster
     * @return Bundle mit den Daten der TextFelder
     */
    private Bundle getTextFields() {
        Bundle b = new Bundle();
        b.putCharSequence("firstName", firstName.getText().toString());
        b.putCharSequence("country", country.getText().toString());
        b.putCharSequence("postalCode", postalCode.getText().toString());
        b.putCharSequence("city", city.getText().toString());
        b.putBoolean("coldRes", coldRes.isChecked());
        b.putBoolean("heatRes", heatRes.isChecked());
        b.putBoolean("coronaMode", coronaMode.isChecked());
        return b;
    }

}
