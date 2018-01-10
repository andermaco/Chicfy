package com.andermaco.test.di.components;

import android.content.Context;

import com.andermaco.test.App;
import com.andermaco.test.common.ResourceManager;
import com.andermaco.test.di.modules.ApplicationModule;
import com.andermaco.test.di.modules.SystemModule;
import com.andermaco.test.di.scopes.PerApplication;
import com.andermaco.test.networking.NetworkModule;
import com.andermaco.test.networking.Service;

import dagger.Component;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * Component that defines application dependencies for the whole application
 */
@PerApplication
@Component(modules = {
        ApplicationModule.class,
        SystemModule.class,
        NetworkModule.class
})
public interface ApplicationComponent {

    void inject(App application);
    ResourceManager getResourceManager();
    Service getService();
    Context getContext();

}
