package com.myd.sensorapps.dagger.modules.activities;

import com.myd.sensorapps.dagger.components.fragments.AccelerometerFragmentComponent;
import com.myd.sensorapps.dagger.components.fragments.BatteryFragmentComponent;
import com.myd.sensorapps.dagger.components.fragments.LocationFragmentComponent;

import dagger.Module;

/**
 * Created by MYD on 11/20/17.
 *
 */

@Module(subcomponents = {
        AccelerometerFragmentComponent.class,
        LocationFragmentComponent.class,
        BatteryFragmentComponent.class})
public class MainActivityModule {

}
