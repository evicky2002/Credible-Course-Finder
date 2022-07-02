package com.example.crediblecoursefinder;

import android.content.Context;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CourseDataService {
    public static final String ACCESSTOKEN = "Stcf4cFBNRgGJWk3m1HhU4d5oK1uPL73Z2QB0g5l";
    public static final String ENDPOINT = "https://www.udemy.com/api-2.0/courses/?page_size=90&search=";
    public static final String ENDPOINT_RATING_1 = "https://www.udemy.com/api-2.0/courses/";
    public static final String ENDPOINT_RATING_2 = "/reviews/?page_size=100";
    JSONArray result;
    JSONArray ratingResult;
    JSONObject jsonObject, ratingObject;
    String[] courseTitles;
    Integer[] courseID;
    Context context;
    Double ratingAverage = null;

    public CourseDataService(Context context) {
        this.context = context;
    }
    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(String[] courseTitles, Integer[] courseID);
    }
    public interface VolleyRatingListener{
        void onError(String message);
        void onResponse(Double courseRating);
    }

    public void getCourses(String input, VolleyResponseListener volleyResponseListener){

        String url = ENDPOINT+input;

        JSONObject parameters = new JSONObject();
        try {
            parameters.put("key", "value");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, parameters,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    result = response.getJSONArray("results");
                    courseTitles = new String[result.length()];
                    courseID = new Integer[result.length()];

                    for (int i=0;i<result.length();i++){
                        jsonObject = result.getJSONObject(i);
                        courseTitles[i] = jsonObject.getString("title");
                        courseID[i] = Integer.parseInt(jsonObject.getString("id"));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                volleyResponseListener.onResponse(courseTitles, courseID);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("error");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + ACCESSTOKEN);
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getCourseRating(int input, VolleyRatingListener volleyRatingListener){
        Log.i("COURSEDATA", "HERE");

        String url = ENDPOINT_RATING_1+input+ENDPOINT_RATING_2;

        JSONObject parameters = new JSONObject();
        try {
            parameters.put("key", "value");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, parameters,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Double ratingTotal = 0d;

                    ratingResult = response.getJSONArray("results");
                    for (int j=0;j<ratingResult.length();j++) {
                        ratingObject = ratingResult.getJSONObject(j);
                        Double temp = Double.parseDouble(ratingObject.getString("rating"));
                        Log.i("COURSEDATA", "temp here : "+temp);

                        ratingTotal += temp;
                    }
                    ratingAverage = ratingTotal/ratingResult.length();

                }catch (Exception e){
                    e.printStackTrace();
                }
                Log.i("COURSEDATA", "sending here : "+ratingAverage);

                volleyRatingListener.onResponse(ratingAverage);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyRatingListener.onError("error");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + ACCESSTOKEN);
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

}
