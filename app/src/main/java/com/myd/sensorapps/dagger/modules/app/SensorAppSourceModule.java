package com.myd.sensorapps.dagger.modules.app;

import android.content.Context;

import com.myd.sensorapps.base.BaseSensor;
import com.myd.sensorapps.dagger.annotations.Sensor;
import com.myd.sensorapps.data.source.AppSource;
import com.myd.sensorapps.data.source.sensor.SensorAppSource;
import com.myd.sensorapps.coreapps.model.Accelerometer;
import com.myd.sensorapps.coreapps.model.Battery;
import com.myd.sensorapps.coreapps.model.Location;
import com.myd.sensorapps.coreapps.sensor.BatterySensor;
import com.myd.sensorapps.coreapps.sensor.LocationSensor;
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
public class SensorAppSourceModule {

    @Singleton
    @Provides
    @Sensor
    AppSource<Accelerometer> provideAccelerometerSensorAppSource(BaseSensor<Accelerometer> accelerometerSensor) {
        return new SensorAppSource<>(accelerometerSensor);
    }

    @Singleton
    @Provides
    BaseSensor<Accelerometer> provideAccelerometerSensor() {
        return Maybe::empty;
    }

    @Singleton
    @Provides
    @Sensor
    AppSource<Location> provideLocationSensorAppSource(BaseSensor<Location> locationSensor) {
        return new SensorAppSource<>(locationSensor);
    }

    @Singleton
    @Provides
    BaseSensor<Location> provideLocationSensor(Context context) {
        return new LocationSensor(context, new RxLocation(context));
    }

    @Singleton
    @Provides
    @Sensor
    AppSource<Battery> provideBatterySensorAppSource(BaseSensor<Battery> batterySensor) {
        return new SensorAppSource<>(batterySensor);
    }

    @Singleton
    @Provides
    BaseSensor<Battery> provideBatterySensor(Context context) {
        return new BatterySensor(context);
    }
}
