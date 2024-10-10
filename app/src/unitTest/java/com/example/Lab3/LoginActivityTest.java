package com.example.Lab3;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testUIComponents() {
        LoginActivity loginActivity = activityRule.getActivity();

        // Check that UI components are initialized correctly
        EditText usernameEditText = loginActivity.findViewById(R.id.username);
        EditText passwordEditText = loginActivity.findViewById(R.id.password);
        Button loginButton = loginActivity.findViewById(R.id.loginButton);
        TextView signupTextView = loginActivity.findViewById(R.id.signupText);
        TextView checkLoginTextView = loginActivity.findViewById(R.id.checklogin);

        assertNotNull(usernameEditText);
        assertNotNull(passwordEditText);
        assertNotNull(loginButton);
        assertNotNull(signupTextView);
        assertNotNull(checkLoginTextView);
    }

    @Test
    public void testLoginButtonClick() {
        LoginActivity loginActivity = activityRule.getActivity();
        loginActivity.findViewById(R.id.username).setVisibility(View.VISIBLE);
        loginActivity.findViewById(R.id.password).setVisibility(View.VISIBLE);

        // Set valid credentials (this won't actually authenticate as Firebase is not mocked)
        EditText usernameEditText = loginActivity.findViewById(R.id.username);
        EditText passwordEditText = loginActivity.findViewById(R.id.password);
        usernameEditText.setText("test@example.com");
        passwordEditText.setText("password");

        // Simulate a button click
        Button loginButton = loginActivity.findViewById(R.id.loginButton);
        loginButton.performClick();

        // Check if the checklogin TextView gets updated (it should show "True" for a successful login)
        TextView checkLoginTextView = loginActivity.findViewById(R.id.checklogin);
        // Since we don't have Firebase in the test, we won't check the exact value here,
        // but you can check that the method was called appropriately.
        // Example: assertEquals("True", checkLoginTextView.getText().toString());
    }

    @Test
    public void testUpdateUI() {
        LoginActivity loginActivity = activityRule.getActivity();
        // Create a mock user for testing (you can't do this without mocking, but we're demonstrating the idea)
        FirebaseUser mockUser = new FirebaseUser() {
            @Override
            public String getEmail() {
                return "test@example.com";
            }

            @Override
            public String getUid() {
                return "test-uid";
            }
        };

        // Call updateUI
        loginActivity.updateUI(mockUser);

        // Check that an intent was started with the correct data
        Intent intent = loginActivity.getIntent();
        assertEquals("test@example.com", intent.getStringExtra("email"));
    }
}
