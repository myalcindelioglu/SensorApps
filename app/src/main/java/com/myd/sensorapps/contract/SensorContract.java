package com.myd.sensorapps.contract;

import com.myd.sensorapps.base.BaseModel;

/**
 * Created by MYD on 12/7/17.
 *
 */

public interface SensorContract {
    interface SensorObserver<T extends BaseModel> {
        void onChanged(T model);
    }
}
