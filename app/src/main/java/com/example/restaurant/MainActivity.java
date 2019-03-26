package com.example.restaurant;

import android.content.Intent;
import android.net.sip.SipSession;
import android.os.PersistableBundle;
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

        // make request to server
        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
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
        // feedback error message
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /** Click listener which listens to specific category clicked */
    private class CatClickListener implements AdapterView.OnItemClickListener {

        // send user off to activity corresponding with the category clicked
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            String clickedCat = (String) parent.getItemAtPosition(position);
            intent.putExtra("category", clickedCat);
            startActivity(intent);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
