package com.example.Lab3;

import android.content.Intent;
import android.widget.TextView;

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
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class ResultActivityTest {

    @Rule
    public ActivityTestRule<ResultActivity> activityTestRule =
            new ActivityTestRule<>(ResultActivity.class, true, false);

    @Before
    public void setUp() {
        // Prepare the intent to launch ResultActivity with test data
        Intent intent = new Intent();
        intent.putExtra("result", "Test Result: 5");
        activityTestRule.launchActivity(intent);
    }

    @Test
    public void testResultTextDisplayedCorrectly() {
        // Verify that the result text is displayed correctly
        onView(withId(R.id.result)).check(matches(withText("Test Result: 5")));
    }

    @Test
    public void testPlayButtonNavigatesToMainActivity() {
        // Click the "Play" button
        onView(withId(R.id.play_btn)).perform(click());

        // Verify that MainActivity is displayed
        onView(withId(R.id.time)).check(matches(withText("00:60"))); // Assuming MainActivity shows timer
    }

    @Test
    public void testMainButtonNavigatesToStartActivity() {
        // Click the "Main" button
        onView(withId(R.id.main_btn)).perform(click());

        // Verify that StartActivity is displayed
        onView(withId(R.id.some_start_activity_view)).check(matches(isDisplayed())); // Change this to an actual view ID in StartActivity
    }

    @Test
    public void testExitButtonFinishesActivity() {
        // Click the "Exit" button
        onView(withId(R.id.exit_btn)).perform(click());

        // Verify that ResultActivity is finished (ensure no views are displayed)
        onView(withId(R.id.result)).check(doesNotExist());
    }
}
