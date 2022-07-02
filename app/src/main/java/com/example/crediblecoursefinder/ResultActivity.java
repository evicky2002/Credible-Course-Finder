package com.example.crediblecoursefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    int courseCounter = 0;
    public String searchKey;
    TextView tvResult;
    String[] titles;
    Integer[] ids;
    ArrayList<Double> ratings;
    CourseDataService courseDataService = new CourseDataService(ResultActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();

        tvResult = (TextView)findViewById(R.id.tvResult);
        Intent intent = getIntent();
        searchKey = intent.getStringExtra("SEARCHKEY");
        courseDataService.getCourses(searchKey, new CourseDataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.i("HELLO", "error");
            }

            @Override
            public void onResponse(String[] response, Integer[] id) {
                titles = response;
                ids = id;
                Log.i("HELLOBRO", "stored : "+Arrays.deepToString(titles));
                Log.i("HELLOBRO", Arrays.deepToString(response));
                Log.i("HELLOBRO", Arrays.deepToString(id));
                extra();

            }
        });

    }
    public void extra(){
        ratings = new ArrayList<>(titles.length);
        for(int i =0;i<ids.length;i++){
            courseDataService.getCourseRating(ids[i], new CourseDataService.VolleyRatingListener() {
                @Override
                public void onError(String message) {
                    Log.i("HELLOBRO", "error : here");

                }

                @Override
                public void onResponse(Double courseRating) {
                    ratings.add(courseRating);
                    courseCounter++;

                    Log.i("HELLOBRO", "courseRating : "+courseRating);
                    if (courseCounter==ids.length){
                        runMe();
                    }

                }
            });
        }
    }
    public void runMe(){
        Log.i("HELLOBRO", "rating : "+ratings);

    }
}