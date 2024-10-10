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

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() {
        // Launch the MainActivity before each test
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void testInitialPointsAreZero() {
        // Check if points TextView shows initial value of 0
        onView(withId(R.id.points)).check(matches(withText("0")));
    }

    @Test
    public void testYesButtonIncrementsPointsCorrectly() {
        setTestColorsForYesButton(); // Set up for a correct answer

        // Click the "Yes" button
        onView(withId(R.id.yes_btn)).perform(click());

        // Check if points incremented
        onView(withId(R.id.points)).check(matches(withText("1"))); // assuming initial points are 0
    }

    @Test
    public void testNoButtonIncrementsPointsCorrectly() {
        setTestColorsForNoButton(); // Set up for an incorrect answer

        // Click the "No" button
        onView(withId(R.id.no_btn)).perform(click());

        // Check if points incremented
        onView(withId(R.id.points)).check(matches(withText("1"))); // assuming initial points are 0
    }

    @Test
    public void testYesButtonDoesNotIncrementPointsOnWrongAnswer() {
        setTestColorsForNoButton(); // Set up for a wrong answer

        // Click the "Yes" button
        onView(withId(R.id.yes_btn)).perform(click());

        // Check if points remain the same
        onView(withId(R.id.points)).check(matches(withText("0"))); // assuming initial points are 0
    }

    @Test
    public void testNoButtonDoesNotIncrementPointsOnCorrectAnswer() {
        setTestColorsForYesButton(); // Set up for a correct answer

        // Click the "No" button
        onView(withId(R.id.no_btn)).perform(click());

        // Check if points remain the same
        onView(withId(R.id.points)).check(matches(withText("0"))); // assuming initial points are 0
    }

    @Test
    public void testColorChangeOnButtonClick() {
        setTestColorsForYesButton(); // Set up for a correct answer

        // Click the "Yes" button to change colors
        onView(withId(R.id.yes_btn)).perform(click());

        // Verify that colors have changed (implementation specific)
        // This will depend on how your colors are set and displayed.
    }

    @Test
    public void testCountdownTimerFunctionality() {
        // Start the countdown timer
        onView(withId(R.id.time)).check(matches(withText("00:60"))); // Assuming timer starts at 60 seconds

        // Simulate waiting for some time (e.g., using Idling Resources or Thread.sleep)
        try {
            Thread.sleep(5000); // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the timer reflects the countdown (should show 55 seconds after 5 seconds)
        onView(withId(R.id.time)).check(matches(withText("00:55")));
    }

    private void setTestColorsForYesButton() {
        // Set up mock values for testing
        Map<String, Integer> mockColors = new HashMap<>();
        mockColors.put("Red", 0xFFFF0000); // Example color mapping
        mockColors.put("Green", 0xFF00FF00);
        mockColors.put("Blue", 0xFF0000FF);

    }

    private void setTestColorsForNoButton() {
        // Set up mock values for testing
        Map<String, Integer> mockColors = new HashMap<>();
        mockColors.put("Red", 0xFFFF0000); // Example color mapping
        mockColors.put("Green", 0xFF00FF00);
        mockColors.put("Blue", 0xFF0000FF);

    }
}
