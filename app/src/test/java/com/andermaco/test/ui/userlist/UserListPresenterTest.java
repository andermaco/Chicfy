package com.andermaco.test.ui.userlist;

import com.andermaco.test.common.AndroidResourceManager;
import com.andermaco.test.common.AndroidRouter;
import com.andermaco.test.common.constants.Constants;
import com.andermaco.test.model.UserListResponse;
import com.andermaco.test.networking.NetworkModule;
import com.andermaco.test.networking.Service;
import com.andermaco.test.ui.userList.UserListActivity;
import com.andermaco.test.ui.userList.UserListPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by andermaco@gmail.com on 28/12/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserListPresenterTest {

    private final static String TAG = UserListPresenter.class.getSimpleName();

    @InjectMocks
    UserListActivity userListActivity;

    @InjectMocks
    AndroidRouter router;

    @InjectMocks
    AndroidResourceManager resourceManager;

    @InjectMocks
    NetworkModule module;

    @InjectMocks
    Service service;

    private static final int PAGE = 1;
    private static final int NUMBER_OF_ITEMS_TO_GET = 10;

    @Test
    public void shouldLoadPresenter() {

        // TODO: Mock the view
        UserListPresenter userListPresenter = new UserListPresenter(userListActivity, router,
                resourceManager, service);

        // TODO: Mock the model
        UserListResponse userListResponse = module.providesNetworkService(module.provideCall())
                .getUserListPaginated(PAGE, NUMBER_OF_ITEMS_TO_GET, TAG,
                        Constants.USER_API_EXCLUDE_FIELDS).blockingGet();
        assertEquals(userListResponse.getResults().size(), NUMBER_OF_ITEMS_TO_GET );
    }
}
