package com.myd.cobiwebapps.dagger.modules.fragments;

import com.myd.cobiwebapps.contract.WebAppContract;
import com.myd.cobiwebapps.webapps.model.Accelerometer;
import com.myd.cobiwebapps.webapps.view.AccelerometerFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 11/22/17.
 *
 */

@Module
public class AccelerometerPresenterModule {

    @Singleton
    @Provides
    WebAppContract.View<Accelerometer> provideAccelerometerView(
            AccelerometerFragment view) {
        return view;
    }
}
