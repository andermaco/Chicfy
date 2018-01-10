package com.andermaco.test.ui.user;

import com.andermaco.test.common.ResourceManager;
import com.andermaco.test.common.Router;
import com.andermaco.test.model.User;
import com.andermaco.test.networking.Service;
import com.andermaco.test.ui.base.BasePresenter;
import com.andermaco.test.ui.base.BaseViewPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * UserActivity presenter following MVP pattern.
 */

public class UserPresenter extends BaseViewPresenter implements BasePresenter {

    private UserView mView;
    private Service mService;
    private User mUser;

    public UserPresenter(UserView view, Router router, ResourceManager resourceManager,
            Service service) {
        super(view, router, resourceManager);
        this.mView = view;
        this.mService = service;
    }

    @Override
    public void onStart() {
        mView.setTitle(resourceManager.getUserTitle());
    }

    @Override
    public void onResume() {
        initViews();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {
        router.goBack();
    }

    private void initViews() {
        MultiTransformation multi = new MultiTransformation(
                new RoundedCornersTransformation(4, 0,
                    RoundedCornersTransformation.CornerType.ALL));
        Glide.with(mView.getImageView())
                .load(mUser.getPicture().getLarge())
                .thumbnail(0.1f)
                .apply(bitmapTransform(multi))
                .into(mView.getImageView());
        mView.getSurnameTextView().setText(mUser.getName().getLast());
        mView.getNameTextView().setText(mUser.getName().getFirst());
        mView.getGenderTextView().setText(mUser.getGender());
        mView.getLocationTextView().setText(mUser.getLocation().getStreet()
                .concat(mUser.getLocation().getCity()
                .concat(mUser.getLocation().getState()
                .concat(mUser.getLocation().getPostcode()))));
        mView.getDateTextView().setText(mUser.getDate());
        mView.getEmailTextView().setText(mUser.getEmail());
    }

    public void setUser(User user) {
        this.mUser = user;
    }
}
