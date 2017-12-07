package com.myd.sensorapps.data.source.repository;

import com.myd.sensorapps.base.BaseModel;
import com.myd.sensorapps.dagger.annotations.Local;
import com.myd.sensorapps.dagger.annotations.Sensor;
import com.myd.sensorapps.data.source.AppSource;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by MYD on 11/20/17.
 *
 */

public class AppRepo<T extends BaseModel> implements AppSource<T> {
    private AppSource<T> localAppSource;
    private AppSource<T> sensorAppSource;

    @Inject
    public AppRepo(@Local AppSource<T> localAppSource,
                   @Sensor AppSource<T> sensorAppSource) {
        this.localAppSource = localAppSource;
        this.sensorAppSource = sensorAppSource;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Single<List<T>> getData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Maybe<T> getSingleData() {
        return sensorAppSource
                .getSingleData()
                .flatMap(x -> localAppSource.addData(x).toMaybe());
    }

    @Override
    public Single<T> addData(@Nullable T model) {
        return localAppSource.addData(model);
    }

    @Override
    public Single<List<T>> updateInfo() {
        return localAppSource.getData();
    }
}
