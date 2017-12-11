package vn.nccsoft.apilogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vancu on 07/12/2017.
 */

public class Item {
    @SerializedName("token")
    @Expose
    private String token;

    public Item(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
