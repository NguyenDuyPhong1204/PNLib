package com.example.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class manHinhCho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(manHinhCho.this, dang_nhap.class));
            }
        },3000);
    }
}