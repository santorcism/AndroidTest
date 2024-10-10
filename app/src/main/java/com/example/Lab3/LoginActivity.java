package com.example.Lab3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    TextView signup;
    TextView checklogin;

    Button loginButton;
    private FirebaseAuth mAuth;
    private String email_val, password_val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signup = findViewById(R.id.signupText);
        checklogin = findViewById(R.id.checklogin);
        mAuth = FirebaseAuth.getInstance();

        //checking if user is logged in
        if (mAuth.getCurrentUser() != null) {
            updateUI(mAuth.getCurrentUser());
        }



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
//                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
//                }

                email_val = username.getText().toString();
                password_val = password.getText().toString();
                mAuth.signInWithEmailAndPassword(email_val, password_val)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("LoginActivity", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    checklogin.setText("True");
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
                                    checklogin.setText("False");
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_LONG).show();
                                }

                                // ...
                            }


                        });
            }
        });


        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(registerIntent);
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }

    public void updateUI(FirebaseUser currentUser) {
        Intent profileIntent = new Intent(getApplicationContext(), StartActivity.class);
        profileIntent.putExtra("email", currentUser.getEmail());
        Log.v("DATA", currentUser.getUid());
        startActivity(profileIntent);
    }

}