package com.myd.sensorapps.data.source;

import com.myd.sensorapps.base.BaseModel;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by MYD on 11/20/17.
 *
 */

public interface AppSource<T extends BaseModel> {
    Single<List<T>> getData();
    Maybe<T> getSingleData();
    Single<T> addData(T model);
    Single<List<T>> updateInfo();

}
