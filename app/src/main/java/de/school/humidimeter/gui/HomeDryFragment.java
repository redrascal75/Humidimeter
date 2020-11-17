package de.school.humidimeter.gui;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionValues;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import de.school.humidimeter.R;

public class HomeDryFragment extends Fragment {

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

        CharSequence charSequence = "Dry Fragment";

        EditText text = view.findViewById(R.id.tempActualDry);
        text.setText(charSequence);


        view.findViewById(R.id.button_ventilation_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: PUT Request an die REST-Schnittstelle, um das Lüften zu bestätigen
                Toast.makeText(getContext(), "Lüften wurde eingetragen!", Toast.LENGTH_LONG).show();
            }
        });
    }
}