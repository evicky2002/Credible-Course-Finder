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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CourseDataService {
    public static final String ACCESSTOKEN = "Stcf4cFBNRgGJWk3m1HhU4d5oK1uPL73Z2QB0g5l";
    public static final String ENDPOINT = "https://www.udemy.com/api-2.0/courses/?page_size=90&search=";
    JSONArray result;
    JSONObject jsonObject;
    String[] courseTitles;
    Context context;

    public CourseDataService(Context context) {
        this.context = context;
    }
    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(String[] courseTitles);
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
                    Log.i("COURSE", "in");

                    result = response.getJSONArray("results");
                    Log.i("COURSE", "HEY");
                    courseTitles = new String[result.length()];

                    for (int i=0;i<result.length();i++){
                        jsonObject = result.getJSONObject(i);
                        Log.i("COURSE", jsonObject.getString("title"));

                        courseTitles[i] = jsonObject.getString("title");
                        Log.i("COURSE", i+" : "+courseTitles[i]);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                Log.i("COURSE","gfjgf");
                volleyResponseListener.onResponse(courseTitles);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESULTACTIVITY", error.toString());
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
}
