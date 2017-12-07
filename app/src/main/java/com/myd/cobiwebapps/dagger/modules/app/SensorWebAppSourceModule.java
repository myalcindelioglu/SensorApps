package com.myd.cobiwebapps.dagger.modules.app;

import android.content.Context;

import com.myd.cobiwebapps.base.BaseSensor;
import com.myd.cobiwebapps.dagger.annotations.Sensor;
import com.myd.cobiwebapps.data.source.WebAppSource;
import com.myd.cobiwebapps.data.source.sensor.SensorWebAppSource;
import com.myd.cobiwebapps.webapps.model.Accelerometer;
import com.myd.cobiwebapps.webapps.model.Battery;
import com.myd.cobiwebapps.webapps.model.Location;
import com.myd.cobiwebapps.webapps.sensor.BatterySensor;
import com.myd.cobiwebapps.webapps.sensor.LocationSensor;
import com.patloew.rxlocation.RxLocation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Maybe;

/**
 * Created by MYD on 11/22/17.
 *
 */

@Module
public class SensorWebAppSourceModule {

    @Singleton
    @Provides
    @Sensor
    WebAppSource<Accelerometer> provideAccelerometerSensorWebAppSource(BaseSensor<Accelerometer> accelerometerSensor) {
        return new SensorWebAppSource<>(accelerometerSensor);
    }

    @Singleton
    @Provides
    BaseSensor<Accelerometer> provideAccelerometerSensor() {
        return Maybe::empty;
    }

    @Singleton
    @Provides
    @Sensor
    WebAppSource<Location> provideLocationSensorWebAppSource(BaseSensor<Location> locationSensor) {
        return new SensorWebAppSource<>(locationSensor);
    }

    @Singleton
    @Provides
    BaseSensor<Location> provideLocationSensor(Context context) {
        return new LocationSensor(context, new RxLocation(context));
    }

    @Singleton
    @Provides
    @Sensor
    WebAppSource<Battery> provideBatterySensorWebAppSource(BaseSensor<Battery> batterySensor) {
        return new SensorWebAppSource<>(batterySensor);
    }

    @Singleton
    @Provides
    BaseSensor<Battery> provideBatterySensor(Context context) {
        return new BatterySensor(context);
    }
}
