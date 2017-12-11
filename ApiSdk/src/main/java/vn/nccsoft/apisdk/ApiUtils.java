package vn.nccsoft.apisdk;

public class ApiUtils {
    private ApiUtils() {}


    public static APIServices getAPIService() {

        return RetrofitClient.getClient().create(APIServices.class);
    }
    public static APIServices getAPIServiceLogin() {

        return RetrofitClient.getClientLogin().create(APIServices.class);
    }
}
