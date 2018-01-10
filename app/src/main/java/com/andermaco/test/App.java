package com.andermaco.test;

import android.app.Application;
import android.content.Context;

import com.andermaco.test.di.components.ApplicationComponent;
import com.andermaco.test.di.components.DaggerApplicationComponent;
import com.andermaco.test.di.modules.ApplicationModule;
import com.andermaco.test.di.modules.SystemModule;
import com.andermaco.test.networking.NetworkModule;

import java.io.File;

/**
 * Created by andermaco@gmail.com on 7/09/17.
 *
 * Application subclass implemented for the application.
 */
public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize application
        initApp();
    }

    /**
     * Initialize application
     */
    private void initApp() {
        setupInjector();
    }

    /**
     * Setup dagger 2 injector
     */
    private void setupInjector() {

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .systemModule(new SystemModule())
                .networkModule(new NetworkModule(new File(getCacheDir(), "responses")))
                .build();
        applicationComponent.inject(this);
    }

    /**
     * Get an Application instance
     * @param context Current context
     * @return Application
     */
    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }

    /**
     * @return Dagger2's application component, so dependencies can be handled.
     */
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
