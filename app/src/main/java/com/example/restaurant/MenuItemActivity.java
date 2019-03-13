package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // recover intent from clicked menu item
        Intent intent = getIntent();
        MenuItem menuRecovered = (MenuItem) intent.getSerializableExtra("menu");

        // initialize and put correct data into views
        TextView title = findViewById(R.id.itemTitle2);
        title.setText(menuRecovered.getName());
        TextView description = findViewById(R.id.itemDescription);
        description.setText(menuRecovered.getDescription());
        TextView price = findViewById(R.id.itemPrice2);
        price.setText("€" + Float.toString(menuRecovered.getPrice()));

        // set image with help of picasso
        ImageView image = findViewById(R.id.itemPicture2);
        Picasso.with(this).load(menuRecovered.getImageUrl()).into(image);
    }
}
