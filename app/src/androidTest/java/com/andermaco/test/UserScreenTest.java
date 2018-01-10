package com.andermaco.test;

import android.content.Intent;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.andermaco.test.common.constants.Constants;
import com.andermaco.test.model.Location;
import com.andermaco.test.model.Name;
import com.andermaco.test.model.Picture;
import com.andermaco.test.model.User;
import com.andermaco.test.ui.user.UserActivity;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

import io.kimo.lib.faker.Faker;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by andermaco@gmail.com on 27/12/17.
 */

@RunWith(AndroidJUnit4.class)
public class UserScreenTest {
    @Rule
    public ActivityTestRule<UserActivity> userActivityActivityTestRule = new ActivityTestRule<UserActivity>(UserActivity.class){
        @Override
        protected Intent getActivityIntent() {
            User user = new User();
            String formatedDateTime = DateTimeFormat.shortDateTime().withLocale(Locale.getDefault())
                .print(new DateTime());
            user.setDate(formatedDateTime);
            user.setEmail(Faker.with(getTargetContext()).Internet.email());
            user.setGender("male");
            Location loc = new Location();
            loc.setCity(Faker.with(getTargetContext()).Lorem.word());
            loc.setPostcode(Faker.with(getTargetContext()).Lorem.word());
            loc.setState(Faker.with(getTargetContext()).Lorem.word());
            loc.setStreet(Faker.with(getTargetContext()).Lorem.word());
            user.setLocation(loc);
            Name name = new Name();
            name.setFirst(Faker.with(getTargetContext()).Lorem.word());
            name.setLast(Faker.with(getTargetContext()).Lorem.word());
            name.setTitle("Mr");
            user.setName(name);
            user.setPhone(Faker.with(getTargetContext()).Phone.phoneWithCountryCode());
            Picture picture = new Picture();
            picture.setLarge("https://dummyimage.com/900.png/09f/fff");
            picture.setMedium("https://dummyimage.com/600.png/09f/fff");
            picture.setThumbnail("https://dummyimage.com/300.png/09f/fff");
            user.setPicture(picture);

            Intent intent = new Intent(getTargetContext(), UserActivity.class);
            intent.putExtra(Constants.BUNDLE_CONST_USER,user);
            return intent;
        }
    };

    /**
     * Test Up navigation click
     * android.support.test.espresso.NoMatchingViewException: No views in hierarchy found matching: with id: android:id/home
     */
    @Test(expected = NoMatchingViewException.class)
    public void clickOnUpNavigation() {
        onView(withId(android.R.id.home)).perform(click());
    }

    @Test
    public void checkDataVisualization() throws Exception {
        onView(withId(R.id.user_image_view)).check(matches(isDisplayed()));
        onView(withId(R.id.user_surname)).check(matches(isDisplayed()));
        onView(withId(R.id.user_name)).check(matches(isDisplayed()));
        onView(withId(R.id.user_gender)).check(matches(isDisplayed()));
        onView(withId(R.id.user_location)).check(matches(isDisplayed()));
        onView(withId(R.id.user_date)).check(matches(isDisplayed()));
        onView(withId(R.id.user_email)).check(matches(isDisplayed()));
    }

}
