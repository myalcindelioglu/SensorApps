package com.myd.cobiwebapps.dagger.components.activities;

import com.myd.cobiwebapps.dagger.annotations.ActivityScope;
import com.myd.cobiwebapps.dagger.modules.fragments.AccelerometerPresenterModule;
import com.myd.cobiwebapps.dagger.modules.fragments.FragmentBuilderModule;
import com.myd.cobiwebapps.dagger.modules.fragments.LocationPresenterModule;
import com.myd.cobiwebapps.dagger.modules.MainActivityModule;
import com.myd.cobiwebapps.webapps.view.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by MYD on 11/20/17.
 *
 */

@ActivityScope
@Subcomponent(modules = {MainActivityModule.class,
        FragmentBuilderModule.class,
        AccelerometerPresenterModule.class,
        LocationPresenterModule.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
