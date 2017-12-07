package com.myd.sensorapps.dagger.modules.fragments;

import android.support.v4.app.Fragment;

import com.myd.sensorapps.dagger.components.fragments.AccelerometerFragmentComponent;
import com.myd.sensorapps.dagger.components.fragments.BatteryFragmentComponent;
import com.myd.sensorapps.dagger.components.fragments.LocationFragmentComponent;
import com.myd.sensorapps.coreapps.view.AccelerometerFragment;
import com.myd.sensorapps.coreapps.view.BatteryFragment;
import com.myd.sensorapps.coreapps.view.LocationFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Created by MYD on 11/22/17.
 *
 */



@Module
public abstract class FragmentBuilderModule {
    @Binds
    @IntoMap
    @FragmentKey(AccelerometerFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindsAccelerometerFragment(AccelerometerFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(LocationFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindsLocationFragment(LocationFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(BatteryFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindsBatteryFragment(BatteryFragmentComponent.Builder builder);
}
