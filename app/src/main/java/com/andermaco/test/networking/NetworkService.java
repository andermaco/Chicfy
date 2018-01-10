package com.andermaco.test.networking;


import com.andermaco.test.model.UserListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * Defines retrofit methods.
 */
public interface NetworkService {

    public final static String TAG = NetworkService.class.getSimpleName();

    @GET(".")
    Single<UserListResponse> getUserList(@Query("results") int results);

    @GET(".")
    Single<UserListResponse> getUserListPaginated(@Query("page") int page,
                                                  @Query("results") int results,
                                                  @Query("seed") String seed,
                                                  @Query("exc") String esc);
}
