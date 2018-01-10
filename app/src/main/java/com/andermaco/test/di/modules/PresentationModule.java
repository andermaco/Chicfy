package com.andermaco.test.di.modules;

import android.app.Activity;
import com.andermaco.test.common.AndroidRouter;
import com.andermaco.test.common.Router;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andermaco@gmail.com on 31/07/17.
 *
 * Defines Presentation layer providers
 */

@Module
public class PresentationModule {

    Activity activity;

    public PresentationModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Router provideRouter() {
        return new AndroidRouter(activity);
    }

}
