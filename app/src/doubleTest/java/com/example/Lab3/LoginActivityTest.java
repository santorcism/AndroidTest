package com.example.Lab3;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule =
            new ActivityTestRule<>(LoginActivity.class, true, false);

    private LoginActivity loginActivity;
    private FirebaseAuth mockAuth;

    @Before
    public void setUp() {
        // Start the activity
        loginActivity = activityRule.launchActivity(new Intent());
        mockAuth = Mockito.mock(FirebaseAuth.class);
        loginActivity.mAuth = mockAuth;  // Use the mocked FirebaseAuth
    }

    @Test
    public void testLoginSuccessful() {
        // Arrange
        EditText username = loginActivity.findViewById(R.id.username);
        EditText password = loginActivity.findViewById(R.id.password);
        Button loginButton = loginActivity.findViewById(R.id.loginButton);
        TextView checkLogin = loginActivity.findViewById(R.id.checklogin);

        username.setText("test@example.com");
        password.setText("password123");

        // Mock Firebase Authentication
        FirebaseUser mockUser = Mockito.mock(FirebaseUser.class);
        when(mockAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(mock(Task.class));
        when(mockAuth.getCurrentUser()).thenReturn(mockUser);
        when(mockUser.getEmail()).thenReturn("test@example.com");

        // Simulate successful login
        Task<AuthResult> task = Mockito.mock(Task.class);
        when(task.isSuccessful()).thenReturn(true);
        doAnswer(invocation -> {
            OnCompleteListener<AuthResult> listener = invocation.getArgument(0);
            listener.onComplete(task);
            return null;
        }).when(mockAuth).signInWithEmailAndPassword(anyString(), anyString());

        // Act
        loginButton.performClick();

        // Assert
        assertEquals("True", checkLogin.getText().toString());
        verify(mockAuth).signInWithEmailAndPassword("test@example.com", "password123");
        assertNotNull(loginActivity.getCurrentUser());
    }

    @Test
    public void testLoginFailed() {
        // Arrange
        EditText username = loginActivity.findViewById(R.id.username);
        EditText password = loginActivity.findViewById(R.id.password);
        Button loginButton = loginActivity.findViewById(R.id.loginButton);
        TextView checkLogin = loginActivity.findViewById(R.id.checklogin);

        username.setText("wrong@example.com");
        password.setText("wrongpassword");

        // Mock Firebase Authentication failure
        Task<AuthResult> task = Mockito.mock(Task.class);
        when(task.isSuccessful()).thenReturn(false);
        doAnswer(invocation -> {
            OnCompleteListener<AuthResult> listener = invocation.getArgument(0);
            listener.onComplete(task);
            return null;
        }).when(mockAuth).signInWithEmailAndPassword(anyString(), anyString());

        // Act
        loginButton.performClick();

        // Assert
        assertEquals("False", checkLogin.getText().toString());
    }

    @Test
    public void testSignupRedirect() {
        // Arrange
        Button signupButton = loginActivity.findViewById(R.id.signupText);

        // Act
        signupButton.performClick();

        // Assert
        Intent expectedIntent = new Intent(loginActivity, StartActivity.class);
        assertEquals(expectedIntent.getComponent(), loginActivity.getCurrentActivity().getIntent().getComponent());
    }
}
