package com.example.a3robit94.mapping;

import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;

import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.config.Configuration;

public class HelloMap extends Activity{
    //creates an activity through the use of extends

    MapView mv;

    public void onCreate(Bundle savedInstanceState){
        //OnCreate runs the code when program is loaded

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Sets content view layout to what is defined it activity_main.xml

        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView)findViewById(R.id.map1); //finds the mapview by ID


        mv.setBuiltInZoomControls(true); //Gives zoom controls
        mv.getController().setZoom(14); //Sets zoom level
        mv.getController().setCenter(new GeoPoint(51.05,-0.72)); //Sets the position of the map
    }
}