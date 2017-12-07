package com.myd.sensorapps.dagger.modules.fragments;

import com.myd.sensorapps.contract.AppContract;
import com.myd.sensorapps.data.source.repository.AppRepo;
import com.myd.sensorapps.coreapps.model.Location;
import com.myd.sensorapps.coreapps.presenter.Presenter;
import com.myd.sensorapps.coreapps.view.LocationFragment;
import com.myd.sensorapps.coreapps.view.MainActivity;
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
    AppContract.Presenter<Location> provideAccelerometerPresenter(AppRepo<Location> repository,
                                                                  LocationFragment view) {
        return new Presenter<>(repository, view);
    }

    @Provides
    AppContract.View<Location> provideAccelerometerView(
            LocationFragment view) {
        return view;
    }

    @Provides
    RxPermissions provideRxPermissions(MainActivity activity) {
        return new RxPermissions(activity);
    }

}
