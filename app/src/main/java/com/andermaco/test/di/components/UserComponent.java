package com.andermaco.test.di.components;

import com.andermaco.test.di.DiComponent;
import com.andermaco.test.di.modules.PresentationModule;
import com.andermaco.test.di.modules.UserModule;
import com.andermaco.test.di.scopes.PerActivity;
import com.andermaco.test.ui.user.UserActivity;

import dagger.Component;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * Define the collection of modules that will be used to construct the UserActivity
 * dependency graph
 */
@PerActivity
@Component(
        modules = {
                PresentationModule.class,
                UserModule.class
        },
        dependencies = {
                ApplicationComponent.class
        }
)
public interface UserComponent extends DiComponent{
    void inject(UserActivity userActivity);
}
