package com.example.Lab3;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class ResultActivityTest {

    @Rule
    public ActivityTestRule<ResultActivity> activityRule = new ActivityTestRule<ResultActivity>(ResultActivity.class, true, false) {
        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            intent.putExtra("result", "100"); // Тестове значення
            return intent;
        }
    };

    private ResultActivity resultActivity;

    @Before
    public void setUp() {
        resultActivity = activityRule.launchActivity(null);
    }

    @Test
    public void testResultTextView() {
        TextView resultTextView = resultActivity.findViewById(R.id.result);
        assertEquals("100", resultTextView.getText().toString());
    }

    @Test
    public void testPlayButton() {
        Button playButton = resultActivity.findViewById(R.id.play_btn);
        playButton.performClick();

        // Перевірка, чи запустилася MainActivity
        Intent expectedIntent = new Intent(resultActivity, MainActivity.class);
        assertEquals(expectedIntent.getComponent(), resultActivity.getIntent().getComponent());
    }

    @Test
    public void testMainButton() {
        Button mainButton = resultActivity.findViewById(R.id.main_btn);
        mainButton.performClick();

        // Перевірка, чи запустилася StartActivity
        Intent expectedIntent = new Intent(resultActivity, StartActivity.class);
        assertEquals(expectedIntent.getComponent(), resultActivity.getIntent().getComponent());
    }

    @Test
    public void testExitButton() {
        Button exitButton = resultActivity.findViewById(R.id.exit_btn);
        exitButton.performClick();

        // Перевірка, чи activity закривається
        assertEquals(true, resultActivity.isFinishing());
    }
}
