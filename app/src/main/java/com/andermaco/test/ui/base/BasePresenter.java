package com.andermaco.test.ui.base;

/**
 * Created by andermaco@gmail.com on 27/07/17.
 *
 * Define base methods to be implemented by inherited presenters
 * @param <T>
 */
public interface BasePresenter<T extends BaseView> {
    // This methods will be executed on activity/fragment's onStart method

    void onStart();

    void onResume();

    void onPause();


    // This method will be executed on activity/fragment's onStop method

    void onStop();


    // This methods will be executed on activity/fragment's onDestroy method

    void onDestroy();

    void onBackPressed();
}
