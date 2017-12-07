package com.myd.sensorapps.application;

import android.app.Activity;
import android.app.Application;

import com.myd.sensorapps.dagger.components.app.AppComponent;
import com.myd.sensorapps.dagger.components.app.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by MYD on 11/20/17.
 *
 */

public class App extends Application implements HasActivityInjector {

    private AppComponent appComponent;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();


        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();

        appComponent.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
