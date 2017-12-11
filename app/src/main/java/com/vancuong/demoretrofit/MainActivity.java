package com.vancuong.demoretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import vn.nccsoft.apisdk.ApiUtils;
import vn.nccsoft.apisdk.SdkManager;
import vn.nccsoft.apisdk.model.HomeWatcher;
import vn.nccsoft.apisdk.model.Report_new_register;
import vn.nccsoft.apisdk.model.Total_online;
import vn.nccsoft.apisdk.service.Login2mService;
import vn.nccsoft.apisdk.service.OnlineService;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_recall);
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


        final SdkManager testCall = new SdkManager();
        // testCall.insert_dlo(getApplicationContext(),daily_login_online);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Date date = new Date();
                // final Daily_login_online daily_login_online=new Daily_login_online(dateFormat.format(date),1,1,10,1);
                //Report_new_register report_new_register=new Report_new_register(1,1,1,1);
                // Revenue_agency revenue_agency=new Revenue_agency(1,10,1,20,50);
                Total_online total_online = new Total_online(10, 1);
                testCall.insert_to(getApplicationContext(), total_online);
            }
        });
        startServiceLogin2m();
        clickHome();

    }

    private void clickHome() {
        HomeWatcher homeWatcher = new HomeWatcher(this);
        homeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                //checkHome = true;
                stopServiceLogin2m();
            }
            @Override
            public void onHomeLongPressed() {

            }
        });
        homeWatcher.startWatch();
    }

    public void startServiceLogin2m() {
        Intent mIntent = new Intent(this, Login2mService.class);
        Bundle mBundle = new Bundle();
        Report_new_register report_new_register=new Report_new_register(1,1,1);
         mIntent.putExtra("report_new_register",report_new_register);
        startService(mIntent);

    }
    public void startServiceOnline() {
        Intent mIntent = new Intent(this, OnlineService.class);
        Bundle mBundle = new Bundle();
        Report_new_register report_new_register=new Report_new_register(1,1,1);
        mIntent.putExtra("time_delay",5000);
        startService(mIntent);
    }

    public void stopServiceLogin2m() {
        stopService(new Intent(getBaseContext(), Login2mService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopServiceLogin2m();
    }

}
