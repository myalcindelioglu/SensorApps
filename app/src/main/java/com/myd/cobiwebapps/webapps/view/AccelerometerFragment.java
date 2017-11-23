package com.myd.cobiwebapps.webapps.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.myd.cobiwebapps.base.BaseFragment;
import com.myd.cobiwebapps.webapps.model.Accelerometer;
import com.myd.cobiwebapps.webapps.presenter.AccelerometerSensorListener;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class AccelerometerFragment extends BaseFragment<Accelerometer> {
    private static final String TAG = "AccelerometerFragment";

    @Inject
    AccelerometerSensorListener accelerometerSensorListener;

    @Inject
    SensorManager sensorManager;

    public static AccelerometerFragment newInstance() {
        Bundle args = new Bundle();
        AccelerometerFragment fragment = new AccelerometerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);

        sensorManager.registerListener(accelerometerSensorListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sensorManager.unregisterListener(accelerometerSensorListener);
    }
}
