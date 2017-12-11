package com.vancuong.testcall;

/**
 * Created by kien.lovan on 11/20/2017.
 */

public class ApiUtils {
    private ApiUtils() {}


    public static APIServices getAPIService() {

        return RetrofitClient.getClient().create(APIServices.class);
    }
}
