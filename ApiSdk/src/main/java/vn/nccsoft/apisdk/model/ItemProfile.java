package vn.nccsoft.apisdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vancu on 13/12/2017.
 */

public class ItemProfile {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private Object username;
    @SerializedName("fb_uid")
    @Expose
    private Object fbUid;
    @SerializedName("fb_token")
    @Expose
    private Object fbToken;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("game_id")
    @Expose
    private String gameId;
    @SerializedName("agency_id")
    @Expose
    private Object agencyId;
    @SerializedName("role")
    @Expose
    private Long role;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public Object getFbUid() {
        return fbUid;
    }

    public void setFbUid(Object fbUid) {
        this.fbUid = fbUid;
    }

    public Object getFbToken() {
        return fbToken;
    }

    public void setFbToken(Object fbToken) {
        this.fbToken = fbToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Object getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Object agencyId) {
        this.agencyId = agencyId;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
