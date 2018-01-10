package com.andermaco.test.di.modules;

import com.andermaco.test.common.ResourceManager;
import com.andermaco.test.common.Router;
import com.andermaco.test.di.scopes.PerActivity;
import com.andermaco.test.networking.Service;
import com.andermaco.test.ui.userList.UserListActivity;
import com.andermaco.test.ui.userList.UserListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 *  Define UserList providers
 */
@Module
public class UserListModule {
    private UserListActivity mActivity;

    public UserListModule(UserListActivity mActivity) {
        this.mActivity = mActivity;
    }

    @PerActivity
    @Provides
    public UserListActivity provideActivity() {
        return this.mActivity;
    }

    @PerActivity
    @Provides
    public UserListPresenter providePresenter(UserListActivity view, Router router,
                                              ResourceManager resourceManager, Service service) {
        return new UserListPresenter(view, router, resourceManager, service);
    }
}
