package com.myd.cobiwebapps.dagger.modules.app;

import com.myd.cobiwebapps.dagger.annotations.Local;
import com.myd.cobiwebapps.dagger.annotations.Sensor;
import com.myd.cobiwebapps.dagger.components.activities.MainActivityComponent;
import com.myd.cobiwebapps.data.source.WebAppSource;
import com.myd.cobiwebapps.data.source.repository.WebAppRepo;
import com.myd.cobiwebapps.webapps.model.Accelerometer;
import com.myd.cobiwebapps.webapps.model.Location;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 11/22/17.
 *
 */

@Module(subcomponents = MainActivityComponent.class)
public class WebAppRepoModule {

    @Singleton
    @Provides
    WebAppRepo<Accelerometer> provideAccelerometerWebAppRepo(
            @Local WebAppSource<Accelerometer> localAccelerometerWebApp,
            @Sensor WebAppSource<Accelerometer> sensorAccelerometerWebApp,
            Class<Accelerometer> clazz) {

        return new WebAppRepo<>(localAccelerometerWebApp, sensorAccelerometerWebApp, clazz);
    }

    @Singleton
    @Provides
    WebAppRepo<Location> provideLocationWebAppRepo(
            @Local WebAppSource<Location> localAccelerometerWebApp,
            @Sensor WebAppSource<Location> sensorAccelerometerWebApp,
            Class<Location> clazz) {

        return new WebAppRepo<>(localAccelerometerWebApp, sensorAccelerometerWebApp, clazz);
    }
}
