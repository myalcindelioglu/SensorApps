package com.myd.cobiwebapps.dagger.components.fragments;

import com.myd.cobiwebapps.dagger.modules.fragments.VibrateFragmentModule;
import com.myd.cobiwebapps.webapps.view.VibrateFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by MYD on 11/22/17.
 *
 */


@Subcomponent(modules = {VibrateFragmentModule.class})
public interface VibrateFragmentComponent extends AndroidInjector<VibrateFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<VibrateFragment>{}
}
