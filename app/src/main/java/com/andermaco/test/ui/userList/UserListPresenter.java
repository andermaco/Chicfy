package com.andermaco.test.ui.userList;

import android.util.Log;

import com.andermaco.test.common.ResourceManager;
import com.andermaco.test.common.Router;
import com.andermaco.test.model.User;
import com.andermaco.test.networking.Service;
import com.andermaco.test.ui.base.BaseActivity;
import com.andermaco.test.ui.base.BasePresenter;
import com.andermaco.test.ui.base.BaseViewPresenter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by andermaco@gmail.com on 15/09/17.
 *
 * Presenter that loads list of users verifying no duplicates and no blacklisted
 * users (deleted ones)
 */
public class UserListPresenter extends BaseViewPresenter implements BasePresenter {

    private static final String TAG = UserListPresenter.class.getSimpleName();
    private UserListView mView;
    private Service mService;
    private Disposable disposable;
    private boolean mUserRemoved = false;   // User to flag whether a user has been removed
    private int pageNumber = 1;             // Used to store current pagination page
    private List<User> mRemovedUserList = new ArrayList<>();

    public UserListPresenter(UserListView view, Router router, ResourceManager resourceManager,
            Service service) {
        super(view, router, resourceManager);
        this.mView = view;
        this.mService = service;
    }

    @Override
    public void onStart() {
        mView.setTitle(resourceManager.getUserListTitle());
    }

    @Override
    public void onResume() {
        if (mView.getAdapter().getUserList().size() == 0 && !mUserRemoved) {
            // Just load load data in case of first execution, not in case of deleting all users
            // and resuming app.
            getData();
        }
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

    public void getData() {
        mService.getUserListPaginated(pageNumber)
            .doOnSubscribe(d -> {
                    disposable = d;
                    mView.blockView();
                })
            .doOnSuccess(userListResponse -> {
                // Remove duplicates
                List<User> noDuplicatedList =
                        new ArrayList<>(new HashSet<User>(userListResponse.getResults()));
                // Remove already deleted users
                noDuplicatedList.removeAll(mRemovedUserList);
                // Update adapter
                mView.getAdapter().addItems(noDuplicatedList);
                mView.notifyPageInserted(pageNumber);
            })
            .doOnError(throwable -> {
                Log.e(TAG, "Error getting user list " + throwable.getMessage());
                throwable.printStackTrace();
            })
            .doFinally(() -> {
                disposable.dispose();
                mView.unBlockView();
            })
//            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe();
    }

    public BaseActivity getView() {
        return (UserListActivity) mView;
    }

    public void openUser(User user) {
        router.openUser(user);
    }

    public void addUsers() {
        pageNumber++;
        getData();
    }

    public void deleteUser(User user, int position) {
        mUserRemoved = true;        // Flag user deletion
        mRemovedUserList.add(user);
        mView.getAdapter().getUserList().remove(user);
        mView.getAdapter().notifyItemRemoved(position);
        mView.getAdapter().notifyItemRangeChanged(position, mView.getAdapter().getUserList().size());
    }

}
