package vn.nccsoft.apisdk.model;

import java.io.Serializable;

/**
 * Created by vancu on 07/12/2017.
 */

public class Revenue_agency implements Serializable {
    private int agency_id;
    private float revenue;
    private int game_id;
    private float percent_share;
    private int total_userpay;

    public Revenue_agency(int agency_id, float revenue, int game_id, float percent_share, int total_userpay) {
        this.agency_id = agency_id;
        this.revenue = revenue;
        this.game_id = game_id;
        this.percent_share = percent_share;
        this.total_userpay = total_userpay;
    }

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public float getPercent_share() {
        return percent_share;
    }

    public void setPercent_share(float percent_share) {
        this.percent_share = percent_share;
    }

    public int getTotal_userpay() {
        return total_userpay;
    }

    public void setTotal_userpay(int total_userpay) {
        this.total_userpay = total_userpay;
    }
}
