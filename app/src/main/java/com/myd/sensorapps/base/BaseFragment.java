package com.myd.sensorapps.base;

import android.support.v4.app.Fragment;

import com.myd.sensorapps.contract.AppContract;

import javax.inject.Inject;

/**
 * Created by MYD on 11/22/17.
 *
 */

public abstract class BaseFragment<T extends BaseModel>
        extends Fragment implements AppContract.View<T> {

    @Inject
    public AppContract.Presenter<T> presenter;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void showError() {

    }

}
