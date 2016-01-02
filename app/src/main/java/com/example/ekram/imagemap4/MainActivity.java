package com.example.ekram.imagemap4;


import android.app.TabActivity;
import android.os.Bundle;

import static com.example.ekram.imagemap4.R.layout.activity_main;

import android.content.Intent;

import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;


        intent = new Intent().setClass(this, MapActivity.class);
         spec = tabHost.newTabSpec("Map").setIndicator("Map")
                 .setContent(intent);
         tabHost.addTab(spec);

         intent = new Intent().setClass(this, DetailActivity.class);
         spec = tabHost.newTabSpec("Bus Details").setIndicator("Bus Details")
                 .setContent(intent);
         tabHost.addTab(spec);


        intent = new Intent().setClass(this, Mydatabase.class);
        spec = tabHost.newTabSpec("Route Planner").setIndicator("Route Planner")
                .setContent(intent);
        tabHost.addTab(spec);


    }



}
