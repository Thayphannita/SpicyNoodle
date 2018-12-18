package com.example.asus.spicynoodle;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        new Handler().postDelayed(runnable,1000);

    }



}