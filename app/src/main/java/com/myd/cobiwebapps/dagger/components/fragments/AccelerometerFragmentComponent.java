package com.myd.cobiwebapps.dagger.components.fragments;

import com.myd.cobiwebapps.dagger.modules.fragments.AccelerometerFragmentModule;
import com.myd.cobiwebapps.webapps.view.AccelerometerFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by MYD on 11/22/17.
 *
 */


@Subcomponent(modules = {AccelerometerFragmentModule.class})
public interface AccelerometerFragmentComponent extends AndroidInjector<AccelerometerFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<AccelerometerFragment>{}
}
