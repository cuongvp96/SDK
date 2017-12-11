package vn.nccsoft.apisdk.model;

/**
 * Created by vancu on 07/12/2017.
 */

public class Daily_login_online {
    private String dlo_datetime;
    private int user_id;
    private int game_id;
    int firt_login;

    int agency_id;

    public Daily_login_online() {
    }

    public Daily_login_online(int user_id, int game_id, int firt_login, int agency_id) {
        this.user_id = user_id;
        this.game_id = game_id;
        this.firt_login = firt_login;
        this.agency_id = agency_id;
    }

    public String getDlo_datetime() {
        return dlo_datetime;
    }

    public void setDlo_datetime(String dlo_datetime) {
        this.dlo_datetime = dlo_datetime;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getFirt_login() {
        return firt_login;
    }

    public void setFirt_login(int firt_login) {
        this.firt_login = firt_login;
    }



    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }
}
