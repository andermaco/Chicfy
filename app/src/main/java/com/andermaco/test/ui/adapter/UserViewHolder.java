package com.andermaco.test.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andermaco.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * Custom recyclerview Viewholder to represent recyclerview item.
 */
public class UserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.activity_user_list_item_container)
    ViewGroup container;

    @BindView(R.id.activity_user_list_item_user_image)
    ImageView userImage;

    @BindView(R.id.activity_user_list_item_last_name)
    TextView lastName;

    @BindView(R.id.activity_user_list_item_first_name)
    TextView firstName;

    @BindView(R.id.activity_user_list_item_phone)
    TextView phone;

    @BindView(R.id.activity_user_list_item_email)
    TextView email;

    @BindView(R.id.activity_user_list_item_delete)
    ImageView deleteUser;

    private View mItemView;
    private Context mContext;

    public UserViewHolder(View itemView, Context context) {
        super(itemView);
        this.mItemView = itemView;
        this.mContext = context;
        ButterKnife.bind(this, itemView);
    }

    public ViewGroup getContainer() {
        return container;
    }

    public void setContainer(ViewGroup container) {
        this.container = container;
    }

    public ImageView getUserImage() {
        return userImage;
    }

    public void setUserImage(ImageView userImage) {
        this.userImage = userImage;
    }

    public TextView getLastName() {
        return lastName;
    }

    public void setLastName(TextView lastName) {
        this.lastName = lastName;
    }

    public TextView getFirstName() {
        return firstName;
    }

    public void setFirstName(TextView firstName) {
        this.firstName = firstName;
    }

    public TextView getPhone() {
        return phone;
    }

    public void setPhone(TextView phone) {
        this.phone = phone;
    }

    public ImageView getDeleteUser() {
        return deleteUser;
    }

    public TextView getEmail() {
        return email;
    }

    public void setEmail(TextView email) {
        this.email = email;
    }
}
