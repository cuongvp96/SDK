package vn.nccsoft.apisdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import vn.nccsoft.apisdk.ApiUtils;
import vn.nccsoft.apisdk.model.Report_new_register;


/**
 * Created by TWO on 11/20/2017.
 */

public class OnlineService extends Service {
    private Handler handler;
    private long delay = 5000;
    String packageName;
    Runnable runnable = new Runnable() {
        public void run() {
            if (!ApiUtils.isAppRunning(getApplicationContext(), packageName)){
                stopSelf();
            }
            //insert data
                handler.postDelayed(this, delay);
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

        if (intent != null && intent.getExtras() != null) {
            packageName = intent.getExtras().getString("packageName");
            delay = intent.getExtras().getLong("time_delay");
        }
        handler.postDelayed(runnable, delay);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

}
