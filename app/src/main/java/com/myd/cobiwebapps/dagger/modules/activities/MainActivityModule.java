package com.myd.cobiwebapps.dagger.modules.activities;

import com.myd.cobiwebapps.dagger.components.fragments.AccelerometerFragmentComponent;
import com.myd.cobiwebapps.dagger.components.fragments.BatteryFragmentComponent;
import com.myd.cobiwebapps.dagger.components.fragments.LocationFragmentComponent;
import com.myd.cobiwebapps.dagger.components.fragments.VibrateFragmentComponent;

import dagger.Module;

/**
 * Created by MYD on 11/20/17.
 *
 */

@Module(subcomponents = {
        AccelerometerFragmentComponent.class,
        LocationFragmentComponent.class,
        BatteryFragmentComponent.class,
        VibrateFragmentComponent.class})
public class MainActivityModule {

}
