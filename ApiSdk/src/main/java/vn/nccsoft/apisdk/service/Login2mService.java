package vn.nccsoft.apisdk.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import vn.nccsoft.apisdk.ApiUtils;
import vn.nccsoft.apisdk.SdkManager;
import vn.nccsoft.apisdk.model.Daily_login_online;
import vn.nccsoft.apisdk.model.HomeWatcher;
import vn.nccsoft.apisdk.model.Report_new_register;
import vn.nccsoft.apisdk.model.User;


public class Login2mService extends Service {
    Report_new_register report_new_register = new Report_new_register();
    private Handler handler;
    private int timeOnline = 11000;
    public static String TAG = "TEST_API";
    private String packageName;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (report_new_register != null && ApiUtils.isAppRunning(getApplicationContext(), packageName)) {
                report_new_register.setRnr_online_2m(1);
                SdkManager.insert_rnr(getApplicationContext(), report_new_register);
                Log.i("online2m", "ruuning");
                Toast.makeText(getApplicationContext(), "online 2m start", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getApplicationContext(), "online 2m stop", Toast.LENGTH_SHORT).show();
            Log.i("online2m", "stop");
            stopSelf();
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        handler.postDelayed(runnable, timeOnline);
        Log.i("online2m", "start");
        if (intent != null && intent.getExtras() != null) {
            packageName = intent.getExtras().getString("packageName");
            report_new_register = (Report_new_register) intent.getExtras().getSerializable("report_new_register");
        }

        isMyServiceRunning(Login2mService.class);
        return START_NOT_STICKY;
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void clickHome() {
        HomeWatcher homeWatcher = new HomeWatcher(this);
        homeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                //checkHome = true;
                stopSelf();
            }

            @Override
            public void onHomeLongPressed() {

            }
        });
        homeWatcher.startWatch();
    }
}
