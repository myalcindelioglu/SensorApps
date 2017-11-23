package com.myd.cobiwebapps.dagger.modules.app;

import android.app.Application;
import android.content.Context;

import com.myd.cobiwebapps.dagger.components.activities.MainActivityComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 11/20/17.
 *
 */

@Module(subcomponents = MainActivityComponent.class)
public class AppModule {
    @Singleton
    @Provides
    Context provideContext (Application application) {
        return application;
    }
}
