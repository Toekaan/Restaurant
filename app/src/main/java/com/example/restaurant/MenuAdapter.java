package com.example.restaurant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menus;

    public MenuAdapter(Context context, int resource, ArrayList<MenuItem> menus) {
        super(context, resource);
        this.menus = menus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

        // initialize views to be changed
        ImageView image = convertView.findViewById(R.id.itemImage);
        TextView title = convertView.findViewById(R.id.itemTitle);
        TextView price = convertView.findViewById(R.id.itemPrice);

        Log.d("IS this called?", "YES");

        // get correct information for item
        MenuItem convertMenu = menus.get(position);

        // put data from menu into view

        //image.setImageBitmap(convertMenu.getImageUrl());
        title.setText(convertMenu.getName());
        price.setText(Float.toString(convertMenu.getPrice()));

        return convertView;
    }
}
