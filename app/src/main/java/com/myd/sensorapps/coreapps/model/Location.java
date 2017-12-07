package com.myd.sensorapps.coreapps.model;

import com.myd.sensorapps.base.BaseModel;
import com.myd.sensorapps.util.DateTimeUtil;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by MYD on 11/19/17.
 *
 */

public class Location extends RealmObject
        implements BaseModel {

    public Location() {
        //realm:
        //must declare a public constructor with no arguments if it contains custom constructors.
    }

    public Location(Double lat, Double lon, Long date) {
        this.lat = lat;
        this.lon = lon;
        this.date = date;
    }

    @PrimaryKey
    private Integer id;
    private Double lat;
    private Double lon;
    private Long date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Location: " +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", date=" + DateTimeUtil.formatDate(date);
    }

}
