package com.example.Lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Lab3.R;

public class StartActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        addListenerOnButton();

    }

    void addListenerOnButton(){
        Button start_btn = (Button) findViewById(R.id.start_btn);
        Button leave_btn = (Button) findViewById(R.id.leave_btn);
        start_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        //finish();
                        startActivity(intent);
                    }
                }
        );
        leave_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                        //finish();
                        startActivity(intent);

                    }
                }
        );
    }

}