package com.myd.cobiwebapps;

import android.content.Intent;
import android.os.Build;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.myd.cobiwebapps.webapps.view.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;


/**
 * Created by MYD on 12/1/17.
 *
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void grantPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation().executeShellCommand(
                    "pm grant " + getInstrumentation().getTargetContext().getPackageName()
                            + " android.permission.ACCESS_FINE_LOCATION");
            getInstrumentation().getUiAutomation().executeShellCommand(
                    "pm grant " + getInstrumentation().getTargetContext().getPackageName()
                            + " android.permission.VIBRATE");
        }
    }


    @Test
    public void testViewPager() throws Exception {
        activityTestRule.launchActivity(new Intent());
        onView(withId(R.id.container)).perform(withCustomConstraints(swipeLeft(),isDisplayingAtLeast(85)));
        onView(withId(R.id.container)).perform(withCustomConstraints(swipeLeft(),isDisplayingAtLeast(85)));
        onView(withId(R.id.container)).perform(withCustomConstraints(swipeLeft(),isDisplayingAtLeast(85)));

        onView(allOf(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE), withId(R.id.fragment_vibrate_button))).check(matches(isDisplayed()));

        onView(withId(R.id.container)).perform(withCustomConstraints(swipeRight(),isDisplayingAtLeast(85)));
        onView(withId(R.id.container)).perform(withCustomConstraints(swipeRight(),isDisplayingAtLeast(85)));
        onView(withId(R.id.container)).perform(withCustomConstraints(swipeRight(),isDisplayingAtLeast(85)));
    }

    public static ViewAction withCustomConstraints(final ViewAction action, final Matcher<View> constraints) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return constraints;
            }

            @Override
            public String getDescription() {
                return action.getDescription();
            }

            @Override
            public void perform(UiController uiController, View view) {
                action.perform(uiController, view);
            }
        };
    }
}
