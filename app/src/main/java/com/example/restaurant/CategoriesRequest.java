package com.example.restaurant;

import android.app.Activity;
import android.content.Context;
import android.telecom.Call;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    Callback activity;


    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    public void getCategories(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest JsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(JsonObjectRequest);
        this.activity = activity;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // give error message back to activity
        activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        // extract arraylist of strings out of JSONobject
        try {
            JSONArray JSONcategories = response.getJSONArray("categories");
            ArrayList<String> categories = new ArrayList<>();
            for (int i = 0; i < JSONcategories.length(); i++) {
                categories.add(JSONcategories.getString(i));
                // give arraylist callback to activity
                activity.gotCategories(categories);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
