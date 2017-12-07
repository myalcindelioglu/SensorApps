package com.myd.sensorapps.dagger.components.activities;

import com.myd.sensorapps.dagger.annotations.ActivityScope;
import com.myd.sensorapps.dagger.modules.activities.MainActivityModule;
import com.myd.sensorapps.dagger.modules.fragments.FragmentBuilderModule;
import com.myd.sensorapps.coreapps.view.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by MYD on 11/20/17.
 *
 */

@ActivityScope
@Subcomponent(modules = {MainActivityModule.class,
        FragmentBuilderModule.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
