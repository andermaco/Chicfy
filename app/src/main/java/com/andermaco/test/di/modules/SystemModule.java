package com.andermaco.test.di.modules;

import android.content.Context;
import com.andermaco.test.common.AndroidResourceManager;
import com.andermaco.test.common.ResourceManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by andermaco@gmail.com on 31/07/17.
 *
 * Define System providers
 */

@Module
public class SystemModule {

    @Provides
    public ResourceManager provideResourceManager(Context context) {
        return new AndroidResourceManager(context);
    }

}
