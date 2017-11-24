package com.myd.cobiwebapps.dagger.modules.app;

import android.content.Context;

import com.myd.cobiwebapps.dagger.annotations.Local;
import com.myd.cobiwebapps.data.realm.DbMigration;
import com.myd.cobiwebapps.data.source.WebAppSource;
import com.myd.cobiwebapps.data.source.local.LocalWebAppSource;
import com.myd.cobiwebapps.webapps.model.Accelerometer;
import com.myd.cobiwebapps.webapps.model.Battery;
import com.myd.cobiwebapps.webapps.model.Location;

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
public class LocalWebAppSourceModule {

    private static final int DATABASE_VERSION = 1;

    @Singleton
    @Provides
    @Local
    WebAppSource<Accelerometer> providesAccelerometerLocalWebAppSource(Realm realm, Class<Accelerometer> clazz) {
        return new LocalWebAppSource<>(realm, clazz);
    }

    @Singleton
    @Provides
    @Local
    WebAppSource<Location> providesLocationLocalWebAppSource(Realm realm, Class<Location> clazz) {
        return new LocalWebAppSource<>(realm, clazz);
    }

    @Singleton
    @Provides
    @Local
    WebAppSource<Battery> providesBatteryLocalWebAppSource(Realm realm, Class<Battery> clazz) {
        return new LocalWebAppSource<>(realm, clazz);
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
