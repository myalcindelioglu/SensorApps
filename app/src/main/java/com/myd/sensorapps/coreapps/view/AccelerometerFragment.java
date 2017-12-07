package com.myd.sensorapps.coreapps.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myd.sensorapps.R;
import com.myd.sensorapps.base.BaseFragment;
import com.myd.sensorapps.coreapps.model.Accelerometer;
import com.myd.sensorapps.coreapps.sensor.AccelerometerSensorListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class AccelerometerFragment extends BaseFragment<Accelerometer> {

    @Inject
    AccelerometerSensorListener accelerometerSensorListener;

    @Inject
    SensorManager sensorManager;

    @BindView(R.id.fragment_accelerometer_text_view)
    public TextView textView;

    @BindView(R.id.fragment_accelerometer_empty_text_view)
    public TextView emptyTextView;

    @BindView(R.id.fragment_accelerometer_progress_bar)
    public ProgressBar progressBar;

    private Unbinder butterKnifeUnBinder;

    public static AccelerometerFragment newInstance() {
        Bundle args = new Bundle();
        AccelerometerFragment fragment = new AccelerometerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accelerometer, container, false);
        butterKnifeUnBinder = ButterKnife.bind(this, view);

        textView.setMovementMethod(new ScrollingMovementMethod());

        presenter.subscribe();
        presenter.fetchAndUpdate();

        return view;
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
        presenter.unSubscribe();
        sensorManager.unregisterListener(accelerometerSensorListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        butterKnifeUnBinder.unbind();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(Accelerometer data) {
        progressBar.setVisibility(View.INVISIBLE);
        textView.append(data.toString() + "\n");
        textView.setVisibility(View.VISIBLE);
        emptyTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEmptyState() {
        progressBar.setVisibility(View.INVISIBLE);
        emptyTextView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
    }
}
