package com.example.restaurant;

import android.content.Intent;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
        Log.d("Works", "we here");

    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(categoriesAdapter);
        listView.setOnItemClickListener(new  CatClickListener());
    }


    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    private class CatClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("WHY NO", "please");
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            String clickedCat = (String) parent.getItemAtPosition(position);
            Log.d("REALLY OwO?", clickedCat);
            intent.putExtra("category", clickedCat);
            startActivity(intent);
        }
    }
}
