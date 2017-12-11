package vn.nccsoft.apilogin;

public class ApiUtils {
    private ApiUtils() {}


    public static APIServices getAPIService() {

        return RetrofitClient.getClientLogin().create(APIServices.class);
    }
}
