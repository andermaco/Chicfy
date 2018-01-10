package com.andermaco.test.ui.userList;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.andermaco.test.R;
import com.andermaco.test.common.ResourceManager;
import com.andermaco.test.common.constants.Constants;
import com.andermaco.test.di.components.ApplicationComponent;
import com.andermaco.test.di.components.DaggerUserListComponent;
import com.andermaco.test.di.components.UserListComponent;
import com.andermaco.test.di.modules.PresentationModule;
import com.andermaco.test.di.modules.UserListModule;
import com.andermaco.test.ui.adapter.UserPaginationAdapter;
import com.andermaco.test.ui.base.BaseActivity;
import com.andermaco.test.ui.base.BasePresenter;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * UserList view
 */
public class UserListActivity extends BaseActivity implements UserListView {

    private static final String TAG = UserListActivity.class.getSimpleName();

    @BindView(R.id.users_container)
    protected ViewGroup mOffersContainer;

    @BindView(R.id.user_list_rv)
    RecyclerView mRecyclerView;

    @OnClick(R.id.user_list_add_users_fab)
    protected void addUsers() {
        presenter.addUsers();
    }

    @Inject
    ResourceManager resourceManager;

    @Inject
    UserListPresenter presenter;

    private UserPaginationAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void setupComponent(ApplicationComponent appComponent) {
        component = DaggerUserListComponent.builder()
                .applicationComponent(appComponent)
                .presentationModule(new PresentationModule(this))
                .userListModule(new UserListModule(this))
                .build();
        ((UserListComponent) component).inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void initViews() {
        mAdapter = new UserPaginationAdapter(presenter, resourceManager, getContext());
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public ViewGroup getContainer() {
        return mOffersContainer;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public UserPaginationAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    @Override
    public void notifyDataSetChanged() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void notifyPageInserted(int pageNumber) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyItemRangeChanged(pageNumber
                        * Constants.USER_API_NUMBER_OF_ENTRIES, Constants.USER_API_NUMBER_OF_ENTRIES);
            }
        });
    }


}
