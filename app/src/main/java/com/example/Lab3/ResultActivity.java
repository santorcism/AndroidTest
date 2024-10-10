package com.example.Lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView result;
    private Button play_btn;
    private Button main_btn;
    private Button exit_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = (TextView) findViewById(R.id.result);
        addListenerOnButton();
        Intent intent = getIntent();
        result.setText(intent.getStringExtra("result"));
    }
    void addListenerOnButton(){
        play_btn = (Button) findViewById(R.id.play_btn);
        main_btn = (Button) findViewById(R.id.main_btn);
        exit_btn = (Button) findViewById(R.id.exit_btn);
        play_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                }
        );
        main_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ResultActivity.this, StartActivity.class);
                        finish();
                        startActivity(intent);
                    }
                }
        );
        exit_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }
        );
    }

    static public Intent createIntent(Context context, CharSequence points){
        Intent i = new Intent(context, ResultActivity.class);
        i.putExtra("result", points);
        return i;
    }


}