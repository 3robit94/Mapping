package com.example.a3robit94.mapping;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.config.Configuration;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

import java.util.Map;

public class HelloMap extends Activity{
    //creates an activity through the use of extends

    MapView mv;

    public void onCreate(Bundle savedInstanceState) {
        //OnCreate runs the code when program is loaded

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Sets content view layout to what is defined it activity_main.xml

        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView) findViewById(R.id.map1); //finds the mapview by ID

        mv.setBuiltInZoomControls(true); //Gives zoom controls
        mv.getController().setZoom(14); //Sets zoom level
        mv.getController().setCenter(new GeoPoint(40.1, 22.5)); //Sets the position of the map via longtitude/latitude
    }

    public boolean onCreateOptionsMenu(Menu menu)           //Loads in xml layout file and generates a menu from it
    {
        MenuInflater inflater=getMenuInflater();            //MenuInflater = A class which takes a menu xml file and generates a java menu object from it
        inflater.inflate(R.menu.menu_hello_map, menu);      //Parsing in the menu xml file and inflates/generates a menu object from it
        return true;                                        //Outputs menu object
    }

    public boolean onOptionsItemSelected(MenuItem item)     //will run when user choose a menu entry
    {
        if(item.getItemId() == R.id.choosemaplist)              //getting id of menu item (This being R.id.choosemap)
        {
            Intent intent = new Intent(this,ChooseListActivity.class);       //message from main activity to launch the second activity
            startActivityForResult(intent,0);
            return true;
        }
        if(item.getItemId() == R.id.setlocation)
        {
            Intent intent = new Intent(this,MapChooseCordinates.class);       //message from main activity to launch the second activity
            startActivityForResult(intent,1);
            return true;
        }
        if(item.getItemId() == R.id.chooseprefs){
            Intent intent = new Intent(this, MapPrefsActivity.class);
            startActivity(intent);
            return true;
        }
        return false;


    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean cyclemap = extras.getBoolean("com.example.cyclemap");
                if(cyclemap==true)
                {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                }
                else
                {
                    mv.getTileProvider().setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }

        if(requestCode==1){
            Bundle extras=intent.getExtras();
            double longitude = extras.getDouble("longitude");
            double latitude = extras.getDouble("latitude");
            mv.getController().setCenter(new GeoPoint(latitude, longitude));
        }
    }

    public void onStart(){
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()); //Creates preference manager
        double lat = Double.parseDouble(prefs.getString("lat", "50.9"));                                  //Takes in values from preferences.xml and converts to string
        double lon = Double.parseDouble(prefs.getString("lon", "-1.4"));                                  //Takes in values from preferences.xml and converts to string
        int zoom = Integer.parseInt(prefs.getString("Zoom", "14"));                                       //Takes in values from preferences.xml and converts to string
        String mapTypeCode = prefs.getString("map", "NONE");                                              //Takes in values from preferences.xml and arrays.xml
        if (mapTypeCode.equals("NM")){                                                                    //If it is set to the NM string in arrays.xml, change tilesource to normal map
            mv.getTileProvider().setTileSource(TileSourceFactory.MAPNIK);
        }
        if (mapTypeCode.equals("CM")){                                                                    //If it is set to the NM string in arrays.xml, change tilesource to cycle map
            mv.setTileSource(TileSourceFactory.CYCLEMAP);
        }
        mv.getController().setCenter(new GeoPoint(lat, lon));                                             //Set the geopoint to the appointed latitude and longitude
        mv.getController().setZoom(zoom);                                                                 //Set the zoom level
    }
}

