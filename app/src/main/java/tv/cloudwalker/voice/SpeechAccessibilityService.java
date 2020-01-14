package tv.cloudwalker.voice;

import android.accessibilityservice.AccessibilityService;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

import com.vikramezhil.droidspeech.DroidSpeech;
import com.vikramezhil.droidspeech.OnDSListener;
import com.vikramezhil.droidspeech.OnDSPermissionsListener;

import java.util.List;
import java.util.Random;

public class SpeechAccessibilityService extends AccessibilityService implements OnDSListener, OnDSPermissionsListener
{
    private static final String TAG = "SpeechAccessibilityServ";
    private DroidSpeech droidSpeech ;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        settingSpeechApi();
        return START_STICKY;
    }

    private void settingSpeechApi(){

        // Initializing the droid speech and setting the listener
        droidSpeech = new DroidSpeech(this, null);
        droidSpeech.setOnDroidSpeechListener(this);
        droidSpeech.setShowRecognitionProgressView(true);
        droidSpeech.setOneStepResultVerify(true);
        droidSpeech.setRecognitionProgressMsgColor(Color.WHITE);
        droidSpeech.setOneStepVerifyConfirmTextColor(Color.WHITE);
        droidSpeech.setOneStepVerifyRetryTextColor(Color.WHITE);



        int[] colorPallets1 = new int[] {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA};
        int[] colorPallets2 = new int[] {Color.YELLOW, Color.RED, Color.CYAN, Color.BLUE, Color.GREEN};
        // Setting random color pallets to the recognition progress view
        droidSpeech.setRecognitionProgressViewColors(new Random().nextInt(2) == 0 ? colorPallets1 : colorPallets2);
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        Log.i(TAG, "onKeyEvent: "+event.getKeyCode());
        if (event.getAction()==KeyEvent.ACTION_DOWN && event.getKeyCode() == 84) {
            if(droidSpeech == null){
                settingSpeechApi();
            }
            droidSpeech.startDroidSpeechRecognition();
        }
        else if (event.getAction()==KeyEvent.ACTION_UP && event.getKeyCode() == 84) {
            if (droidSpeech != null) {
                droidSpeech.closeDroidSpeechOperations();
            }
        }

        return super.onKeyEvent(event);
    }

    @Override
    public void onDroidSpeechSupportedLanguages(String currentSpeechLanguage, List<String> supportedSpeechLanguages) {
        Log.i(TAG, "Current speech language = " + currentSpeechLanguage);
        Log.i(TAG, "Supported speech languages = " + supportedSpeechLanguages.toString());

        if(supportedSpeechLanguages.contains("en-IN"))
        {
            // Setting the droid speech preferred language as tamil if found
            droidSpeech.setPreferredLanguage("en-IN");

            // Setting the confirm and retry text in tamil
            droidSpeech.setOneStepVerifyConfirmText("Confirm text");
            droidSpeech.setOneStepVerifyRetryText("Retry text");
        }
    }


    @Override
    public void onDroidSpeechRmsChanged(float rmsChangedValue) {
        Log.i(TAG, "onDroidSpeechRmsChanged: ");
    }

    @Override
    public void onDroidSpeechLiveResult(String liveSpeechResult) {
        Log.d(TAG, "onDroidSpeechLiveResult: ");
    }

    @Override
    public void onDroidSpeechFinalResult(String finalSpeechResult) {
        Log.d(TAG, "onDroidSpeechFinalResult: "+finalSpeechResult);
    }


    @Override
    public void onDroidSpeechClosedByUser() {
        Log.d(TAG, "onDroidSpeechClosedByUser: ");
    }

    @Override
    public void onDroidSpeechError(String errorMsg) {
        Log.d(TAG, "onDrcloseDroidSpeechOperationsoidSpeechError: "+errorMsg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(droidSpeech != null){
            droidSpeech.closeDroidSpeechOperations();
        }

        Log.i("EXIT", "ondestroy!");
        Intent broadcastIntent = new Intent(this, SpeechRestarterBroadcastReceiver.class);
        sendBroadcast(broadcastIntent);
    }


    @Override
    public void onDroidSpeechAudioPermissionStatus(boolean audioPermissionGiven, String errorMsgIfAny) {
        Log.d(TAG, "onDroidSpeechAudioPermissionStatus: "+audioPermissionGiven);
    }

    private void primeSearch(String result){
        result = result.toLowerCase();
        switch (result)
        {
            case "go to youtube":
            case "youtube":
            case "move to youtube":
            case "open youtube":{
                Intent intent = getPackageManager().getLeanbackLaunchIntentForPackage("com.google.android.youtube.tv");
                if (intent != null){ startActivity(intent);}
            }
            break;
            case "go to hotstar":
            case "hotstar":
            case "move to hotstar":
            case "open hotstar":{
                Intent intent = getPackageManager().getLeanbackLaunchIntentForPackage("in.startv.hotstar");
                if (intent != null){ startActivity(intent);}
            }
            break;
            case "go to netflix":
            case "netflix":
            case "move to netflix":
            case "open netflix":{
                Intent intent = getPackageManager().getLeanbackLaunchIntentForPackage("com.netflix.mediaclient");
                if (intent != null){ startActivity(intent);}
            }
            break;
            case "go to amazon prime":
            case "go to prime":
            case "amazon prime":
            case "prime":
            case "move to amazon prime":
            case "move to prime":
            case "open amazon prime":
            case "open prime":{
                Intent intent = getPackageManager().getLeanbackLaunchIntentForPackage("com.amazon.avod.thirdpartyclient");
                if (intent != null){ startActivity(intent);}
            }
            break;
            case "go to hungama play":
            case "hungama play":
            case "move to hungama play":
            case "open hungama play":{
                Intent intent = getPackageManager().getLeanbackLaunchIntentForPackage("com.hungama.movies.tv");
                if (intent != null){ startActivity(intent);}
            }
            break;
            case "go to zee five":
            case "zee five":
            case "move to zee five":
            case "open zee five":{
                Intent intent = getPackageManager().getLeanbackLaunchIntentForPackage("com.zee5.aosp");
                if (intent != null){ startActivity(intent);}
            }
            break;
            case "go to voot":
            case "voot":
            case "move to voot":
            case "open voot":{
                Intent intent = getPackageManager().getLeanbackLaunchIntentForPackage("com.viacom18.tv.voot");
                if (intent != null){ startActivity(intent);}
            }
            break;
            case "go to movie box":
            case "movie box":
            case "move to movie box":
            case "open movie box":{
                Intent intent = getPackageManager().getLeanbackLaunchIntentForPackage("tv.cloudwalker.cloudwalkeruniverse");
                if (intent != null){ startActivity(intent);}
            }
            break;
            case "left":
            case "move left":
            case "go to left":
            case "go left":{
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(21);
            }
            break;
            case "right":
            case "move right":
            case "go to right":
            case "go right":{
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(22);
            }
            break;
            case "down":
            case "move down":
            case "go to down":
                case "go down":{
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(20);
            }
            break;
            case "up":
            case "move up":
            case "go to up":
                case "go up":{
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(20);
            }
            break;
            case "go to all apps":
            case "go all apps":
            case "all apps":
            case "move to all apps":
            {
                Log.d(TAG, "primeSearch: not implemented all apps");
            }
            break;
            case "go back":
            case "back":
            case "go to back":
            case "move back":
            {
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(3);
            }
            break;
            case "switch to HDMI one":
            case "go to HDMI one":
            case "move to HDMI one":
            case "HDMI one":
            {
                
            }
            break;
            case "switch to HDMI two":
            case "go to HDMI two":
            case "move to HDMI two":
            case "HDMI two":
            {
                
            }
            break;
            case "switch to HDMI three":
            case "go to HDMI three":
            case "move to HDMI three":
            case "HDMI three":
            {
                
            }
            break;
            case "switch to AV one":
            case "go to AV one":
            case "move to AV one":
            case "AV one":
            {

            }
            break;
            case "switch to AV two":
            case "go to AV two":
            case "move to AV two":
            case "AV two":
            {

            }
            break;
            case "switch to AV three":
            case "go to AV three":
            case "move to AV three":
            case "AV three":
            {

            }
            break;
            case "switch to T V":
            case "go to T V":
            case "move to T V":
            case "T V":
            {

            }
            break;
            case "open":{
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(23);
            }
            break;
            default:{

            }
        }
    }
}
