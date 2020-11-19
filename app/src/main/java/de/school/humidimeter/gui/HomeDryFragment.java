package de.school.humidimeter.gui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.school.humidimeter.R;
import de.school.humidimeter.logic.JSONRequestData;
import de.school.humidimeter.logic.JSONRequestUser;
import de.school.humidimeter.logic.MeasuredData;
import de.school.humidimeter.logic.Person;

public class HomeDryFragment extends Fragment {

    private static final String TAG = "HomeDryFragment";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_dry, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        JSONRequestData jsonRequestData = new JSONRequestData();
        MeasuredData mData = jsonRequestData.getDataRequest();

        JSONRequestUser jsonRequestUser = new JSONRequestUser();
        Person person = jsonRequestUser.getUserRequest();

        int time = jsonRequestUser.getVentilationRecommendation();
        Log.d(TAG, "getVentilationRecommendation: " + time);

        CharSequence recommendedVentilationTime;

        if (time == 5) {
            recommendedVentilationTime = "Bitte für 3 - " + time + " Mintuen lüften!";

        }  else if (time == 10) {
            recommendedVentilationTime = "Bitte für 5 - " + time + " Mintuen lüften!";

        }  else if (time == 15) {
            recommendedVentilationTime = "Bitte für " + time + " - 30 Mintuen lüften!";

        } else {
            recommendedVentilationTime = "";
        }

        CharSequence lastVentilationDry;
        if (person.getLastVentilation() == null) {
            lastVentilationDry = "Keine Lüftung eingetragen!";
        } else {
            lastVentilationDry = "Letzte Lüftung: " + person.getLastVentilation();
        }

        CharSequence tempDry = "Temperatur: " + mData.getTemperature() + "°C";
        CharSequence humidityDry = "Luftfeuchtigkeit: " + mData.getHumidity() + "%";

        TextView textTemp = view.findViewById(R.id.tempActualDry);
        textTemp.setText(tempDry);
        TextView textHumidity = view.findViewById(R.id.humidityActualDry);
        textHumidity.setText(humidityDry);
        TextView textLastVent = view.findViewById(R.id.lastVentilationDry);
        textLastVent.setText(lastVentilationDry);
        TextView textRecomVentTime = view.findViewById(R.id.pleaseVentilateDry);
        textRecomVentTime.setText(recommendedVentilationTime);


        view.findViewById(R.id.button_ventilation_first).setOnClickListener(view1 -> {
            jsonRequestUser.changeVentilation();
            Toast.makeText(getContext(), "Lüften wurde eingetragen!", Toast.LENGTH_LONG).show();
        });
    }
}