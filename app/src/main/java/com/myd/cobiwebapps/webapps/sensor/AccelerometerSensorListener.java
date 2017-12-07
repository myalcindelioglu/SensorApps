package com.myd.cobiwebapps.webapps.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.myd.cobiwebapps.base.BaseSensor;
import com.myd.cobiwebapps.contract.SensorContract;
import com.myd.cobiwebapps.webapps.model.Accelerometer;

import javax.inject.Inject;

import io.reactivex.Maybe;

/**
 * Created by MYD on 11/23/17.
 *
 */

public class AccelerometerSensorListener
        implements SensorEventListener, BaseSensor<Accelerometer> {

    private final static float SHAKE_THRESHOLD = 3.25f;
    private Accelerometer lastData = new Accelerometer();

    private SensorContract.SensorObserver<Accelerometer> observer;

    @Inject
    public AccelerometerSensorListener(SensorContract.SensorObserver<Accelerometer> observer) {
        this.observer = observer;
    }

    @Override
    public void onSensorChanged(SensorEvent se) {
        Accelerometer data = new Accelerometer(
                se.values[0],
                se.values[1],
                se.values[2],
                System.currentTimeMillis()
        );

        double acceleration = Math.sqrt(Math.pow(data.getX(), 2) +
                Math.pow(data.getY(), 2) +
                Math.pow(data.getZ(), 2)) - SensorManager.GRAVITY_EARTH;

        if (acceleration > SHAKE_THRESHOLD) {
            lastData = data;
            observer.onChanged(data);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public Maybe<Accelerometer> getSingleData() {
        return Maybe.just(lastData);
    }
}