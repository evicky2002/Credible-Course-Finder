package com.example.crediblecoursefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    public String searchKey;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();

        tvResult = (TextView)findViewById(R.id.tvResult);
        Intent intent = getIntent();
        searchKey = intent.getStringExtra("SEARCHKEY");

        CourseDataService courseDataService = new CourseDataService(ResultActivity.this);
        courseDataService.getCourses(searchKey, new CourseDataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.i("HELLO", "error");
            }

            @Override
            public void onResponse(String[] response) {
                Log.i("HELLOBRO", Arrays.deepToString(response));

            }
        });

    }
}