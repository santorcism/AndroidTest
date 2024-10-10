package com.example.Lab3;

import static java.lang.String.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private TextView time;
    private TextView points;
    private TextView color1;
    private TextView color2;
    private Map<String,Integer> dictionary;
    private int size;
    private Object[] values;
    private List<String> keyList;
    private int attempt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("main_activity", "onCreate");
        setContentView(R.layout.activity_main);
        String[] tags = getResources().getStringArray(R.array.tags);
        dictionary = array2dict(tags);

        Set<String> keySet = dictionary.keySet();
        keyList = new ArrayList<>(keySet);
        values = dictionary.values().toArray();
        size = keyList.size();
        onYesClick();
        onNoClick();


        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int status = 1;

        setTestMode(sharedPref, status);

        time = (TextView) findViewById(R.id.time);
        new CountDownTimer(60000, 1000){
            @Override
            public void onTick(long l){
                if(l < 10000)
                    time.setText("00:0" + Long.toString(l/1000) );
                else
                    time.setText("00:" + Long.toString(l/1000) );

            }
            @Override
            public void onFinish(){
                time.setText("00:00");
                Intent intent = createIntent(MainActivity.this, points.getText());

                finish();
                startActivity(intent);
            }
        }.start();
    }

    static public Intent createIntent(Context context, CharSequence points){
        Intent i = new Intent(context, ResultActivity.class);
        i.putExtra("result", points);
        return i;
    }

    static public void setTestMode(SharedPreferences sharedPref, int status){
//        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("testing_mode", status);
        editor.apply();
    }

    static public Map array2dict(String[] tags) {
        Map dictionary = new HashMap<String,Integer>();
        for (String tag : tags) {
            String[] pair = tag.split(":");

            String key = pair[0];
            int value = Integer.parseInt(pair[1]);
            dictionary.put(key, value);//

            // do whatever you want with key and value
        }
        return dictionary;
    }


    public void onYesClick (){
        Button yes = (Button) findViewById(R.id.yes_btn);
        points = (TextView) findViewById(R.id.points);
        color1 = (TextView) findViewById(R.id.color1);
        color2 = (TextView) findViewById(R.id.color2);

        yes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int c2 = color2.getCurrentTextColor();
                        //System.out.println("color: "+c2);
                        if(c2 == dictionary.get(color1.getText().toString())){
                            int prev = Integer.parseInt(points.getText().toString());
                            points.setText(Integer.toString(prev+1));
                        }
                        changeColors();
                        attempt += 1;
                    }
                }
        );
    }
    public void onNoClick (){
        Button no = (Button) findViewById(R.id.no_btn);
        points = (TextView) findViewById(R.id.points);
        color1 = (TextView) findViewById(R.id.color1);
        color2 = (TextView) findViewById(R.id.color2);
        no.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int c2 = color2.getCurrentTextColor();
                        if(c2 != dictionary.get(color1.getText().toString())){
                            int prev = Integer.parseInt(points.getText().toString());
                            points.setText(Integer.toString(prev+1));
                        }
                        changeColors();
                        attempt += 1;
                    }
                }
        );
    }
    public void changeColors(){

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int highScore = sharedPref.getInt(getString(R.string.testing_mode), 0);

        if (highScore == 0) {
            randomChangeColors();
        }
        else{
            fixedChangeColors();
        }
    }

    public void randomChangeColors() {


        Random R = new Random();
        color1.setText(keyList.get(R.nextInt(size)));
        color1.setTextColor((Integer) values[R.nextInt(values.length)]);
        color2.setText(keyList.get(R.nextInt(size)));
        color2.setTextColor((Integer) values[R.nextInt(values.length)]);
    }

    public void fixedChangeColors() {
        if (attempt < size) {
            color1.setText(keyList.get(attempt));
            color1.setTextColor((Integer) values[attempt]);
            color2.setText(keyList.get(attempt));
            color2.setTextColor((Integer) values[attempt]);
        }
    }


}