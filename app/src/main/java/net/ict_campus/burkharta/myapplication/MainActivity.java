package net.ict_campus.burkharta.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    public static final String PACKAGE_KEY = "badi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BadiListModel badis = new BadiListModel(this);

        ListView badiView = (ListView) findViewById(R.id.badi_liste);
        badiView.setAdapter(new ArrayAdapter<BadiModel>(
                this,
                android.R.layout.simple_list_item_1,
                badis.getBadisAsList()
        ));

        badiView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView parent, View v, int position, long id){
                        Intent intent = new Intent(getApplicationContext(), BadiDetailsActivity.class);
                        BadiModel selected = (BadiModel) parent.getItemAtPosition(position);
                        Toast.makeText(getApplicationContext(), selected.toString(), Toast.LENGTH_LONG).show();
                        selected.ladeTemperaturen();
                        try {
                            intent.putExtra(PACKAGE_KEY, selected.getJSONPackage());
                            startActivity(intent);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                            //TODO: Errorhandling
                        }
                    }
                }
        );
    }
}
