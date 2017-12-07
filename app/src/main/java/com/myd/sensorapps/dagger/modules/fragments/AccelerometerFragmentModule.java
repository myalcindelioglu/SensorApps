package com.myd.sensorapps.dagger.modules.fragments;

import android.content.Context;
import android.hardware.SensorManager;

import com.myd.sensorapps.contract.SensorContract;
import com.myd.sensorapps.contract.AppContract;
import com.myd.sensorapps.data.source.repository.AppRepo;
import com.myd.sensorapps.coreapps.model.Accelerometer;
import com.myd.sensorapps.coreapps.presenter.Presenter;
import com.myd.sensorapps.coreapps.sensor.AccelerometerSensorListener;
import com.myd.sensorapps.coreapps.view.AccelerometerFragment;
import com.myd.sensorapps.coreapps.view.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 11/22/17.
 *
 */

@Module
public class AccelerometerFragmentModule {

    @Provides
    AppContract.Presenter<Accelerometer> provideAccelerometerPresenter(
            AppRepo<Accelerometer> repository,
            AccelerometerFragment view) {
        return new Presenter<>(repository, view);
    }

    @Provides
    AppContract.View<Accelerometer> provideAccelerometerView(
            AccelerometerFragment view) {
        return view;
    }

    @Provides
    AccelerometerSensorListener provideSensorListener(SensorContract.SensorObserver<Accelerometer> observer) {
        return new AccelerometerSensorListener(observer);
    }

    @Provides
    SensorContract.SensorObserver<Accelerometer> providesAccelerometerSensorObserver(
            AppContract.Presenter<Accelerometer> presenter) {
        return presenter::addDataAndUpdate;
    }

    @Provides
    SensorManager provideSensorManager(MainActivity activity) {
        return (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
    }

}
