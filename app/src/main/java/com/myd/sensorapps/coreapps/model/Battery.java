package com.myd.sensorapps.coreapps.model;

import com.myd.sensorapps.base.BaseModel;
import com.myd.sensorapps.util.DateTimeUtil;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by MYD on 11/19/17.
 *
 */

public class Battery extends RealmObject
        implements BaseModel {

    public Battery() {
        //realm:
        //must declare a public constructor with no arguments if it contains custom constructors.
    }

    public Battery(Float percentage, Boolean isCharging, Long date) {
        this.percentage = percentage;
        this.isCharging = isCharging;
        this.date = date;
    }

    @PrimaryKey
    private Integer id;
    private Float percentage;
    private Boolean isCharging;
    private Long date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Boolean getCharging() {
        return isCharging;
    }

    public void setCharging(Boolean charging) {
        isCharging = charging;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Battery: " +
                "id=" + id +
                ", percentage=" + percentage +
                ", isCharging=" + isCharging +
                ", date=" + DateTimeUtil.formatDate(date);
    }

}
