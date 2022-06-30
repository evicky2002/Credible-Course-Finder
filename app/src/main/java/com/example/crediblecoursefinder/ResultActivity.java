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

import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    JSONArray result;
    JSONObject jsonObject;
    String name="";
    TextView tvResult;
    String accesstoken = "Stcf4cFBNRgGJWk3m1HhU4d5oK1uPL73Z2QB0g5l";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();

        tvResult = (TextView)findViewById(R.id.tvResult);
        Intent intent = getIntent();
        String searchKey = intent.getStringExtra("SEARCHKEY");

//        RequestQueue queue = Volley.newRequestQueue(this);
        String endpoint = "https://www.udemy.com/api-2.0/courses/?page_size=90&search=";
        String url = endpoint+searchKey;

        Log.i("RESULTACTIVITY",url);

// Request a string response from the provided URL.
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("key", "value");
        } catch (Exception e) {
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, parameters,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    result = response.getJSONArray("results");
                    for (int i=0;i<result.length();i++){
                        jsonObject = result.getJSONObject(i);
                        name += "\n";
                        name += jsonObject.getString("title");
                    }
                    Log.i("RESULTACTIVITY", "hi");


                }catch (Exception e){
                    e.printStackTrace();
                }
                tvResult.setText(name);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESULTACTIVITY", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Basic Authentication
                //String auth = "Basic " + Base64.encodeToString(CONSUMER_KEY_AND_SECRET.getBytes(), Base64.NO_WRAP);

                headers.put("Authorization", "Bearer " + accesstoken);
                return headers;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(request);
    }
}