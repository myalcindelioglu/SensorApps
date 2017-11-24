package com.myd.cobiwebapps.webapps.view;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

import com.myd.cobiwebapps.base.BaseFragment;
import com.myd.cobiwebapps.webapps.model.Accelerometer;
import com.myd.cobiwebapps.webapps.model.Battery;

import java.util.concurrent.TimeUnit;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class BatteryFragment extends BaseFragment<Battery> {

    private static final String TAG = "BatteryFragment";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static BatteryFragment newInstance() {
        Bundle args = new Bundle();
        BatteryFragment fragment = new BatteryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);

        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = getContext().registerReceiver(null, iFilter);

        compositeDisposable.add(
                Observable.interval(1000L, TimeUnit.MILLISECONDS).timeInterval()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(x -> {
                            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                            Log.d(TAG, "Battery status: " + status);
                            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                                    status == BatteryManager.BATTERY_STATUS_FULL;

                            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                            float batteryPercent = level / (float)scale;

                            Battery battery = new Battery(
                                    batteryPercent,
                                    isCharging,
                                    System.currentTimeMillis());

                            presenter.addData(battery);

                        }));

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
