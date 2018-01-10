package com.andermaco.test.ui.base;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by andermaco@gmail.com on 27/07/17.
 *
 * MVP View that must be extended by all MVP views
 */
public interface BaseView {

    void blockView();

    void unBlockView();

    ViewGroup getContainer();

    Context getContext();

    void setTitle(String title);

}
