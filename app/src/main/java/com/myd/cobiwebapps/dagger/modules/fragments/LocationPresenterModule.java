package com.myd.cobiwebapps.dagger.modules.fragments;

import com.myd.cobiwebapps.contract.WebAppContract;
import com.myd.cobiwebapps.webapps.model.Location;
import com.myd.cobiwebapps.webapps.view.LocationFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 11/22/17.
 *
 */

@Module
public class LocationPresenterModule {

    @Singleton
    @Provides
    WebAppContract.View<Location> provideAccelerometerView(
            LocationFragment view) {
        return view;
    }
}
