package com.example.crediblecoursefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        String searchKey = intent.getStringExtra("SEARCHKEY");
        Toast.makeText(this, searchKey, Toast.LENGTH_SHORT).show();

    }
}