package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // get caregory string from intent
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        // send off menurequest with category string
        MenuRequest x = new MenuRequest(this);
        x.getMenuItems(this, category);
    }

    @Override
    public void gotMenuRequest(ArrayList<MenuItem> menus) {
        Log.d("It got here!", "Really it did.");
        MenuAdapter menuAdapter = new MenuAdapter(this, R.layout.menu_item, menus);
        ListView listView = findViewById(R.id.menuList);
        listView.setAdapter(menuAdapter);
        // listView.setOnItemClickListener(new MainActivity.CatClickListener());
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
