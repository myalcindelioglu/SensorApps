package com.myd.cobiwebapps.dagger.components.app;

import android.app.Application;

import com.myd.cobiwebapps.application.App;
import com.myd.cobiwebapps.dagger.modules.activities.ActivityBuilderModule;
import com.myd.cobiwebapps.dagger.modules.app.AppModule;
import com.myd.cobiwebapps.dagger.modules.app.LocalWebAppSourceModule;
import com.myd.cobiwebapps.dagger.modules.app.SensorWebAppSourceModule;
import com.myd.cobiwebapps.dagger.modules.app.WebAppRepoModule;

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
        LocalWebAppSourceModule.class,
        SensorWebAppSourceModule.class,
        WebAppRepoModule.class
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
