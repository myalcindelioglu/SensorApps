package com.myd.cobiwebapps.dagger.modules.fragments;

import android.support.v4.app.Fragment;

import com.myd.cobiwebapps.dagger.components.fragments.AccelerometerFragmentComponent;
import com.myd.cobiwebapps.dagger.components.fragments.LocationFragmentComponent;
import com.myd.cobiwebapps.webapps.view.AccelerometerFragment;
import com.myd.cobiwebapps.webapps.view.LocationFragment;

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
}
