package com.vancuong.testcall.API;

public class ApiUtils {
    private ApiUtils() {}


    public static APIServices getAPIService() {

        return RetrofitClient.getClient().create(APIServices.class);
    }
}
