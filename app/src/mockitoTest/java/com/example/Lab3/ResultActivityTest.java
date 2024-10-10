package com.example.Lab3;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(AndroidJUnit4.class)
public class ResultActivityTest {

    @Mock
    Intent mockIntent;

    private ResultActivity resultActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mocking the intent to pass data to ResultActivity
        mockIntent = Mockito.mock(Intent.class);
        when(mockIntent.getStringExtra("result")).thenReturn("Test Result");

        // Launch the ResultActivity with the mocked Intent
        ActivityScenario<ResultActivity> scenario = ActivityScenario.launch(ResultActivity.class);
        scenario.onActivity(activity -> {
            resultActivity = activity;
            resultActivity.setIntent(mockIntent); // Set the mocked intent
        });
    }

    @Test
    public void testResultDisplay() {
        // Verify that the result text is set correctly
        TextView resultTextView = resultActivity.findViewById(R.id.result);
        assertEquals("Test Result", resultTextView.getText().toString());
    }

    @Test
    public void testPlayButtonClick() {
        // Simulate play button click
        Button playButton = resultActivity.findViewById(R.id.play_btn);
        playButton.performClick();

        // Verify that the intent to start MainActivity is created and started
        Intent expectedIntent = new Intent(resultActivity, MainActivity.class);
        verify(resultActivity).startActivity(expectedIntent);
        assertEquals(0, resultActivity.getFragmentManager().getBackStackEntryCount());
    }

    @Test
    public void testMainButtonClick() {
        // Simulate main button click
        Button mainButton = resultActivity.findViewById(R.id.main_btn);
        mainButton.performClick();

        // Verify that the intent to start StartActivity is created and started
        Intent expectedIntent = new Intent(resultActivity, StartActivity.class);
        verify(resultActivity).startActivity(expectedIntent);
        assertEquals(0, resultActivity.getFragmentManager().getBackStackEntryCount());
    }

    @Test
    public void testExitButtonClick() {
        // Simulate exit button click
        Button exitButton = resultActivity.findViewById(R.id.exit_btn);
        exitButton.performClick();

        // Verify that the activity finishes
        verify(resultActivity).finish();
    }
}
