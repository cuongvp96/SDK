package vn.nccsoft.apisdk;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

public class ApiUtils {
    private ApiUtils() {}


    public static APIServices getAPIService() {

        return RetrofitClient.getClient().create(APIServices.class);
    }
    public static APIServices getAPIServiceLogin() {

        return RetrofitClient.getClientLogin().create(APIServices.class);
    }
    public static boolean isAppRunning(final Context context, final String packageName) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        if (procInfos != null)
        {
            for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                if (processInfo.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
