package com.vancuong.testcall.API;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


import com.vancuong.testcall.model.DailyLoginOnline;

import java.util.ArrayList;

/**
 * Created by vancu on 22/11/2017.
 */

public class AutoRequestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(),"Run",Toast.LENGTH_SHORT).show();
        OnCallBack onCallBack=new OnCallBack() {

            @Override
            public void onSuccessResponse(ArrayList<DailyLoginOnline> result) {
            }

            @Override
            public void onSuccessPost(String result) {
                Toast.makeText(getApplicationContext(),"Tes2",Toast.LENGTH_SHORT).show();
            }
        };
        CallApi.postAll(onCallBack);
            return START_STICKY;

    }

}
