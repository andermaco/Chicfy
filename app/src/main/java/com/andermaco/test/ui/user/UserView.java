package com.andermaco.test.ui.user;

import android.widget.ImageView;
import android.widget.TextView;

import com.andermaco.test.ui.base.BaseView;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * Methods to be implemented activity view.
 */

public interface UserView extends BaseView {
    ImageView getImageView();

    TextView getSurnameTextView();

    TextView getNameTextView();

    TextView getGenderTextView();

    TextView getLocationTextView();

    TextView getDateTextView();

    TextView getEmailTextView();
}
