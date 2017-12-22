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
    Call<ItemsLogin> login(@Field("email") String email,
                           @Field("password") String password,
                           @Field("game_id") int game_id
    );
    @POST("/api/register")
    @FormUrlEncoded
    Call<ItemRegister> register(@Field("name") String name,
                                @Field("game_id") int game_id,
                                @Field("agency_id") int agency_id,
                                @Field("email") String email,
                                @Field("password") String password,
                                @Field("os_type") String os_type,
                                @Field("os_version") String os_version,
                                @Field("device_uid") String device_uid
    );
    @POST("api/fblogin")
    @FormUrlEncoded
    Call<ItemsLogin> login_fb(@Field("fb_uid") String fb_uid,
                                   @Field("fb_token") String fb_token,
                                   @Field("last_name") String last_name,
                                   @Field("first_name") String first_name,
                                   @Field("email") String email,
                                   @Field("phone") String phone,
                                   @Field("os_type") String os_type,
                                   @Field("os_version") String os_version,
                                   @Field("game_id") int game_id,
                                   @Field("agency_id") int agency_id,
                                   @Field("device_uid") String device_uid
    );
    @POST("/api/pay")
    @FormUrlEncoded
    Call<ItemsLogin> payment(@Field("pay_price") int pay_price,
                           @Field("pay_card_type") int pay_card_type,
                           @Field("description") String description
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
