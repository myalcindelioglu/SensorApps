package com.myd.sensorapps.data.source.sensor;

import com.myd.sensorapps.base.BaseModel;
import com.myd.sensorapps.base.BaseSensor;
import com.myd.sensorapps.data.source.AppSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by MYD on 11/21/17.
 *
 */

public class SensorAppSource<T extends BaseModel>
        implements AppSource<T> {

    private BaseSensor<T> sensor;

    @Inject
    public SensorAppSource(BaseSensor<T> sensor) {
        this.sensor = sensor;
    }

    @Override
    public Maybe<T> getSingleData() {
        return sensor.getSingleData();
    }

    @Override
    public Single<List<T>> getData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Single<T> addData(T model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Single<List<T>> updateInfo() {
        throw new UnsupportedOperationException();
    }
}
