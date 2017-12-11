package com.vancuong.demoretrofit;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by vancu on 22/11/2017.
 */

public class AutoTest extends Service {
    private Handler handler;
    private int delay = 4000;
    Runnable runnable = new Runnable() {
        public void run() {
            Toast.makeText(getApplicationContext(), "Tes3", Toast.LENGTH_SHORT).show();
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
        handler.postDelayed(runnable, delay);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
