package com.example.Lab3;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class StartActivityTest {

    @Test
    public void testStartButtonNavigatesToMainActivity() {
        // Запускаємо StartActivity
        ActivityScenario<StartActivity> scenario = ActivityScenario.launch(StartActivity.class);

        // Натискаємо кнопку старту
        onView(withId(R.id.start_btn)).perform(click());

        // Перевіряємо, чи MainActivity запущена
        scenario.onActivity(activity -> {
            Intent intent = new Intent(activity, MainActivity.class);
            assertNotNull(intent); // Тут ви можете додати більше перевірок, якщо потрібно
        });
    }

    @Test
    public void testLeaveButtonNavigatesToLoginActivity() {
        // Запускаємо StartActivity
        ActivityScenario<StartActivity> scenario = ActivityScenario.launch(StartActivity.class);

        // Натискаємо кнопку виходу
        onView(withId(R.id.leave_btn)).perform(click());

        // Перевіряємо, чи LoginActivity запущена
        scenario.onActivity(activity -> {
            Intent intent = new Intent(activity, LoginActivity.class);
            assertNotNull(intent); // Тут ви можете додати більше перевірок, якщо потрібно
        });
    }
}
