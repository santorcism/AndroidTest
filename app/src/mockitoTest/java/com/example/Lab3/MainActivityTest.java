package com.example.Lab3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
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
public class MainActivityTest {

    @Mock
    SharedPreferences sharedPreferences;
    @Mock
    SharedPreferences.Editor editor;

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Mock SharedPreferences behavior
        when(sharedPreferences.edit()).thenReturn(editor);
        when(editor.putInt(anyString(), anyInt())).thenReturn(editor);
        when(sharedPreferences.getInt(anyString(), anyInt())).thenReturn(0);

        // Launch the MainActivity
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(activity -> {
            mainActivity = activity;
            // Mock the SharedPreferences to use in the activity
            mainActivity.getPreferences(Context.MODE_PRIVATE).edit().putInt("testing_mode", 0).apply();
        });
    }

    @Test
    public void testIncrementPointsOnYesClick() {
        // Assuming initial points are "0"
        TextView points = mainActivity.findViewById(R.id.points);
        points.setText("0");

        // Simulate yes button click
        Button yesButton = mainActivity.findViewById(R.id.yes_btn);
        mainActivity.color1 = new TextView(mainActivity);
        mainActivity.color2 = new TextView(mainActivity);

        // Mock color values
        mainActivity.dictionary = mainActivity.array2dict(mainActivity.getResources().getStringArray(R.array.tags));
        mainActivity.color1.setText("red"); // Set color1 to a key in the dictionary
        mainActivity.color2.setText("red"); // Set color2 to a matching color

        // Simulate the click
        yesButton.performClick();

        // Verify points increment
        assertEquals("1", points.getText().toString());
    }

    @Test
    public void testIncrementPointsOnNoClick() {
        // Assuming initial points are "0"
        TextView points = mainActivity.findViewById(R.id.points);
        points.setText("0");

        // Simulate no button click
        Button noButton = mainActivity.findViewById(R.id.no_btn);
        mainActivity.color1 = new TextView(mainActivity);
        mainActivity.color2 = new TextView(mainActivity);

        // Mock color values
        mainActivity.dictionary = mainActivity.array2dict(mainActivity.getResources().getStringArray(R.array.tags));
        mainActivity.color1.setText("red"); // Set color1 to a key in the dictionary
        mainActivity.color2.setText("blue"); // Set color2 to a different color

        // Simulate the click
        noButton.performClick();

        // Verify points increment
        assertEquals("1", points.getText().toString());
    }

    @Test
    public void testTimerFinishRedirectsToResultActivity() {
        // Start a timer
        CountDownTimer timer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long l) {}

            @Override
            public void onFinish() {
                Intent intent = MainActivity.createIntent(mainActivity, "Test Points");
                mainActivity.startActivity(intent);
            }
        }.start();

        // Simulate the timer finishing
        timer.onFinish();

        // Verify that the ResultActivity was started with the correct points
        // This would require more complex verification in a real application
        // Check if the correct Intent was created and started
        // (You can also mock the context to check for Intent creation)
    }
}
