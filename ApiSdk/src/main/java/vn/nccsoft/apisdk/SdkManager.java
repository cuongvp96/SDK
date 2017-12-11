package vn.nccsoft.apisdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.nccsoft.apisdk.model.Daily_login_online;
import vn.nccsoft.apisdk.model.Item;
import vn.nccsoft.apisdk.model.MessageServer;
import vn.nccsoft.apisdk.model.Report_new_register;
import vn.nccsoft.apisdk.model.Revenue_agency;
import vn.nccsoft.apisdk.model.Total_online;

/**
 * Created by vancu on 20/11/2017.
 */

public class SdkManager {
    public static void getTokenLogin(final Context context, String username, String password) {

        Call<Item> mCall = ApiUtils.getAPIServiceLogin().login(username,password);
        mCall.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(response.isSuccessful()) {
                    String  token = response.body().getToken();
                    SharedPrefsUtils sharedPrefsUtils=new SharedPrefsUtils();
                    sharedPrefsUtils.setStringPreference(context,"token_login",token);
                }
            }
            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }

    public static void insert_dlo(final Context context,Daily_login_online daily_login_online){
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Call<MessageServer> mCall = ApiUtils.getAPIService().insert_dlo(dateFormat.format(date),daily_login_online.getUser_id(),
        daily_login_online.getGame_id(),daily_login_online.getFirt_login(),daily_login_online.getAgency_id());
        mCall.enqueue(new Callback<MessageServer>() {
            @Override
            public void onResponse(Call<MessageServer> call, Response<MessageServer> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Thanh cong: "+response.body().getSuccess().toString(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<MessageServer> call, Throwable t) {

            }
        });
    }
    public static void insert_rnr(final Context context,Report_new_register report_new_register){

        Call<MessageServer> mCall = ApiUtils.getAPIService().insert_rnr(report_new_register.getUser_id(),report_new_register.getGame_id(),
        report_new_register.getRnr_online_2m(),report_new_register.getAgency_id());
        mCall.enqueue(new Callback<MessageServer>() {
            @Override
            public void onResponse(Call<MessageServer> call, Response<MessageServer> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Thanh cong: "+response.body().getSuccess().toString(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<MessageServer> call, Throwable t) {

            }
        });
    }
    public static void insert_ra(final Context context, Revenue_agency revenue_agency){

        Call<MessageServer> mCall = ApiUtils.getAPIService().insert_ra(revenue_agency.getAgency_id(),revenue_agency.getRevenue(),
                revenue_agency.getGame_id(),revenue_agency.getPercent_share(),revenue_agency.getTotal_userpay());
        mCall.enqueue(new Callback<MessageServer>() {
            @Override
            public void onResponse(Call<MessageServer> call, Response<MessageServer> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Thanh cong: "+response.body().getSuccess().toString(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<MessageServer> call, Throwable t) {

            }
        });
    }
    public static void insert_to(final Context context, Total_online total_online){

        Call<MessageServer> mCall = ApiUtils.getAPIService().insert_to(total_online.getAmount_user(),
                total_online.getGame_id());
        mCall.enqueue(new Callback<MessageServer>() {
            @Override
            public void onResponse(Call<MessageServer> call, Response<MessageServer> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Thanh cong: "+response.body().getSuccess().toString(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<MessageServer> call, Throwable t) {

            }
        });
    }

}
