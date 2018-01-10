package com.andermaco.test.networking;

import com.andermaco.test.model.UserListResponse;

import io.reactivex.Single;

import static com.andermaco.test.common.constants.Constants.USER_API_EXCLUDE_FIELDS;
import static com.andermaco.test.common.constants.Constants.USER_API_NUMBER_OF_ENTRIES;


/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * Define Service manager methods.
 */
public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Single<UserListResponse> getUserList(int page) {
        return networkService.getUserList(USER_API_NUMBER_OF_ENTRIES);
    }

     public Single<UserListResponse> getUserListPaginated(int page) {
         return networkService.getUserListPaginated(page, USER_API_NUMBER_OF_ENTRIES,
                 NetworkService.TAG, USER_API_EXCLUDE_FIELDS);
     }

}
