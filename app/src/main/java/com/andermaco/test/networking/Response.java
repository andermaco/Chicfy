package com.andermaco.test.networking;

import com.google.gson.annotations.SerializedName;


/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * Defines Response model.
 */
public class Response {
    @SerializedName("status")
    public String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @SuppressWarnings({"unused", "used by Retrofit"})
    public Response() {
    }

    public Response(String status) {
        this.status = status;
    }
}
