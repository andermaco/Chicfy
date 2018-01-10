package com.andermaco.test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * Defines UserList response model
 */

public class UserListResponse {

    @SerializedName("results")
    @Expose
    public List<User> results;

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

}
