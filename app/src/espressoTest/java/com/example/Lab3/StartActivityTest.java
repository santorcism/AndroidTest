package com.example.Lab3;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class StartActivityTest {

    @Rule
    public ActivityTestRule<StartActivity> activityTestRule =
            new ActivityTestRule<>(StartActivity.class);

    @Before
    public void setUp() {
        // No specific setup needed as we are testing the StartActivity directly
    }

    @Test
    public void testStartButtonNavigatesToMainActivity() {
        // Click the "Start" button
        onView(withId(R.id.start_btn)).perform(click());

        // Verify that MainActivity is displayed
        onView(withId(R.id.time)).check(matches(isDisplayed())); // Replace with an actual view ID in MainActivity
    }

    @Test
    public void testLeaveButtonNavigatesToLoginActivity() {
        // Click the "Leave" button
        onView(withId(R.id.leave_btn)).perform(click());

        // Verify that LoginActivity is displayed
        onView(withId(R.id.username)).check(matches(isDisplayed())); // Replace with an actual view ID in LoginActivity
    }

    @Test
    public void testStartButtonText() {
        // Verify that the "Start" button has the correct text
        onView(withId(R.id.start_btn)).check(matches(withText("Start"))); // Replace with the actual text
    }

    @Test
    public void testLeaveButtonText() {
        // Verify that the "Leave" button has the correct text
        onView(withId(R.id.leave_btn)).check(matches(withText("Leave"))); // Replace with the actual text
    }
}
