package de.school.humidimeter.gui;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import java.util.List;

import de.school.humidimeter.R;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        startJobScheduler();


        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.)


        //TODO: Request auf REST-Schnittstelle, um die Luftfeuchtigkeit zu erhalten
        int humidity = 70;
        if (humidity < 60) {

        } else if (humidity >= 60 && humidity <= 80) {

        } else if (humidity > 80) {

        }




//        Bundle b = getIntent().getExtras();
//        if (b.getString(""))



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            goToSettingsShowActivity();
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
        });
    }

    private void startJobScheduler() {
//        JobScheduler

    }

    private void goToSettingsShowActivity() {
        Intent intent = new Intent(this, SettingsShowActivity.class);
        startActivity(intent);
    }
}