package de.school.humidimeter.gui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import de.school.humidimeter.R;

public class HomeWetFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_wet, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CharSequence charSequence = "Wet Fragment";

        EditText text = view.findViewById(R.id.tempActualWet);
        text.setText(charSequence);

        view.findViewById(R.id.button_ventilation_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: PUT Request an die REST-Schnittstelle, um das Lüften zu bestätigen
                Toast.makeText(getContext(), "Lüften wurde eingetragen!", Toast.LENGTH_LONG).show();
            }
        });
    }
}