package de.school.humidimeter.logic;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import de.school.humidimeter.R;
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

//            showNotification(recommendedVentilationTime);
            b.putInt("time", recommendedVentilationTime);

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

//    private void showNotification(int recommendedVentilationTime) {
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle("Lüften notwendig!")
//                .setContentText("Empfohlene Dauer: " + recommendedVentilationTime + " Minuten");
//
//        Intent resultIntent = new Intent(this, HomeActivity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(HomeActivity.class);
//
//        // Adds the Intent that starts the Activity to the top of the stack
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//        mBuilder.setContentIntent(resultPendingIntent);
//
//
//        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        // notificationID allows you to update the notification later on.
//        mNotificationManager.notify(0, mBuilder.build());
//    }
}
