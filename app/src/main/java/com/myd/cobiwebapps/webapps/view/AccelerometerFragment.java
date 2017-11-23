package com.myd.cobiwebapps.webapps.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myd.cobiwebapps.base.BaseFragment;
import com.myd.cobiwebapps.webapps.model.Accelerometer;
import com.myd.cobiwebapps.webapps.presenter.AccelerometerPresenter;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class AccelerometerFragment extends BaseFragment<Accelerometer> {
    private static final String TAG = "AccelerometerFragment";

    @Inject
    AccelerometerPresenter presenter;

    @Inject
    SensorManager sensorManager;

    private AccelerometerSensorListener accelerometerSensorListener;

    public static AccelerometerFragment newInstance() {
        Bundle args = new Bundle();
        AccelerometerFragment fragment = new AccelerometerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        presenter.subscribe();
        presenter.updateData();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);

        accelerometerSensorListener = new AccelerometerSensorListener();

        sensorManager.registerListener(accelerometerSensorListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sensorManager.unregisterListener(accelerometerSensorListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(String data) {
        progressBar.setVisibility(View.INVISIBLE);
        textView.append(data + "\n");
        textView.setVisibility(View.VISIBLE);
        emptyTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmptyState() {
        progressBar.setVisibility(View.INVISIBLE);
        emptyTextView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
    }

    public class AccelerometerSensorListener implements SensorEventListener {

        private final static float SHAKE_THRESHOLD = 3.25f;

        private Accelerometer lastData;

        AccelerometerSensorListener() {
            lastData = new Accelerometer(
                    0f,
                    0f,
                    0f,
                    0L
            );
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

            lastData = data;

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }
}
