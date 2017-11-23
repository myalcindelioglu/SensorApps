package com.myd.cobiwebapps.dagger.modules.fragments;

import com.myd.cobiwebapps.data.source.repository.WebAppRepo;
import com.myd.cobiwebapps.webapps.model.Location;
import com.myd.cobiwebapps.webapps.presenter.LocationPresenter;
import com.myd.cobiwebapps.webapps.view.LocationFragment;
import com.myd.cobiwebapps.webapps.view.MainActivity;
import com.patloew.rxlocation.RxLocation;
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
    LocationPresenter provideAccelerometerPresenter(WebAppRepo<Location> repository,
                                                    LocationFragment view) {
        return new LocationPresenter(repository, view);
    }

    @Provides
    RxPermissions provideRxPermissions(MainActivity activity) {
        return new RxPermissions(activity);
    }

    @Provides
    RxLocation provideRxLocation(MainActivity activity) {
        return new RxLocation(activity);
    }

}
