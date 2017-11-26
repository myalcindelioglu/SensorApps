package com.myd.cobiwebapps;

import com.myd.cobiwebapps.webapps.model.Accelerometer;
import com.myd.cobiwebapps.webapps.model.Battery;
import com.myd.cobiwebapps.webapps.model.Location;

/**
 * Created by MYD on 11/25/17.
 *
 */

public class WebAppTestUtils {
    static Accelerometer generateAccelerometer() {
        Accelerometer accelerometer = new Accelerometer(2f, 3f, 4f, System.currentTimeMillis());
        accelerometer.setId(1);
        return accelerometer;
    }

    static Location generateLocation() {
        Location location = new Location(32.0, 29.0, System.currentTimeMillis());
        location.setId(1);
        return location;
    }

    public static Battery generateBattery(boolean isCharging) {
        Battery battery = new Battery(0.2f, isCharging, System.currentTimeMillis());
        battery.setId(1);
        return battery;
    }
}
