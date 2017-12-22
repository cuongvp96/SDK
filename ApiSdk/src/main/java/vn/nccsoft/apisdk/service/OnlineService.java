package vn.nccsoft.apisdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import vn.nccsoft.apisdk.ApiUtils;
import vn.nccsoft.apisdk.model.Report_new_register;


/**
 * Created by TWO on 11/20/2017.
 */

public class OnlineService extends Service {
    private Handler handler;
    private long delay = 30*60*1000;
    private String packageName;
    Runnable runnable = new Runnable() {
        public void run() {
            if (!ApiUtils.isAppRunning(getApplicationContext(), packageName)){
                stopSelf();
                Toast.makeText(getApplicationContext(),"online stop",Toast.LENGTH_SHORT).show();
                Log.i("online","stop");
            }
            Toast.makeText(getApplicationContext(),"online restart",Toast.LENGTH_SHORT).show();
            //insert data
            Log.i("online","Restart");
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
            Toast.makeText(getApplicationContext(),"online start",Toast.LENGTH_SHORT).show();
            Log.i("online","start");
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
