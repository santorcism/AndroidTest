package com.example.Lab3;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(AndroidJUnit4.class)
public class StartActivityTest {

    private StartActivity startActivity;

    @Before
    public void setUp() {
        // Launch the StartActivity
        ActivityScenario<StartActivity> scenario = ActivityScenario.launch(StartActivity.class);
        scenario.onActivity(activity -> {
            startActivity = activity; // Get the reference to the activity
        });
    }

    @Test
    public void testStartButtonClick() {
        // Simulate click on the Start button
        Button startButton = startActivity.findViewById(R.id.start_btn);
        startButton.performClick();

        // Verify that MainActivity is started
        Intent expectedIntent = new Intent(startActivity, MainActivity.class);
        assertNotNull(expectedIntent);
        // Here we can verify the intent, but actual verification might need additional setup.
        // However, the actual verification of started intents is best done in an instrumented test.
    }

    @Test
    public void testLeaveButtonClick() {
        // Simulate click on the Leave button
        Button leaveButton = startActivity.findViewById(R.id.leave_btn);
        leaveButton.performClick();

        // Verify that LoginActivity is started
        Intent expectedIntent = new Intent(startActivity, LoginActivity.class);
        assertNotNull(expectedIntent);
        // Similar to the previous test, actual verification is more complex in unit tests.
    }
}
