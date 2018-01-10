package com.andermaco.test.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andermaco.test.R;
import com.andermaco.test.common.constants.Constants;
import com.andermaco.test.di.components.ApplicationComponent;
import com.andermaco.test.di.components.DaggerUserComponent;
import com.andermaco.test.di.components.UserComponent;
import com.andermaco.test.di.modules.PresentationModule;
import com.andermaco.test.di.modules.UserModule;
import com.andermaco.test.model.User;
import com.andermaco.test.ui.base.BaseActivity;
import com.andermaco.test.ui.base.BasePresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * User view.
 */
public class UserActivity extends BaseActivity implements UserView {

    @Inject
    UserPresenter presenter;

    @BindView(R.id.users_container)
    ViewGroup container;

    @BindView(R.id.user_image_view)
    ImageView imageView;

    @BindView(R.id.user_surname)
    TextView surnameTextView;

    @BindView(R.id.user_name)
    TextView nameTextView;

    @BindView(R.id.user_gender)
    TextView genderTextView;

    @BindView(R.id.user_location)
    TextView locationTextView;

    @BindView(R.id.user_date)
    TextView dateTextView;

    @BindView(R.id.user_email)
    TextView emailTextView;

    @Override
    public ViewGroup getContainer() {
        return container;
    }

    @Override
    public Context getContext() {
        return getContext();
    }

    @Override
    protected void setupComponent(ApplicationComponent appComponent) {
        component = DaggerUserComponent.builder()
                .applicationComponent(appComponent)
                .presentationModule(new PresentationModule(this))
                .userModule(new UserModule(this))
                .build();
        ((UserComponent) component).inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
            presenter.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_user;
    }

    @Override
    protected void destroy() {

    }

    @Override
    protected void setDataToPresenter(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            User user = (User) bundle.get(Constants.BUNDLE_CONST_USER);
            presenter.setUser(user);
        }
    }


    // Getter and setters

    public void setPresenter(UserPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public TextView getSurnameTextView() {
        return surnameTextView;
    }

    public void setSurnameTextView(TextView surnameTextView) {
        this.surnameTextView = surnameTextView;
    }

    @Override
    public TextView getNameTextView() {
        return nameTextView;
    }

    public void setNameTextView(TextView nameTextView) {
        this.nameTextView = nameTextView;
    }

    @Override
    public TextView getGenderTextView() {
        return genderTextView;
    }

    public void setGenderTextView(TextView genderTextView) {
        this.genderTextView = genderTextView;
    }

    @Override
    public TextView getLocationTextView() {
        return locationTextView;
    }

    public void setLocationTextView(TextView locationTextView) {
        this.locationTextView = locationTextView;
    }

    @Override
    public TextView getDateTextView() {
        return dateTextView;
    }

    public void setDateTextView(TextView dateTextView) {
        this.dateTextView = dateTextView;
    }

    @Override
    public TextView getEmailTextView() {
        return emailTextView;
    }

    public void setEmailTextView(TextView emailTextView) {
        this.emailTextView = emailTextView;
    }
}
