package com.myd.sensorapps.base;

import io.reactivex.Maybe;

/**
 * Created by MYD on 12/6/17.
 *
 */

public interface BaseSensor<T extends BaseModel> {
    Maybe<T> getSingleData();
}
