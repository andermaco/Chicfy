package com.andermaco.test.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andermaco.test.R;
import com.andermaco.test.common.ResourceManager;
import com.andermaco.test.model.User;
import com.andermaco.test.ui.userList.UserListPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.andermaco.test.common.constants.Constants.VISIBLE_NUMBER_OF_ITEMS;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * Adapter to manage recycler item visualization.
 */
public class UserPaginationAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private UserListPresenter mPresenter;
    private ResourceManager mResourceManager;
    private Context mContext;
    private List<User> mUserList = new ArrayList<>();

    public UserPaginationAdapter(UserListPresenter mPresenter, ResourceManager mResourceManager,
                                 Context context) {
        this.mPresenter = mPresenter;
        this.mResourceManager = mResourceManager;
        this.mContext = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
            .from(parent.getContext()).inflate(R.layout.activity_user_list_item, parent, false);
        // Setting number of recycler view items to be shown
        itemView.getLayoutParams().height = parent.getHeight() / VISIBLE_NUMBER_OF_ITEMS;
        itemView.requestLayout();
        return new UserViewHolder(itemView, mContext);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = mUserList.get(position);
        showUser(holder, user, position);
    }

    @Override
    public int getItemCount() {
        if (mUserList == null) return 0;
        return mUserList.size();
    }

    public List<User> getUserList() {
        return mUserList;
    }

    public void addItems(List<User> userList) {
        mUserList.addAll(userList);
    }

    private void showUser(UserViewHolder holder, User user, int position) {
        MultiTransformation multi = new MultiTransformation(
            new RoundedCornersTransformation(4, 0,
                    RoundedCornersTransformation.CornerType.ALL));
        Glide.with(holder.getUserImage())
                .load(user.getPicture().getMedium())
//                .thumbnail(0.5f)
                .apply(bitmapTransform(multi))
                .into(holder.getUserImage());
        holder.getUserImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.openUser(user);
            }
        });
        holder.getLastName().setText(user.getName().getLast());
        holder.getFirstName().setText(", ".concat(user.getName().getFirst()));
        holder.getPhone().setText(user.getPhone());
        holder.getEmail().setText(user.getEmail());
        holder.getDeleteUser().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.deleteUser(user, position);
            }
        });
    }
}