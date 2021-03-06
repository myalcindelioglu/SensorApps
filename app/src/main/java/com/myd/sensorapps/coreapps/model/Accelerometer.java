package com.myd.sensorapps.coreapps.model;

import com.myd.sensorapps.base.BaseModel;
import com.myd.sensorapps.util.DateTimeUtil;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by MYD on 11/19/17.
 *
 */

public class Accelerometer extends RealmObject
        implements BaseModel {

    public Accelerometer() {
        //realm:
        //must declare a public constructor with no arguments if it contains custom constructors.
    }

    public Accelerometer(Float x, Float y, Float z, Long date) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.date = date;
    }

    @PrimaryKey
    private Integer id = 0;
    private Float x = 0f;
    private Float y = 0f;
    private Float z = 0f;
    private Long date = 0L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getZ() {
        return z;
    }

    public void setZ(Float z) {
        this.z = z;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Accelerometer: "+
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", date=" + DateTimeUtil.formatDate(date);
    }

}
