package com.myd.sensorapps.dagger.modules.app;

import com.myd.sensorapps.dagger.annotations.Local;
import com.myd.sensorapps.dagger.annotations.Sensor;
import com.myd.sensorapps.dagger.components.activities.MainActivityComponent;
import com.myd.sensorapps.data.source.AppSource;
import com.myd.sensorapps.data.source.repository.AppRepo;
import com.myd.sensorapps.coreapps.model.Accelerometer;
import com.myd.sensorapps.coreapps.model.Battery;
import com.myd.sensorapps.coreapps.model.Location;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 11/22/17.
 *
 */

@Module(subcomponents = MainActivityComponent.class)
public class AppRepoModule {

    @Singleton
    @Provides
    AppRepo<Accelerometer> provideAccelerometerAppRepo(
            @Local AppSource<Accelerometer> localAccelerometerApp,
            @Sensor AppSource<Accelerometer> sensorAccelerometerApp) {

        return new AppRepo<>(localAccelerometerApp, sensorAccelerometerApp);
    }

    @Singleton
    @Provides
    AppRepo<Location> provideLocationAppRepo(
            @Local AppSource<Location> localLocationApp,
            @Sensor AppSource<Location> sensorLocationApp) {

        return new AppRepo<>(localLocationApp, sensorLocationApp);
    }

    @Singleton
    @Provides
    AppRepo<Battery> provideBatteryAppRepo(
            @Local AppSource<Battery> localBatteryApp,
            @Sensor AppSource<Battery> sensorBatteryApp) {

        return new AppRepo<>(localBatteryApp, sensorBatteryApp);
    }
}
