package com.myd.cobiwebapps.dagger.modules.fragments;

import com.myd.cobiwebapps.contract.WebAppContract;
import com.myd.cobiwebapps.data.source.repository.WebAppRepo;
import com.myd.cobiwebapps.webapps.model.Location;
import com.myd.cobiwebapps.webapps.presenter.Presenter;
import com.myd.cobiwebapps.webapps.view.LocationFragment;
import com.myd.cobiwebapps.webapps.view.MainActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 11/22/17.
 *
 */

@Module
public class LocationFragmentModule {

    @Provides
    WebAppContract.Presenter<Location> provideAccelerometerPresenter(WebAppRepo<Location> repository,
                                                           LocationFragment view) {
        return new Presenter<>(repository, view);
    }

    @Provides
    WebAppContract.View<Location> provideAccelerometerView(
            LocationFragment view) {
        return view;
    }

    @Provides
    RxPermissions provideRxPermissions(MainActivity activity) {
        return new RxPermissions(activity);
    }

}
