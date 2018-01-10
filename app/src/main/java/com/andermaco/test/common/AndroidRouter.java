package com.andermaco.test.common;

import android.app.Activity;
import android.content.Intent;

import com.andermaco.test.model.User;
import com.andermaco.test.ui.user.UserActivity;

import static com.andermaco.test.common.constants.Constants.BUNDLE_CONST_USER;

/**
 * Created by andermaco@gmail.com on 31/07/17.
 *
 * Manager in charge of managing route to other objects (activities, fragments, services...)
 */
public class AndroidRouter implements Router {

    private final static String TAG = AndroidRouter.class.getSimpleName();

    private final Activity activity;

    public AndroidRouter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void goBack() {
        activity.finish();
    }


    @Override
    public void openUser(User user) {
        Intent intent = new Intent(this.activity, UserActivity.class);
        intent.putExtra(BUNDLE_CONST_USER, user);
        activity.startActivity(intent);
    }

}
