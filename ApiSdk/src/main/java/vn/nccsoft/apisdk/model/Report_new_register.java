package vn.nccsoft.apisdk.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vancu on 07/12/2017.
 */

public class Report_new_register implements Serializable {
    private int report_id;
    private int user_id;
    private int game_id;
    private int rnr_online_2m;
    private Date rnr_time;
    private int agency_id;

    public Report_new_register() {
    }

    public Report_new_register(int user_id, int game_id, int agency_id) {
        this.user_id = user_id;
        this.game_id = game_id;
        this.rnr_online_2m = 0;
        this.agency_id = agency_id;
    }


    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
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

    public int getRnr_online_2m() {
        return rnr_online_2m;
    }

    public void setRnr_online_2m(int rnr_online_2m) {
        this.rnr_online_2m = rnr_online_2m;
    }

    public Date getRnr_time() {
        return rnr_time;
    }

    public void setRnr_time(Date rnr_time) {
        this.rnr_time = rnr_time;
    }

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }
}
