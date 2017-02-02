package com.example.a3robit94.mapping;

import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.config.Configuration;

public class HelloMap extends Activity implements View.OnClickListener {
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
        mv.getController().setCenter(new GeoPoint(40.1,22.5)); //Sets the position of the map via longtitude/latitude

        Button b = (Button)findViewById(R.id.btn1);
        b.setOnClickListener(this);
    }

    public void onClick(View MapView){
        EditText longitudeEditText = (EditText) findViewById(R.id.longitudeEditText);
        double longitude = Double.parseDouble(longitudeEditText.getText().toString());
        EditText latitudeEditText = (EditText) findViewById(R.id.latitudeEditText);
        double latitude = Double.parseDouble(latitudeEditText.getText().toString());
        mv.getController().setCenter(new GeoPoint(latitude,longitude));
    }
}