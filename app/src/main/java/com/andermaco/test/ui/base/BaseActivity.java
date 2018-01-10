package com.andermaco.test.ui.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.andermaco.test.App;
import com.andermaco.test.R;
import com.andermaco.test.di.DiComponent;
import com.andermaco.test.di.components.ApplicationComponent;

import butterknife.ButterKnife;

/**
 * Created by andermaco@gmail.com on 26/07/17.
 *
 * Base methods for being inherited for activities.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private boolean isInjected = false;
    private BasePresenter presenter;
    protected DiComponent component;
    private Dialog spinnerProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResourceId());

        ButterKnife.bind(this);

        createBlockDialog();

        //Dependency injection
        injectDependencies();

        initPresenter();

        setDataToPresenter(getIntent());

        initViews();
    }

    /**
     * Set up dagger 2 component
     *
     * @param appComponent Application component
     */
    protected abstract void setupComponent(ApplicationComponent appComponent);

    private void injectDependencies() {
        if (!isInjected) {
            setupComponent(App.getApp(this).getApplicationComponent());
            isInjected = true;
        }
    }

    /**
     * @return The presenter attached to the activity. This must extends from {@link BasePresenter}
     */
    protected abstract BasePresenter getPresenter();

    /**
     * Initialize all views
     */
    protected abstract void initViews();

    /**
     * Specify the layout of the fragment to be inflated in the {@link BaseActivity#onCreate(Bundle)}
     */
    protected abstract int getLayoutResourceId();

    /**
     * Destroy all objects
     */
    protected abstract void destroy();

    private void initPresenter() {
        if (presenter == null) {
            presenter = this.getPresenter();
        }
    }

    protected void setDataToPresenter(Intent intent) {
        // Fill with code on each presenter that must need it
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().onDestroy();
        }
    }

    /**
     * Creates and shows an spinner dialog used on IO actions
     */
    @Override
    public void blockView() {
        spinnerProgressDialog.show();
        spinnerProgressDialog.setContentView(R.layout.progress_dialog);
    }

    /**
     * Hide (dismiss) and spinner dialog used on IO actions
     */
    @Override
    public void unBlockView() {
        spinnerProgressDialog.dismiss();
    }

    @Override
    public void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
    presenter.onBackPressed();
}

    private void createBlockDialog() {
        spinnerProgressDialog = new ProgressDialog(this);
        spinnerProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        spinnerProgressDialog.setCanceledOnTouchOutside(false);
        spinnerProgressDialog.setCancelable(false);
    }

}
