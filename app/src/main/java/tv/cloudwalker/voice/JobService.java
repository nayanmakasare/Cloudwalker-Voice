package tv.cloudwalker.voice;

import android.app.job.JobParameters;
import android.util.Log;

public class JobService extends android.app.job.JobService {
    private static String TAG = JobService.class.getSimpleName();
    private static SpeechRestarterBroadcastReceiver restartSensorServiceReceiver;
    private static JobService instance;
    private static JobParameters jobParameters;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
//        ProcessMainClass bck = new ProcessMainClass();
//        bck.launchService(this);
//        registerRestarterReceiver();
//        instance = this;
//        JobService.jobParameters = jobParameters;

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(TAG, "Stopping job");
//        Intent broadcastIntent = new Intent(Globals.RESTART_INTENT);
//        sendBroadcast(broadcastIntent);
//        // give the time to run
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                unregisterReceiver(restartSensorServiceReceiver);
//            }
//        }, 2000);
        return false;
    }
}
