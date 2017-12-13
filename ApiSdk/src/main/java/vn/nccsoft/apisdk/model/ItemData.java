package vn.nccsoft.apisdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vancu on 13/12/2017.
 */

public class ItemData {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("profile")
    @Expose
    private ItemProfile profile;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ItemProfile getProfile() {
        return profile;
    }

    public void setProfile(ItemProfile profile) {
        this.profile = profile;
    }
}
