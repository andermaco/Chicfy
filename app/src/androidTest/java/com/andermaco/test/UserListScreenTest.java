package com.andermaco.test;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.andermaco.test.common.constants.Constants;
import com.andermaco.test.ui.userList.UserListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

//import static android.support.test.espresso.action.ViewActions.click;


/**
 * Created by andermaco@gmail.com on 27/12/17.
 */

@RunWith(AndroidJUnit4.class)
public class UserListScreenTest {

    @Rule
    public ActivityTestRule<UserListActivity> userListActivityActivityTestRule =
            new ActivityTestRule<UserListActivity>(UserListActivity.class);

    /**
     * Test click on floating action button to load more users
     * @throws Exception
     */
    @Test
    public void clickOnAddUsersFAB_loadUsers() throws Exception {
        int pre_items = userListActivityActivityTestRule.getActivity().getAdapter().getUserList().size();
        onView(withId(R.id.user_list_add_users_fab)).perform(click());
        int post_items =  userListActivityActivityTestRule.getActivity().getAdapter().getUserList().size();
        assertEquals(post_items, (pre_items + Constants.USER_API_NUMBER_OF_ENTRIES));

    }

    /**
     * Test click on user image to call new activity with the user's data
     * @throws Exception
     */
    @Test
    public void clickOnUserListItem_showSpecificUser() throws Exception {
        onView(withId(R.id.user_list_rv))
            .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                    CustomViewAction.clickChildViewWithId(R.id.activity_user_list_item_user_image)));
    }

    /**
     * Test user deletion clicking on a recycler view item delete icon
     * @throws Exception
     */
    @Test
    public void clickOnUserListItem_deleteSpecificUser() throws Exception {
        int pre_items = userListActivityActivityTestRule.getActivity().getAdapter().getUserList().size();
        onView(withId(R.id.user_list_rv))
            .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                    CustomViewAction.clickChildViewWithId(R.id.activity_user_list_item_delete)));
        int post_items =  userListActivityActivityTestRule.getActivity().getAdapter().getUserList().size();
        assertEquals(post_items, (pre_items - 1));
    }

    /**
     * Test recycler view scroll to last position
     * @throws Exception
     */
    @Test
    public void scrollToLastPosition()  throws Exception {
        onView(withId(R.id.user_list_rv))
                .perform(RecyclerViewActions.scrollToPosition(userListActivityActivityTestRule
                        .getActivity().getAdapter().getItemCount() - 1));
    }

    /**
     * Test recycler view scroll to first position
     * @throws Exception
     */
    @Test
    public void scrollToFirstPosition()  throws Exception {
        onView(withId(R.id.user_list_rv))
                .perform(RecyclerViewActions.scrollToPosition(0));
    }

    /**
     * Test clicking on recycler view item does not do nothing
     * @throws Exception
     */
    @Test
    public void clickOnRecyclerViewItem_doNothing() throws Exception {
        onView(withId(R.id.user_list_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

}
