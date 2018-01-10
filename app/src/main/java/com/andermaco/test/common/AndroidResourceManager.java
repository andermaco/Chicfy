package com.andermaco.test.common;

import android.content.Context;

import com.andermaco.test.R;

/**
 * Created by andermaco@gmail.com on 31/07/17.
 *
 * Manager that manages all string resource requirements. It will be populated to those objects that
 * requires string resolutions.
 */
public class AndroidResourceManager implements ResourceManager {

    private final Context context;

    public AndroidResourceManager(Context context) {
        this.context = context;
    }

    @Override
    public String getUserListTitle() {
        return context.getString(R.string.user_list_title_activity);
    }

    @Override
    public String getUserTitle() {
        return context.getString(R.string.user_title_activity);
    }

}
