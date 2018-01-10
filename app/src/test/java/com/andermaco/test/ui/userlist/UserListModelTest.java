package com.andermaco.test.ui.userlist;

import com.andermaco.test.common.constants.Constants;
import com.andermaco.test.model.UserListResponse;
import com.andermaco.test.networking.NetworkModule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by andermaco@gmail.com on 27/12/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserListModelTest {
    private final static String TAG = UserListModelTest.class.getSimpleName();

    private static final int PAGE = 1;
    private static final int NUMBER_OF_ITEMS_TO_GET = 10;

    @InjectMocks
    NetworkModule module;

    /**
     * Test url data retrieval as a whole (all data) limited by the number of items defined
     * by constant NUMBER_OF_ITEMS_TO_GET
     */
    @Test
    public void shouldLoadUsers_wholeData() {
        UserListResponse userListResponse = module.providesNetworkService(module.provideCall())
                .getUserList(NUMBER_OF_ITEMS_TO_GET).blockingGet();
        assertEquals(userListResponse.getResults().size(), NUMBER_OF_ITEMS_TO_GET );
    }

    /**
     * Test url specific data retrieval (not all data) limited by the number of items defined
     * by constant NUMBER_OF_ITEMS_TO_GET and by the constant PAGE requested
     */
    @Test
    public void shouldLoadUsers_specificData() {
        UserListResponse userListResponse = module.providesNetworkService(module.provideCall())
                .getUserListPaginated(PAGE, NUMBER_OF_ITEMS_TO_GET, TAG,
                        Constants.USER_API_EXCLUDE_FIELDS).blockingGet();
        assertEquals(userListResponse.getResults().size(), NUMBER_OF_ITEMS_TO_GET );
    }
}
