package com.example.restaurant;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    Callback activity;
    String category;

    public interface Callback {
        void gotMenuRequest(ArrayList<MenuItem> menus);
        void gotMenuError(String messaage);
    }

    public MenuRequest(Context context) {
        this.context = context;
    }

    public void getMenuItems(Callback activity, String category) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest JsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(JsonObjectRequest);
        this.activity = activity;
        this.category = category;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<MenuItem> menuList = new ArrayList<>();
        // fail safe for JSON
        try {
            JSONArray items = response.getJSONArray("items");
            // for loop turning JSON into MenuItems
            for (int i = 0; i < items.length(); i++) {
                // initialize item to be appended to menulist later
                MenuItem item = new MenuItem();

                // prepare temporary data holder
                JSONObject temp = items.getJSONObject(i);



                // only get items in specified category
                if (temp.getString("category").equals(category)) {
                    Log.d("MENU REQUEST CATEGORY LOOP:", temp.getString("category"));
                    item.setCategory(temp.getString("category"));
                    item.setDescription(temp.getString("description"));
                    item.setImageUrl(temp.getString("image_url"));
                    item.setName(temp.getString("name"));
                    item.setPrice(temp.getLong("price"));
                    menuList.add(item);
                }


                // extract correct data from JSONarray
                /*String category = temp.getString(0);
                String description = temp.getString(1);
                float price = (float) temp.getLong(2);
                String url = temp.getString(3);
                int id = temp.getInt(4);
                String name = temp.getString(5);*/

                // put data into Menu item
                /*item.setCategory(temp.getString("category"));
                item.setDescription(temp.getString("description"));
                item.setImageUrl(temp.getString("image_url"));
                item.setName(temp.getString("name"));
                item.setPrice(temp.getLong("price"));*/

                // add menu item to list

            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        // send menuList back to activity
        activity.gotMenuRequest(menuList);
    }
}
