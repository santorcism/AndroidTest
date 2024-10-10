package com.example.Lab3;

import android.content.Intent;
import android.widget.EditText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule =
            new ActivityTestRule<>(LoginActivity.class, true, false);

    @Before
    public void setUp() {
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void testLoginSuccessful() {
        // Input valid credentials
        onView(withId(R.id.username)).perform(typeText("test@example.com"));
        onView(withId(R.id.password)).perform(typeText("valid_password"));

        // Click the login button
        onView(withId(R.id.loginButton)).perform(click());

        // Check if the next activity is launched (you can verify UI changes or intent)
        // This part requires checking if the StartActivity was launched
        ActivityScenario<StartActivity> scenario = ActivityScenario.launch(StartActivity.class);
        scenario.onActivity(activity -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            assertNotNull(user); // Ensure that the user is not null
        });
    }

    @Test
    public void testLoginFailed() {
        // Input invalid credentials
        onView(withId(R.id.username)).perform(typeText("wrong@example.com"));
        onView(withId(R.id.password)).perform(typeText("wrong_password"));

        // Click the login button
        onView(withId(R.id.loginButton)).perform(click());

        // Check for failure message (Toast) or update in UI
        // Note: Toast verification can be done using IdlingResource or custom methods
    }

    @Test
    public void testSignupRedirect() {
        // Click the signup button
        onView(withId(R.id.signupText)).perform(click());

        // Check if the StartActivity is launched
        ActivityScenario<StartActivity> scenario = ActivityScenario.launch(StartActivity.class);
        // You can add assertions based on the UI of StartActivity
    }
}
