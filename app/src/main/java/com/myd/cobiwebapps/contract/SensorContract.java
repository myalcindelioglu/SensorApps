package com.myd.cobiwebapps.contract;

import com.myd.cobiwebapps.base.BaseModel;

/**
 * Created by MYD on 12/7/17.
 *
 */

public interface SensorContract {
    interface SensorObserver<T extends BaseModel> {
        void onChanged(T model);
    }
}
