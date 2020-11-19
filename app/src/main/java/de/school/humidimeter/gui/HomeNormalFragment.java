package de.school.humidimeter.gui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.school.humidimeter.R;
import de.school.humidimeter.logic.JSONRequestData;
import de.school.humidimeter.logic.JSONRequestUser;
import de.school.humidimeter.logic.MeasuredData;
import de.school.humidimeter.logic.Person;

public class HomeNormalFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_normal, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        JSONRequestData jsonRequestData = new JSONRequestData();
        MeasuredData mData = jsonRequestData.getDataRequest();

        JSONRequestUser jsonRequestUser = new JSONRequestUser();
        Person person = jsonRequestUser.getUserRequest();







        CharSequence tempDry = "Temperatur: " + mData.getTemperature() + "°C";
        CharSequence humidityDry = "Luftfeuchtigkeit: " + mData.getHumidity() + "%";
        CharSequence lastVentilationDry;
        if (person.getLastVentilation() == null) {
             lastVentilationDry = "Keine Lüftung eingetragen!";
        } else {
            lastVentilationDry = "Letzte Lüftung: " + person.getLastVentilation();
        }

        TextView textTemp = view.findViewById(R.id.tempActualNormal);
        textTemp.setText(tempDry);
        TextView textHumidity = view.findViewById(R.id.humidityActualNormal);
        textHumidity.setText(humidityDry);
        TextView textLastVent = view.findViewById(R.id.lastVentilationNormal);
        textLastVent.setText(lastVentilationDry);
    }
}