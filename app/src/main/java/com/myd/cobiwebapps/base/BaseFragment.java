package com.myd.cobiwebapps.base;

import android.support.v4.app.Fragment;

import com.myd.cobiwebapps.contract.WebAppContract;

import javax.inject.Inject;

/**
 * Created by MYD on 11/22/17.
 *
 */

public abstract class BaseFragment<T extends BaseModel>
        extends Fragment implements WebAppContract.View<T> {

    @Inject
    public WebAppContract.Presenter<T> presenter;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void showError() {

    }

}
