package com.vancuong.testcall;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vancu on 20/11/2017.
 */

public class TestCall {
    public static ArrayList<Photo> testCall(final OnCallBack onCallBack) {
     final ArrayList<Photo> list = new ArrayList<>();
        Call<List<Photo>> mCall = ApiUtils.getAPIService().getPhoto();
        mCall.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful()) {

                    list.addAll(response.body());
                    onCallBack.onSuccessResponse(list);
                         Log.i("test11", "test=" + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
            }
        });
        return list;
    }

}
