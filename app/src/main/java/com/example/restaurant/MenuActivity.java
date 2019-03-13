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
        Log.d("It got here!", "Really it did." + menus.size());
        MenuAdapter menuAdapter = new MenuAdapter(this, R.layout.menu_item, menus);
        ListView listView = findViewById(R.id.menuList);
        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(new MenuActivity.MenuListener());
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class MenuListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            MenuItem menuClicked = (MenuItem) parent.getAdapter().getItem(position);

            /*TextView titleView = view.findViewById(R.id.itemTitle);
            String title = (String) titleView.getText();
            intent.putExtra("title", title);
            TextView priceView = view.findViewById(R.id.itemPrice);
            String price = priceView.getText();
            intent.putExtra("price", price);*/
            //ImageView imageV = view.findViewById(R.id.itemImage);
            //imageV.get

            intent.putExtra("menu", menuClicked);
            startActivity(intent);
        }
    }

}
