package com.example.gpeegpee.oreobackgroundservicetestapplication.service;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothHeadset;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class SampleJobService extends JobService {
    private static final String TAG = "SampleJobService";
    private static final int JOB_ID = 1234;

    boolean isWorking = false;
    boolean jobCancelled = false;

    public static void schedule(Context context) {
        JobInfo sampleJob = new JobInfo.Builder(JOB_ID,
                new ComponentName(context.getPackageName(), SampleJobService.class.getName()))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setOverrideDeadline(1000)
                .build();

        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(sampleJob);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "SampleJobService was scheduled");
        } else {
            Log.e(TAG, "SampleJobService was not scheduled");
        }
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "StartService is created", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "StartService is destroyed", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob");
        Toast.makeText(this, "StartService is started", Toast.LENGTH_SHORT).show();

        isWorking = true;
        startWorkOnNewThread(params);
        return true;
    }

    private void startWorkOnNewThread(final JobParameters params) {
        new Thread(new Runnable() {
            public void run() {
                doWork(params);
            }
        }).start();
    }

    private void doWork(JobParameters params) {
        // 10 seconds of working (1000*10ms)
        while(true) {
            // If the job has been cancelled, stop working; the job will be rescheduled.
            if (jobCancelled) {
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                Log.e(TAG, "exception" + e);
            }
            Log.d(TAG, "JobService is still working");
        }
        Log.d(TAG, "Job finished!");
        isWorking = false;
        boolean needsReschedule = false;
        jobFinished(params, needsReschedule);
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob");
        Toast.makeText(this, "StartService is stopped", Toast.LENGTH_SHORT).show();
//        jobCancelled = true;
//        boolean needsReschedule = isWorking;
//        jobFinished(params, needsReschedule);
        return false;
    }


}
