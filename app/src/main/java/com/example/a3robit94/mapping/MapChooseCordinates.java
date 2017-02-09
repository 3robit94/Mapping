package com.example.a3robit94.mapping;

/**
 * Created by 3robit94 on 09/02/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

import org.osmdroid.util.GeoPoint;

public class MapChooseCordinates extends Activity implements View.OnClickListener {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coord);
        Button btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        EditText longitudeEdit = (EditText) findViewById(R.id.longitudeEdit);
        EditText latitudeEdit = (EditText) findViewById(R.id.latitudeEdit);
        double longitude = Double.parseDouble(longitudeEdit.getText().toString());
        double latitude = Double.parseDouble(latitudeEdit.getText().toString());
        bundle.putDouble("longitude", longitude);
        bundle.putDouble("latitude", latitude);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}
