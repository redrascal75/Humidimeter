package de.school.humidimeter.gui;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.school.humidimeter.R;
import de.school.humidimeter.logic.JSONRequestData;
import de.school.humidimeter.logic.MeasuredData;
import de.school.humidimeter.logic.ScheduledJobService;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        startJobScheduler();

        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();



        //TODO: Request auf REST-Schnittstelle, um die Luftfeuchtigkeit zu erhalten


        JSONRequestData jsonRequestData = new JSONRequestData();
        MeasuredData mData = jsonRequestData.getDataRequest();


        int humidity = mData.getHumidity();
        Log.d(TAG, "Luftfeuchtigkeit: " + humidity + "%");

        if (humidity < 30) {
            fragment = new HomeDryFragment();
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();

        } else if (humidity >= 30 && humidity <= 70) {
            fragment = new HomeNormalFragment();
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();

        } else if (humidity > 70) {
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