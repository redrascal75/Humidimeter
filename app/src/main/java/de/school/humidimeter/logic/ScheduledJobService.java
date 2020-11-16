package de.school.humidimeter.logic;

import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import de.school.humidimeter.gui.HomeActivity;
import de.school.humidimeter.gui.HomeDryFragment;
import de.school.humidimeter.gui.HomeNormalFragment;
import de.school.humidimeter.gui.HomeWetFragment;

public class ScheduledJobService extends JobService {

    private static final String TAG = "ScheduledJobService";
    private boolean jobCancelled = false;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        doBackgroundWork(params);
        return true;
    }
    private void doBackgroundWork(final JobParameters params) {
        new Thread(() -> {
            //TODO: Laden der Daten aus der REST-Schnittstelle
            int recommendedVentilationTime = 15;

            Bundle b = new Bundle();



            switch (recommendedVentilationTime) {
                case 5:
                    //TODO: Schicke Push mit 3-5 Minuten Lüftungsempfehlung
                    b.putInt("time", recommendedVentilationTime);
                    break;
                case 10:
                    //TODO: Schicke Push mit 5-10 Minuten Lüftungsempfehlung
                    b.putInt("time", recommendedVentilationTime);
                    break;
                case 15:
                    //TODO: Schicke Push mit 15-20 Minuten Lüftungsempfehlung
                    b.putInt("time", recommendedVentilationTime);
                    break;
                default:
                    return;
            }

            startActivity(new Intent (this, HomeActivity.class), b);


            Log.d(TAG, "Job finished");
            jobFinished(params, true);
        }).start();
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }
}
