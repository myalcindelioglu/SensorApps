package com.myd.cobiwebapps.contract;

import com.myd.cobiwebapps.base.BaseModel;
import com.myd.cobiwebapps.base.BasePresenter;
import com.myd.cobiwebapps.base.BaseView;

/**
 * Created by MYD on 11/21/17.
 *
 */

public interface WebAppContract {
    interface View<T extends BaseModel> extends BaseView<Presenter<T>> {
        void showProgress();

        void showData(String data);

        void showError();

        void showEmptyState();

    }

    interface Presenter<T extends BaseModel> extends BasePresenter {
        void updateData();

        void addData(T model);
    }
}
