package com.vancuong.testcall.API;

import com.vancuong.testcall.Photo;
import com.vancuong.testcall.model.DailyLoginOnline;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIServices {
    @POST("/posts")
    @FormUrlEncoded
    Call<String> postAll(@Field("datetime") String datetime,
                                    @Field("userID")Long userID,
                                    @Field("gameID") Long gameID,
                                    @Field("firtlogin")Boolean firtlogin
    );
    @GET("/")
    Call<ArrayList<DailyLoginOnline>> getJson();

}
