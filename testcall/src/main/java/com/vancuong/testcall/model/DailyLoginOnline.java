package com.vancuong.testcall.model;

/**
 * Created by vancu on 20/11/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyLoginOnline {

    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("userID")
    @Expose
    private Long userID;
    @SerializedName("gameID")
    @Expose
    private Long gameID;
    @SerializedName("firtlogin")
    @Expose
    private Boolean firtlogin;



    public DailyLoginOnline() {
    }

    public DailyLoginOnline(String datetime, Long userID, Long gameID, Boolean firtlogin) {
        this.datetime = datetime;
        this.userID = userID;
        this.gameID = gameID;
        this.firtlogin = firtlogin;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getGameID() {
        return gameID;
    }

    public void setGameID(Long gameID) {
        this.gameID = gameID;
    }

    public Boolean getFirtlogin() {
        return firtlogin;
    }

    public void setFirtlogin(Boolean firtlogin) {
        this.firtlogin = firtlogin;
    }


}