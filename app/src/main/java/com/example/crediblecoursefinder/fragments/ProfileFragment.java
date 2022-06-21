package com.example.crediblecoursefinder.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.crediblecoursefinder.CircleTransform;
import com.example.crediblecoursefinder.FirstActivity;
import com.example.crediblecoursefinder.R;
import com.example.crediblecoursefinder.SignInActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {
    FirebaseAuth mAuth;
    MaterialButton btnSignOut;
    TextView tvUser, tvEmail;
    ImageView ivProfile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btnSignOut = view.findViewById(R.id.btnSignOut);
        tvUser = view.findViewById(R.id.tvUser);
        tvEmail = view.findViewById(R.id.tvEmail);
        ivProfile = view.findViewById(R.id.ivProfile);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String name = currentUser.getDisplayName();
        String email = currentUser.getEmail();
        tvUser.setText(name);
        tvEmail.setText(email);
        Glide.with(getActivity())
                .load(currentUser.getPhotoUrl()) // add your image url
                .transform(new CircleTransform(getActivity())) // applying the image transformer
                .into(ivProfile);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}