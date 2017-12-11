package vn.nccsoft.apilogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface APIServices {
    @POST("/consumer/login/")
    @FormUrlEncoded
    Call<Item> login(@Field("username") String username,
                     @Field("password") String password
    );
}
