package com.example.crediblecoursefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //Getting the FireBase Auth Instance
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        Handler handler = new Handler();
        //Getting the current user Object
        FirebaseUser user = mAuth.getCurrentUser();
        //Runs the runnable after a given specified time
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(user !=null){
                    //If a user exists, then go the First Activity
                    Intent firstActivityIntent = new Intent(MainActivity.this, FirstActivity.class);
                    startActivity(firstActivityIntent);
                    finish();
                }else{
                    //If a user doesn't exists, then go the SignIn Activity
                    Intent firstActivityIntent = new Intent(MainActivity.this, SignInActivity.class);
                    startActivity(firstActivityIntent);
                    finish();
                }
            }
        }, 1500);
    }
}