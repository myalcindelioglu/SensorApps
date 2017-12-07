package com.myd.sensorapps.dagger.modules.app;

import android.content.Context;

import com.myd.sensorapps.dagger.annotations.Local;
import com.myd.sensorapps.data.realm.DbMigration;
import com.myd.sensorapps.data.source.AppSource;
import com.myd.sensorapps.data.source.local.LocalAppSource;
import com.myd.sensorapps.coreapps.model.Accelerometer;
import com.myd.sensorapps.coreapps.model.Battery;
import com.myd.sensorapps.coreapps.model.Location;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by MYD on 11/22/17.
 *
 */

@Module
public class LocalAppSourceModule {

    private static final int DATABASE_VERSION = 1;

    @Singleton
    @Provides
    @Local
    AppSource<Accelerometer> providesAccelerometerLocalAppSource(Realm realm, Class<Accelerometer> clazz) {
        return new LocalAppSource<>(realm, clazz);
    }

    @Singleton
    @Provides
    @Local
    AppSource<Location> providesLocationLocalAppSource(Realm realm, Class<Location> clazz) {
        return new LocalAppSource<>(realm, clazz);
    }

    @Singleton
    @Provides
    @Local
    AppSource<Battery> providesBatteryLocalAppSource(Realm realm, Class<Battery> clazz) {
        return new LocalAppSource<>(realm, clazz);
    }

    @Provides
    @Singleton
    Realm provideRealm(RealmConfiguration config) {
        Realm.setDefaultConfiguration(config);
        try {
            return Realm.getDefaultInstance();
        } catch (Exception e) {
            Realm.deleteRealm(config);
            Realm.setDefaultConfiguration(config);
            return Realm.getDefaultInstance();
        }
    }

    @Provides
    @Singleton
    RealmConfiguration provideRealmConfig(Context context) {
        Realm.init(context);

        return new RealmConfiguration.Builder()
                .schemaVersion(DATABASE_VERSION)
                .migration(new DbMigration())
                .build();
    }

    @Provides
    @Singleton
    Class<Accelerometer> provideAccelerometerClass() {
        return Accelerometer.class;
    }

    @Provides
    @Singleton
    Class<Location> provideLocationClass() {
        return Location.class;
    }

    @Provides
    @Singleton
    Class<Battery> provideBatteryClass() {
        return Battery.class;
    }
}
