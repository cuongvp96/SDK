package vn.nccsoft.apisdk.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import vn.nccsoft.apisdk.SdkManager;
import vn.nccsoft.apisdk.model.Daily_login_online;
import vn.nccsoft.apisdk.model.Report_new_register;
import vn.nccsoft.apisdk.model.User;


public class Login2mService extends Service{
Report_new_register report_new_register=new Report_new_register();
    private Handler handler;
    private int timeOnline = 2000;
    public static String TAG = "TEST_API";
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(report_new_register!=null) {
                report_new_register.setRnr_online_2m(1);
                SdkManager.insert_rnr(getApplicationContext(), report_new_register);
            }
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
        if (intent !=null && intent.getExtras()!=null)
            report_new_register= (Report_new_register) intent.getExtras().getSerializable("report_new_register");

        isMyServiceRunning(Login2mService.class);
        return START_NOT_STICKY;
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i(TAG, "post submitted to API.");
                return true;
            }
        }
        return false;
    }

}
