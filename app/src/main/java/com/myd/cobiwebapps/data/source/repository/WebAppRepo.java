package com.myd.cobiwebapps.data.source.repository;

import com.myd.cobiwebapps.base.BaseModel;
import com.myd.cobiwebapps.dagger.annotations.Local;
import com.myd.cobiwebapps.dagger.annotations.Sensor;
import com.myd.cobiwebapps.data.source.WebAppSource;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by MYD on 11/20/17.
 *
 */

public class WebAppRepo<T extends BaseModel> implements WebAppSource<T> {
    private WebAppSource<T> localWebAppSource;
    private WebAppSource<T> sensorWebAppSource;

    @Inject
    public WebAppRepo(@Local WebAppSource<T> localWebAppSource,
                      @Sensor WebAppSource<T> sensorWebAppSource) {
        this.localWebAppSource = localWebAppSource;
        this.sensorWebAppSource = sensorWebAppSource;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Single<List<T>> getData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Maybe<T> getSingleData() {
        return sensorWebAppSource
                .getSingleData()
                .flatMap(x -> localWebAppSource.addData(x).toMaybe());
    }

    @Override
    public Single<T> addData(@Nullable T model) {
        return localWebAppSource.addData(model);
    }

    @Override
    public Single<List<T>> updateInfo() {
        return localWebAppSource.getData();
    }
}
