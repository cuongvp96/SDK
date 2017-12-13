package vn.nccsoft.apisdk;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.nccsoft.apisdk.model.ItemRegister;
import vn.nccsoft.apisdk.model.ItemsLogin;
import vn.nccsoft.apisdk.model.MessageServer;

public interface APIServices {
    @POST("/api/login")
    @FormUrlEncoded
    Call<ItemsLogin> login(@Field("email") String username,
                           @Field("password") String password,
                           @Field("game_id") String game_id
    );
    @POST("/api/register")
    @FormUrlEncoded
    Call<ItemRegister> register(@Field("email") String username,
                                @Field("password") String password,
                                @Field("game_id") String game_id,
                                @Field("name") String name
    );
    @POST("api/fblogin")
    @FormUrlEncoded
    Call<ItemsLogin> login_fb(@Field("fb_uid") String fb_uid,
                                @Field("fb_token") String fb_token,
                                @Field("last_name") String last_name,
                                @Field("first_name") String first_name,
                                @Field("email") String email,
                                @Field("phone") String phone
    );
    @POST("/description/daily_login_online.php")
    @FormUrlEncoded
    Call<MessageServer> insert_dlo(@Field("dlo_datetime") String dlo_datetime,
                                   @Field("user_id") int user_id,
                                   @Field("game_id") int game_id,
                                   @Field("firt_login") int firt_login,
                                   @Field("agency_id") int agency_id
    );

    @POST("/description/new_register_user.php")
    @FormUrlEncoded
    Call<MessageServer> insert_rnr(@Field("user_id") int user_id,
                                   @Field("game_id") int game_id,
                                   @Field("rnr_online_2m") int rnr_online_2m,
                                   @Field("agency_id") int agency_id
    );

    @POST("/description/revenue_agency.php")
    @FormUrlEncoded
    Call<MessageServer> insert_ra(@Field("agency_id") int agency_id,
                                  @Field("revenue") float revenue,
                                  @Field("game_id") int game_id,
                                  @Field("percent_share") float percent_share,
                                  @Field("total_userpay") int total_userpay
    );

    @POST("/description/total_online.php")
    @FormUrlEncoded
    Call<MessageServer> insert_to(@Field("amount_user") int amount_user,
                                  @Field("game_id") int game_id
    );
}
