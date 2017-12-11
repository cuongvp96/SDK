package com.vancuong.testcall;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kien.lovan on 11/20/2017.
 */

public class RetrofitClient {
    final static String url="https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;
    public static String MyauthHeaderContent = "Bearer {your_token}";

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private  static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(logging).addNetworkInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("Authorization", MyauthHeaderContent);

            return chain.proceed(builder.build());
        }
    });

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
