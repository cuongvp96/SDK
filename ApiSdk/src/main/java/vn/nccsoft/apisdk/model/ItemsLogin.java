package vn.nccsoft.apisdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vancu on 07/12/2017.
 */

public class ItemsLogin {
    @SerializedName("data")
    @Expose
    private ItemData data;
    @SerializedName("code")
    @Expose
    private Long code;

    public ItemData getData() {
        return data;
    }

    public void setData(ItemData data) {
        this.data = data;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
