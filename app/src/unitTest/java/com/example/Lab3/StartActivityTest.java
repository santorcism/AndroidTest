package com.example.Lab3;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class StartActivityTest {

    @Rule
    public ActivityTestRule<StartActivity> activityRule =
            new ActivityTestRule<>(StartActivity.class);

    @Test
    public void testUIComponents() {
        StartActivity startActivity = activityRule.getActivity();

        // Check that UI components are initialized correctly
        Button startButton = startActivity.findViewById(R.id.start_btn);
        Button leaveButton = startActivity.findViewById(R.id.leave_btn);

        assertNotNull(startButton);
        assertNotNull(leaveButton);
    }

    @Test
    public void testStartButtonClick() {
        StartActivity startActivity = activityRule.getActivity();

        // Simulate a button click on the start button
        Button startButton = startActivity.findViewById(R.id.start_btn);
        startButton.performClick();

        // Verify that the intent started is MainActivity
        Intent expectedIntent = new Intent(startActivity, MainActivity.class);
        Intent actualIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.example.Lab3");

        assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
    }

    @Test
    public void testLeaveButtonClick() {
        StartActivity startActivity = activityRule.getActivity();

        // Simulate a button click on the leave button
        Button leaveButton = startActivity.findViewById(R.id.leave_btn);
        leaveButton.performClick();

        // Verify that the intent started is LoginActivity
        Intent expectedIntent = new Intent(startActivity, LoginActivity.class);
        Intent actualIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.example.Lab3");

        assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
    }
}
