package com.example.crediblecoursefinder.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.crediblecoursefinder.R;
import com.example.crediblecoursefinder.ResultActivity;
import com.google.android.material.button.MaterialButton;


public class SearchFragment extends Fragment {
    EditText etSearch;
    MaterialButton btnSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        etSearch =(EditText) view.findViewById(R.id.etSearch);
        btnSearch = view.findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);

                String searchKey = etSearch.getText().toString().trim();
                intent.putExtra("SEARCHKEY",searchKey);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}