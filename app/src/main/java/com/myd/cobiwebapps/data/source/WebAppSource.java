package com.myd.cobiwebapps.data.source;

import com.myd.cobiwebapps.base.BaseModel;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by MYD on 11/20/17.
 *
 */

public interface WebAppSource<T extends BaseModel> {
    Single<List<T>> getData();
    Single<String> addData(T model);
    Single<List<String>> updateInfo();

}
