package com.myd.cobiwebapps.data.source.sensor;

import com.myd.cobiwebapps.base.BaseModel;
import com.myd.cobiwebapps.data.source.WebAppSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by MYD on 11/21/17.
 *
 */

public class SensorWebAppSource<T extends BaseModel>
        implements WebAppSource<T> {

    @Inject
    public SensorWebAppSource() {
    }

    @Override
    public Single<List<T>> getData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Single<String> addData(T model) {
        return Single.just(model.toString());
    }

    @Override
    public Single<List<String>> updateInfo() {
        throw new UnsupportedOperationException();
    }
}
