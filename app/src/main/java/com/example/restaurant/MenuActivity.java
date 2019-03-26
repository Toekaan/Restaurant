package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // get category string from intent
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        // send off menurequest with category string
        MenuRequest x = new MenuRequest(this);
        x.getMenuItems(this, category);
    }

    @Override
    public void gotMenuRequest(ArrayList<MenuItem> menus) {
        MenuAdapter menuAdapter = new MenuAdapter(this, R.layout.menu_item, menus);
        ListView listView = findViewById(R.id.menuList);
        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(new MenuActivity.MenuListener());
    }

    @Override
    public void gotMenuError(String message) {
        // feedback error message to user
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class MenuListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);

            // check which menu was clicked
            MenuItem menuClicked = (MenuItem) parent.getAdapter().getItem(position);

            // send user off to new activity
            intent.putExtra("menu", menuClicked);
            startActivity(intent);
        }
    }

}
