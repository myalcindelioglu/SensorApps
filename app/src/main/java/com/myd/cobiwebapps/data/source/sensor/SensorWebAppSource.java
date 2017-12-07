package com.myd.cobiwebapps.data.source.sensor;

import com.myd.cobiwebapps.base.BaseModel;
import com.myd.cobiwebapps.base.BaseSensor;
import com.myd.cobiwebapps.data.source.WebAppSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by MYD on 11/21/17.
 *
 */

public class SensorWebAppSource<T extends BaseModel>
        implements WebAppSource<T> {

    private BaseSensor<T> sensor;

    @Inject
    public SensorWebAppSource(BaseSensor<T> sensor) {
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
