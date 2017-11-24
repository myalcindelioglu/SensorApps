package com.myd.cobiwebapps.webapps.presenter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.myd.cobiwebapps.contract.WebAppContract;
import com.myd.cobiwebapps.webapps.model.Accelerometer;

import javax.inject.Inject;

/**
 * Created by MYD on 11/23/17.
 *
 */

public class AccelerometerSensorListener implements SensorEventListener {

    private static final long INTERVAL = 1000L;
    private long lastDate = 0L;

    private WebAppContract.Presenter<Accelerometer> presenter;

    @Inject
    public AccelerometerSensorListener(WebAppContract.Presenter<Accelerometer> presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSensorChanged(SensorEvent se) {
        if ((System.currentTimeMillis() - lastDate) > INTERVAL ) {
            Accelerometer data = new Accelerometer(
                    se.values[0],
                    se.values[1],
                    se.values[2],
                    System.currentTimeMillis()
            );
            presenter.addData(data);
            lastDate = data.getDate();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
