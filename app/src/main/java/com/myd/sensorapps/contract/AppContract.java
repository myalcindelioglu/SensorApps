package com.myd.sensorapps.contract;

import com.myd.sensorapps.base.BaseModel;
import com.myd.sensorapps.base.BasePresenter;
import com.myd.sensorapps.base.BaseView;

/**
 * Created by MYD on 11/21/17.
 *
 */

public interface AppContract {
    interface View<T extends BaseModel> extends BaseView<Presenter<T>> {
        void showProgress();

        void showData(T data);

        void showError();

        void showEmptyState();

    }

    interface Presenter<T extends BaseModel> extends BasePresenter {
        void fetchAndUpdate();

        void addDataAndUpdate(T model);

        void getDataAndUpdate();
    }
}
