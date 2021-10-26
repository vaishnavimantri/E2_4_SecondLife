package com.example.secondlifetesting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        Intent loginpage = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginpage);


    }

    public void Register(View view) {
        Intent registerpage = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(registerpage);
    }
}