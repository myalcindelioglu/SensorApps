package com.myd.sensorapps.dagger.components.app;

import android.app.Application;

import com.myd.sensorapps.application.App;
import com.myd.sensorapps.dagger.modules.activities.ActivityBuilderModule;
import com.myd.sensorapps.dagger.modules.app.AppModule;
import com.myd.sensorapps.dagger.modules.app.LocalAppSourceModule;
import com.myd.sensorapps.dagger.modules.app.SensorAppSourceModule;
import com.myd.sensorapps.dagger.modules.app.AppRepoModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by MYD on 11/20/17.
 *
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        AppModule.class,
        LocalAppSourceModule.class,
        SensorAppSourceModule.class,
        AppRepoModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
