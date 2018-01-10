package com.andermaco.test.di.modules;

import com.andermaco.test.common.ResourceManager;
import com.andermaco.test.common.Router;
import com.andermaco.test.di.scopes.PerActivity;
import com.andermaco.test.networking.Service;
import com.andermaco.test.ui.user.UserActivity;
import com.andermaco.test.ui.user.UserPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * Define User providers
 */
@Module
public class UserModule {
    private UserActivity mActivity;

    public UserModule(UserActivity mActivity) {
        this.mActivity = mActivity;
    }

    @PerActivity
    @Provides
    public UserActivity provideActivity() {
        return this.mActivity;
    }

    @PerActivity
    @Provides
    public UserPresenter providePresenter(UserActivity view, Router router,
                                          ResourceManager resourceManager, Service service) {
        return new UserPresenter(view, router, resourceManager, service);
    }

}
