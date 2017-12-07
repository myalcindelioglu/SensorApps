package com.myd.sensorapps.dagger.modules.fragments;

import com.myd.sensorapps.contract.AppContract;
import com.myd.sensorapps.data.source.repository.AppRepo;
import com.myd.sensorapps.coreapps.model.Battery;
import com.myd.sensorapps.coreapps.presenter.Presenter;
import com.myd.sensorapps.coreapps.view.BatteryFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 11/22/17.
 *
 */

@Module
public class BatteryFragmentModule {

    @Provides
    AppContract.Presenter<Battery> provideBatteryPresenter(AppRepo<Battery> repository,
                                                           BatteryFragment view) {
        return new Presenter<>(repository, view);
    }

    @Provides
    AppContract.View<Battery> provideAccelerometerView(
            BatteryFragment view) {
        return view;
    }

}
