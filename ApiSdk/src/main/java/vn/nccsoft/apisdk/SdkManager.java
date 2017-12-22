package vn.nccsoft.apisdk;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.nccsoft.apisdk.model.Daily_login_online;
import vn.nccsoft.apisdk.model.ItemRegister;
import vn.nccsoft.apisdk.model.ItemsLogin;
import vn.nccsoft.apisdk.model.MessageServer;
import vn.nccsoft.apisdk.model.Report_new_register;
import vn.nccsoft.apisdk.model.Revenue_agency;
import vn.nccsoft.apisdk.model.Total_online;
import vn.nccsoft.apisdk.service.Login2mService;
import vn.nccsoft.apisdk.service.OnlineService;

/**
 * Created by vancu on 20/11/2017.
 */

public class SdkManager {

    private int gameid;
    private int agency_id;

    public SdkManager(int gameid, int agency_id) {
        this.gameid = gameid;
        this.agency_id = agency_id;
    }

    public FragmentLogin startLoginSDK(Activity activity) {

        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentLogin fragmentLogin = new FragmentLogin();
//        fragmentLogin.setCancelable(false);
        fragmentLogin.setGame_id(getGameid());
        fragmentLogin.setAgency_id(getAgency_id());
        fragmentLogin.show(fragmentManager, "dialog");
        return fragmentLogin;
    }

    public static void startServiceLogin2m(final Context context, String packageName, Report_new_register report_new_register) {
        Intent mIntent = new Intent(context, Login2mService.class);
        Bundle mBundle = new Bundle();
        // Report_new_register report_new_register=new Report_new_register(1,1,1);
        mIntent.putExtra("packageName", packageName);
        mIntent.putExtra("report_new_register", report_new_register);
        context.startService(mIntent);
    }

    public static void startServiceOnline(final Context context, String packageName) {
        Intent mIntent = new Intent(context, OnlineService.class);
        Bundle mBundle = new Bundle();
        mIntent.putExtra("packageName", packageName);
//        Report_new_register report_new_register=new Report_new_register(1,1,1);
        mIntent.putExtra("time_delay", 30*60*1000L);
        context.startService(mIntent);
    }

    public static void login(final Context context, String username, String password, int game_id, final SuccessCallBack onCallBack) {

        Call<ItemsLogin> mCall = ApiUtils.getAPIServiceAPI().login(username, password, game_id);
        mCall.enqueue(new Callback<ItemsLogin>() {
            @Override
            public void onResponse(Call<ItemsLogin> call, Response<ItemsLogin> response) {
                if (response.isSuccessful() && response.body().getCode() == 1) {
                    String token = response.body().getData().getToken();
                    SharedPrefsUtils sharedPrefsUtils = new SharedPrefsUtils();
                    sharedPrefsUtils.setStringPreference(context, "token_login", token);
                    onCallBack.onSuccessResponse("1");
                    Toast.makeText(context, "Login success!", Toast.LENGTH_SHORT).show();
                } else {
                    onCallBack.onSuccessResponse("2");
                    Toast.makeText(context, "Login failed code 2!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemsLogin> call, Throwable t) {
                onCallBack.onSuccessResponse("3");
                Toast.makeText(context, "Login failed code 3!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void loginFB(final Context context, String fb_uid, String fb_token, String last_name,
                               String first_name, String email, String phone, String os_type, String os_version,
                               int game_id, int agency_id, String device_uid, final SuccessCallBack onCallBack) {

        Call<ItemsLogin> mCall = ApiUtils.getAPIServiceAPI().login_fb(fb_uid, fb_token, last_name,
                first_name, email, phone, os_type, os_version, game_id, agency_id, device_uid);
        mCall.enqueue(new Callback<ItemsLogin>() {
            @Override
            public void onResponse(Call<ItemsLogin> call, Response<ItemsLogin> response) {
                if (response.isSuccessful() && response.body().getCode() == 1) {
                    String token = response.body().getData().getToken();
                    SharedPrefsUtils sharedPrefsUtils = new SharedPrefsUtils();
                    sharedPrefsUtils.setStringPreference(context, "token_login", token);
                    onCallBack.onSuccessResponse("1");
                    Toast.makeText(context, "Login success!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Login failed error code 2!", Toast.LENGTH_SHORT).show();
                    onCallBack.onSuccessResponse("2" +
                            "");
                }
            }

            @Override
            public void onFailure(Call<ItemsLogin> call, Throwable t) {
                onCallBack.onSuccessResponse("3");
                Toast.makeText(context, "Login failed error code 3!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void payment(final Context context, int pay_price, int pay_card_type,
                               String description, final SuccessCallBack onCallBack) {

        Call<ItemsLogin> mCall = ApiUtils.getAPIServiceAPI().payment(pay_price, pay_card_type, description);
        mCall.enqueue(new Callback<ItemsLogin>() {
            @Override
            public void onResponse(Call<ItemsLogin> call, Response<ItemsLogin> response) {
                if (response.isSuccessful() && response.body().getCode() == 1) {
                    onCallBack.onSuccessResponse("1");
                    Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error code 2!", Toast.LENGTH_SHORT).show();
                    onCallBack.onSuccessResponse("2" +
                            "");
                }
            }

            @Override
            public void onFailure(Call<ItemsLogin> call, Throwable t) {
                onCallBack.onSuccessResponse("3");
                Toast.makeText(context, "Error code 3!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static void offline(final SuccessCallBack onCallBack) {

        Call<ItemsLogin> mCall = ApiUtils.getAPIServiceAPI().offline();
        mCall.enqueue(new Callback<ItemsLogin>() {
            @Override
            public void onResponse(Call<ItemsLogin> call, Response<ItemsLogin> response) {
                if (response.isSuccessful() && response.body().getCode() == 1) {
                    onCallBack.onSuccessResponse("1");
//                    Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(context, "Error code 2!", Toast.LENGTH_SHORT).show();
                    onCallBack.onSuccessResponse("2" + "");
                }
            }

            @Override
            public void onFailure(Call<ItemsLogin> call, Throwable t) {
                onCallBack.onSuccessResponse("3");
//                Toast.makeText(context, "Error code 3!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static void newRegister(final SuccessCallBack onCallBack) {

        Call<ItemsLogin> mCall = ApiUtils.getAPIServiceAPI().newRegister();
        mCall.enqueue(new Callback<ItemsLogin>() {
            @Override
            public void onResponse(Call<ItemsLogin> call, Response<ItemsLogin> response) {
                if (response.isSuccessful() && response.body().getCode() == 1) {
                    onCallBack.onSuccessResponse("1");
//                    Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(context, "Error code 2!", Toast.LENGTH_SHORT).show();
                    onCallBack.onSuccessResponse("2" + "");
                }
            }

            @Override
            public void onFailure(Call<ItemsLogin> call, Throwable t) {
                onCallBack.onSuccessResponse("3");
//                Toast.makeText(context, "Error code 3!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static void refreshToken(final SuccessCallBack onCallBack) {

        Call<ItemsLogin> mCall = ApiUtils.getAPIServiceAPI().refreshToken();
        mCall.enqueue(new Callback<ItemsLogin>() {
            @Override
            public void onResponse(Call<ItemsLogin> call, Response<ItemsLogin> response) {
                if (response.isSuccessful() && response.body().getCode() == 1) {
                    onCallBack.onSuccessResponse("1");
//                    Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(context, "Error code 2!", Toast.LENGTH_SHORT).show();
                    onCallBack.onSuccessResponse("2" + "");
                }
            }

            @Override
            public void onFailure(Call<ItemsLogin> call, Throwable t) {
                onCallBack.onSuccessResponse("3");
//                Toast.makeText(context, "Error code 3!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static void register(final Context context, String name, int game_id, int agency_id,
                                String email, String password, String os_type, String os_version, String device_uid, final SuccessCallBack onCallBack) {

        Call<ItemRegister> mCall = ApiUtils.getAPIServiceAPI().register(name, game_id, agency_id, email,
                password, os_type, os_version, device_uid);
        mCall.enqueue(new Callback<ItemRegister>() {
            @Override
            public void onResponse(Call<ItemRegister> call, Response<ItemRegister> response) {
                if (response.isSuccessful() && response.body().getCode() == 1) {
                    Toast.makeText(context, "Sign Up Success!", Toast.LENGTH_SHORT).show();
                    onCallBack.onSuccessResponse("1");
//                        String token = response.body().getData().getToken();
//                        SharedPrefsUtils sharedPrefsUtils = new SharedPrefsUtils();
//                        sharedPrefsUtils.setStringPreference(context, "token_login", token);
                } else {
                    onCallBack.onSuccessResponse("2");
                    Toast.makeText(context, "Signup failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemRegister> call, Throwable t) {
                onCallBack.onSuccessResponse("3");
                Toast.makeText(context, "Signup failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void insert_dlo(final Context context, Daily_login_online daily_login_online) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Call<MessageServer> mCall = ApiUtils.getAPIServiceAPI().insert_dlo(dateFormat.format(date), daily_login_online.getUser_id(),
                daily_login_online.getGame_id(), daily_login_online.getFirt_login(), daily_login_online.getAgency_id());
        mCall.enqueue(new Callback<MessageServer>() {
            @Override
            public void onResponse(Call<MessageServer> call, Response<MessageServer> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "successfull: " + response.body().getSuccess().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MessageServer> call, Throwable t) {

            }
        });
    }

    public static void insert_rnr(final Context context, Report_new_register report_new_register) {

        Call<MessageServer> mCall = ApiUtils.getAPIServiceAPI().insert_rnr(report_new_register.getUser_id(), report_new_register.getGame_id(),
                report_new_register.getRnr_online_2m(), report_new_register.getAgency_id());
        mCall.enqueue(new Callback<MessageServer>() {
            @Override
            public void onResponse(Call<MessageServer> call, Response<MessageServer> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "successfull: " + response.body().getSuccess().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MessageServer> call, Throwable t) {

            }
        });
    }

    public static void insert_ra(final Context context, Revenue_agency revenue_agency) {

        Call<MessageServer> mCall = ApiUtils.getAPIServiceAPI().insert_ra(revenue_agency.getAgency_id(), revenue_agency.getRevenue(),
                revenue_agency.getGame_id(), revenue_agency.getPercent_share(), revenue_agency.getTotal_userpay());
        mCall.enqueue(new Callback<MessageServer>() {
            @Override
            public void onResponse(Call<MessageServer> call, Response<MessageServer> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "successfull " + response.body().getSuccess().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MessageServer> call, Throwable t) {

            }
        });
    }

    public static void insert_to(final Context context, Total_online total_online) {

        Call<MessageServer> mCall = ApiUtils.getAPIServiceAPI().insert_to(total_online.getAmount_user(),
                total_online.getGame_id());
        mCall.enqueue(new Callback<MessageServer>() {
            @Override
            public void onResponse(Call<MessageServer> call, Response<MessageServer> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "successfull " + response.body().getSuccess().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MessageServer> call, Throwable t) {

            }
        });
    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }
}
