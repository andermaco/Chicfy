package com.andermaco.test.di.components;

import com.andermaco.test.di.DiComponent;
import com.andermaco.test.di.modules.PresentationModule;
import com.andermaco.test.di.modules.UserListModule;
import com.andermaco.test.di.scopes.PerActivity;
import com.andermaco.test.ui.userList.UserListActivity;

import dagger.Component;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * Define the collection of modules that will be used to construct the UserListActivity
 * dependency graph
 */
@PerActivity
@Component(
        modules = {
                PresentationModule.class,
                UserListModule.class
        },
        dependencies = {
                ApplicationComponent.class
        }
)
public interface UserListComponent extends DiComponent {
    void inject(UserListActivity userListActivity);
}
