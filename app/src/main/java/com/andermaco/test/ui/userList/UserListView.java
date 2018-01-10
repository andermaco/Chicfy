package com.andermaco.test.ui.userList;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andermaco.test.ui.adapter.UserPaginationAdapter;
import com.andermaco.test.ui.base.BaseView;

/**
 * Created by andermaco@gmail.com on 15/09/17.
 *
 * Methods to be implemented by activity view.
 */

public interface UserListView extends BaseView {
    void setTitle(int resourceId);

    UserPaginationAdapter getAdapter();

    RecyclerView getRecyclerView();

    LinearLayoutManager getLayoutManager();

    void notifyDataSetChanged();

    void notifyPageInserted(int pageNumber);
}
