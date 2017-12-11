package com.vancuong.testcall.API;

import com.vancuong.testcall.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vancu on 22/11/2017.
 */

public class CallApi {
    public static void postAll(final OnCallBack onCallBack){
        Call<String> mCall = ApiUtils.getAPIService().postAll("",1l,1l,true);
        mCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    onCallBack.onSuccessPost(response.toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
