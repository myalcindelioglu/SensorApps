package com.myd.cobiwebapps.dagger.modules.app;

import com.myd.cobiwebapps.dagger.annotations.Sensor;
import com.myd.cobiwebapps.data.source.WebAppSource;
import com.myd.cobiwebapps.data.source.sensor.SensorWebAppSource;
import com.myd.cobiwebapps.webapps.model.Accelerometer;
import com.myd.cobiwebapps.webapps.model.Location;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 11/22/17.
 *
 */

@Module
public class SensorWebAppSourceModule {

    @Singleton
    @Provides
    @Sensor
    WebAppSource<Accelerometer> provideAccelerometerSensorWebAppSource() {
        return new SensorWebAppSource<>();
    }

    @Singleton
    @Provides
    @Sensor
    WebAppSource<Location> provideLocationSensorWebAppSource() {
        return new SensorWebAppSource<>();
    }
}
