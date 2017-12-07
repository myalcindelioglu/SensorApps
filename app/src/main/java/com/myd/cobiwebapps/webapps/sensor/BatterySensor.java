package com.myd.cobiwebapps.webapps.sensor;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

import com.myd.cobiwebapps.base.BaseSensor;
import com.myd.cobiwebapps.webapps.model.Battery;

import io.reactivex.Maybe;

/**
 * Created by MYD on 12/7/17.
 *
 */

public class BatterySensor implements BaseSensor<Battery> {

    private static final String TAG = "BatterySensor";

    private final IntentFilter iFilter;
    private final Intent batteryStatus;

    public BatterySensor(Context context) {
        iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatus = context.registerReceiver(null, iFilter);
    }

    @Override
    public Maybe<Battery> getSingleData() {
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

        return Maybe.just(battery);
    }
}
