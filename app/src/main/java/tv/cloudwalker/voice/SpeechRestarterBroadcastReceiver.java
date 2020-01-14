package tv.cloudwalker.voice;

import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SpeechRestarterBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "SpeechRestarterBroadcas";
    private static JobScheduler jobScheduler = null;

    @Override
    public void onReceive(Context context, Intent intent) {

//        Log.d(TAG, "about to start timer " + context.toString());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            scheduleJob(context);
//        } else {
//            ProcessMainClass bck = new ProcessMainClass();
//            bck.launchService(context);
//        }


        Log.i(SpeechRestarterBroadcastReceiver.class.getSimpleName(), "Service Stops! Oooooooooooooppppssssss!!!!");
        context.startService(new Intent(context, SpeechAccessibilityService.class));;
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public static void scheduleJob(Context context) {
//        if (jobScheduler == null) {
//            jobScheduler = (JobScheduler) context
//                    .getSystemService(JOB_SCHEDULER_SERVICE);
//        }
//        ComponentName componentName = new ComponentName(context,
//                JobService.class);
//        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
//                .setOverrideDeadline(0)
//                .setPersisted(true).build();
//
//        jobScheduler.schedule(jobInfo);
//    }
}
