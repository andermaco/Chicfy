package com.andermaco.test.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * User model
 */
public class User implements Parcelable {

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("name")
    @Expose
    private Name name;

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("picture")
    @Expose
    private Picture picture;

    @SerializedName("registered")
    @Expose
    private String date;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gender);
        dest.writeParcelable(this.name, flags);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeParcelable(this.picture, flags);
        dest.writeString(this.date);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.gender = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.email = in.readString();
        this.phone = in.readString();
        this.picture = in.readParcelable(Picture.class.getClassLoader());
        this.date = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
