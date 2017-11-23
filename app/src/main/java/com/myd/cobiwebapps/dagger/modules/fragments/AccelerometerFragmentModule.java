package com.myd.cobiwebapps.dagger.modules.fragments;

import android.content.Context;
import android.hardware.SensorManager;

import com.myd.cobiwebapps.contract.WebAppContract;
import com.myd.cobiwebapps.data.source.repository.WebAppRepo;
import com.myd.cobiwebapps.webapps.model.Accelerometer;
import com.myd.cobiwebapps.webapps.presenter.AccelerometerSensorListener;
import com.myd.cobiwebapps.webapps.presenter.Presenter;
import com.myd.cobiwebapps.webapps.view.AccelerometerFragment;
import com.myd.cobiwebapps.webapps.view.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 11/22/17.
 *
 */

@Module
public class AccelerometerFragmentModule {

    @Provides
    WebAppContract.Presenter<Accelerometer> provideAccelerometerPresenter(WebAppRepo<Accelerometer> repository,
                                                           AccelerometerFragment view) {
        return new Presenter<>(repository, view);
    }

    @Provides
    WebAppContract.View<Accelerometer> provideAccelerometerView(
            AccelerometerFragment view) {
        return view;
    }

    @Provides
    AccelerometerSensorListener provideSensorListener(WebAppContract.Presenter<Accelerometer> presenter) {
        return new AccelerometerSensorListener(presenter);
    }

    @Provides
    SensorManager provideSensorManager(MainActivity activity) {
        return (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
    }

}
