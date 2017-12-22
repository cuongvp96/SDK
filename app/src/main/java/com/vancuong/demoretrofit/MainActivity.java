package com.vancuong.demoretrofit;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import vn.nccsoft.apisdk.ApiUtils;
import vn.nccsoft.apisdk.FragmentLogin;
import vn.nccsoft.apisdk.SdkManager;
import vn.nccsoft.apisdk.model.HomeWatcher;
import vn.nccsoft.apisdk.model.Report_new_register;
import vn.nccsoft.apisdk.model.Total_online;
import vn.nccsoft.apisdk.service.Login2mService;
import vn.nccsoft.apisdk.service.OnlineService;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    FragmentLogin fragmentLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_recall);
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final AppCompatActivity activity=this;
        final SdkManager sdkManager = new SdkManager(1,1);
        sdkManager.startLoginSDK(this);

        // testCall.insert_dlo(getApplicationContext(),daily_login_online);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Date date = new Date();
                // final Daily_login_online daily_login_online=new Daily_login_online(dateFormat.format(date),1,1,10,1);
                //Report_new_register report_new_register=new Report_new_register(1,1,1,1);
                // Revenue_agency revenue_agency=new Revenue_agency(1,10,1,20,50);
//                Total_online total_online = new Total_online(10, 1);
//                testCall.insert_to(getApplicationContext(), total_online);
                sdkManager.startLoginSDK(activity);
            }
        });
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo("vn.nccsoft.apisdk",PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String sign= Base64.encodeToString(md.digest(), Base64.DEFAULT);
//                Log.e("MY KEY HASH:", sign);
//                Toast.makeText(getApplicationContext(),sign,         Toast.LENGTH_LONG).show();
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//        } catch (NoSuchAlgorithmException e) {
//        }

//        testCall.startServiceLogin2m();
//        testCall.startServiceOnline();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       // fragmentLogin.onActivityResult(requestCode, resultCode, data);

      //  callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
