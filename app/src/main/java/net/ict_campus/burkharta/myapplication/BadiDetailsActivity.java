package net.ict_campus.burkharta.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class BadiDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badi_details);

        Intent intent = getIntent();
        JSONObject payload = null;
        try {
            payload = new JSONObject(intent.getStringExtra(MainActivity.PACKAGE_KEY));
        }
        catch(JSONException e){
            Log.v(this.getLocalClassName(), e.toString());
        }

        TextView badiNameView = (TextView) findViewById(R.id.badiinfos);
        try {
            badiNameView.setText(payload.getString("name"));
        }
        catch(JSONException e){
            badiNameView.setText("Ups");
        }

        ListView badiView = (ListView) findViewById(R.id.badidetails);

        populateView(badiView, payload);

    }

    private void populateView(ListView target, JSONObject data){
        Iterator<String> keys = data.keys();

        ArrayAdapter<String> badiTemperatureAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1
        );

        while(keys.hasNext()){
            String key = keys.next();
            if(!key.equals(BadiModel.BADI_NAME_KEY)){
                try {
                    badiTemperatureAdapter.add(key + " : " + data.getDouble(key) + "C");
                }
                catch(JSONException e){
                    badiTemperatureAdapter.add(key + " : Error");
                }
            }
        }

        target.setAdapter(badiTemperatureAdapter);
    }
}
