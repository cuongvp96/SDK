package com.vancuong.testcall;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by kien.lovan on 11/20/2017.
 */

public interface APIServices {
    @POST("/posts")
    @FormUrlEncoded
    Call<Photo> savePost(@Field("albumId") Integer albumId,
                         @Field("id") Integer id,
                         @Field("title") String title,
                         @Field("url") String url,
                         @Field("thumbnailUrl") String thumbnailUrl
                         );
    @GET("/photos")
    Call<ArrayList<Photo>> getJson();

    @GET("/photos")
    Call<List<Photo>> getPhoto();
}
