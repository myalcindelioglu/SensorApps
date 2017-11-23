package com.myd.cobiwebapps.webapps.presenter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.myd.cobiwebapps.contract.WebAppContract;
import com.myd.cobiwebapps.webapps.model.Accelerometer;

import javax.inject.Inject;

/**
 * Created by MYD on 11/23/17.
 *
 */

public class AccelerometerSensorListener implements SensorEventListener {

    private final static float SHAKE_THRESHOLD = 3.25f;

    private WebAppContract.Presenter<Accelerometer> presenter;

    @Inject
    public AccelerometerSensorListener(WebAppContract.Presenter<Accelerometer> presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSensorChanged(SensorEvent se) {
        Accelerometer data = new Accelerometer(
                se.values[0],
                se.values[1],
                se.values[2],
                System.currentTimeMillis()
        );
        data.setDate(System.currentTimeMillis());

        double acceleration = Math.sqrt(Math.pow(data.getX(), 2) +
                Math.pow(data.getY(), 2) +
                Math.pow(data.getZ(), 2)) - SensorManager.GRAVITY_EARTH;

        if (acceleration > SHAKE_THRESHOLD) {
            presenter.addData(data);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
