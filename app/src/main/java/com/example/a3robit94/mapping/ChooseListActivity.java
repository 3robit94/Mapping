package com.example.a3robit94.mapping;
/**
 * Created by 3robit94 on 20/02/2017.
 */
import android.app.ListActivity;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ListView;
import android.content.Context;


public class ChooseListActivity extends ListActivity {
    String[] data, details;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new String[]{"Regular Map", "Cycle Map"};
        details = new String[]{"A regular map view","A view for cyclists"};
        MyAdapter adapter = new MyAdapter();
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView lv, View view, int index, long id) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        boolean cyclemap = false;
        if(index == 1){
            cyclemap = true;
        }
        bundle.putBoolean("com.example.cyclemap",cyclemap);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }

    public class MyAdapter extends ArrayAdapter<String>
    {
        public MyAdapter()
        {
            // We have to use ExampleListActivity.this to refer to the outer class (the activity)
            super(ChooseListActivity.this, android.R.layout.simple_list_item_1, data);
        }

        public View getView(int index, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.mapentry, parent, false);
            TextView title = (TextView)view.findViewById(R.id.map_name), detail = (TextView)view.findViewById(R.id.map_desc);
            title.setText(data[index]);
            detail.setText(details[index]);
            return view;
        }
    }
}