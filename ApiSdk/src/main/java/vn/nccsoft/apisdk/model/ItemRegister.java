package vn.nccsoft.apisdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vancu on 13/12/2017.
 */

public class ItemRegister {
    @SerializedName("data")
    @Expose
    private ItemProfile data;
    @SerializedName("code")
    @Expose
    private Long code;

    public ItemProfile getData() {
        return data;
    }

    public void setData(ItemProfile data) {
        this.data = data;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
