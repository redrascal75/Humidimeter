package de.school.humidimeter.gui;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
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

import android.util.Log;
import android.view.View;

import java.util.List;

import de.school.humidimeter.R;
import de.school.humidimeter.logic.ScheduledJobService;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        startJobScheduler();

        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();

        //TODO: Request auf REST-Schnittstelle, um die Luftfeuchtigkeit zu erhalten
        int humidity = 50;
        if (humidity < 60) {
            fragment = new HomeDryFragment();
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();

        } else if (humidity >= 60 && humidity <= 80) {
            fragment = new HomeNormalFragment();
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();

        } else if (humidity > 80) {
            fragment = new HomeWetFragment();
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();

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

    void startJobScheduler() {
//        JobScheduler
        ComponentName componentName = new ComponentName(this, ScheduledJobService.class);
        JobInfo info = new JobInfo.Builder(1, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.schedule(info);

    }

    private void goToSettingsShowActivity() {
        Intent intent = new Intent(this, SettingsShowActivity.class);
        startActivity(intent);
    }
}