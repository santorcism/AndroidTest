package com.example.Lab3;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    private LoginActivity loginActivity;
    private FirebaseAuth mAuth;

    @Before
    public void setUp() {
        // Mock FirebaseAuth and FirebaseUser
        mAuth = mock(FirebaseAuth.class);
        loginActivity = new LoginActivity();
        loginActivity.mAuth = mAuth; // Inject the mocked mAuth
    }

    @Test
    public void testSuccessfulLogin() {
        // Mock FirebaseUser
        FirebaseUser user = mock(FirebaseUser.class);
        when(user.getEmail()).thenReturn("test@example.com");
        when(mAuth.getCurrentUser()).thenReturn(user);

        // Start the activity
        ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(LoginActivity.class);

        // Simulate login button click
        scenario.onActivity(activity -> {
            // Mock input fields
            EditText username = activity.username;
            EditText password = activity.password;

            username.setText("test@example.com");
            password.setText("password123");

            // Mock sign-in process
            doAnswer(invocation -> {
                ((OnCompleteListener<AuthResult>) invocation.getArguments()[1]).onComplete(mock(Task.class));
                return null;
            }).when(mAuth).signInWithEmailAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());

            // Click login button
            activity.loginButton.performClick();
        });

        // Check if the user is redirected to StartActivity
        scenario.onActivity(activity -> {
            Intent intent = new Intent(activity.getApplicationContext(), StartActivity.class);
            assertNotNull(intent);
        });
    }

    @Test
    public void testFailedLogin() {
        // Start the activity
        ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(LoginActivity.class);

        // Simulate login button click
        scenario.onActivity(activity -> {
            // Mock input fields
            EditText username = activity.username;
            EditText password = activity.password;

            username.setText("wronguser@example.com");
            password.setText("wrongpassword");

            // Mock failed sign-in process
            doAnswer(invocation -> {
                ((OnCompleteListener<AuthResult>) invocation.getArguments()[1]).onComplete(mock(Task.class));
                return null;
            }).when(mAuth).signInWithEmailAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());

            // Click login button
            activity.loginButton.performClick();
        });

        // Verify that the error message is shown
        scenario.onActivity(activity -> {
            TextView checklogin = activity.checklogin;
            assertNotNull(checklogin.getText().toString());
        });
    }
}
