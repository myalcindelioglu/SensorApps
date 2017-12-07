package com.myd.sensorapps.dagger.components.fragments;

import com.myd.sensorapps.dagger.modules.fragments.BatteryFragmentModule;
import com.myd.sensorapps.coreapps.view.BatteryFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by MYD on 11/22/17.
 *
 */


@Subcomponent(modules = {BatteryFragmentModule.class})
public interface BatteryFragmentComponent extends AndroidInjector<BatteryFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BatteryFragment>{}
}
