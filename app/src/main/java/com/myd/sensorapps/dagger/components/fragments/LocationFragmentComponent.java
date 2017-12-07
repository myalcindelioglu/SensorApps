package com.myd.sensorapps.dagger.components.fragments;

import com.myd.sensorapps.dagger.modules.fragments.LocationFragmentModule;
import com.myd.sensorapps.coreapps.view.LocationFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by MYD on 11/22/17.
 *
 */


@Subcomponent(modules = {LocationFragmentModule.class})
public interface LocationFragmentComponent extends AndroidInjector<LocationFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LocationFragment>{}
}
