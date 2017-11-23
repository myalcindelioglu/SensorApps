package com.myd.cobiwebapps.dagger.modules.activities;

import android.app.Activity;

import com.myd.cobiwebapps.dagger.components.activities.MainActivityComponent;
import com.myd.cobiwebapps.webapps.view.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by MYD on 11/20/17.
 *
 */

@Module
public abstract class ActivityBuilderModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindsMainActivity(MainActivityComponent.Builder builder);
}
