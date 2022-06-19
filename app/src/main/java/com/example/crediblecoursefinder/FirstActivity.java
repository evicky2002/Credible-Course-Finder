package com.example.crediblecoursefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.crediblecoursefinder.databinding.ActivityFirstBinding;
import com.example.crediblecoursefinder.databinding.ActivityMainBinding;
import com.example.crediblecoursefinder.fragments.CartFragment;
import com.example.crediblecoursefinder.fragments.ProfileFragment;
import com.example.crediblecoursefinder.fragments.SearchFragment;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirstActivity extends AppCompatActivity {
    ActivityFirstBinding bindng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindng = ActivityFirstBinding.inflate(getLayoutInflater());
        setContentView(bindng.getRoot());
        getSupportActionBar().hide();
        replaceFragment(new SearchFragment());
        bindng.bottomNavigationView.setSelectedItemId(R.id.search);
        //BottomNavigation
        bindng.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.cart:
                    replaceFragment(new CartFragment());
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;

            }
            return true;
        });

        //Sign Out

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}