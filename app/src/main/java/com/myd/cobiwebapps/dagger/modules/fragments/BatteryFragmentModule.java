package com.myd.cobiwebapps.dagger.modules.fragments;

import com.myd.cobiwebapps.contract.WebAppContract;
import com.myd.cobiwebapps.data.source.repository.WebAppRepo;
import com.myd.cobiwebapps.webapps.model.Battery;
import com.myd.cobiwebapps.webapps.presenter.Presenter;
import com.myd.cobiwebapps.webapps.view.BatteryFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 11/22/17.
 *
 */

@Module
public class BatteryFragmentModule {

    @Provides
    WebAppContract.Presenter<Battery> provideBatteryPresenter(WebAppRepo<Battery> repository,
                                                              BatteryFragment view) {
        return new Presenter<>(repository, view);
    }

    @Provides
    WebAppContract.View<Battery> provideAccelerometerView(
            BatteryFragment view) {
        return view;
    }

}
