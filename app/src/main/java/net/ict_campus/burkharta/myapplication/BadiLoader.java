package net.ict_campus.burkharta.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Burkharta on 19.05.2016.
 */
public class BadiLoader extends AsyncTask<String, String, String> {
    private HashMap<String, Double> beckenTemperaturen;

    public BadiLoader(HashMap<String, Double> beckenTemperaturen){
        this.beckenTemperaturen = beckenTemperaturen;
    }

    @Override
    protected  String doInBackground(String... badi) {
        StringBuilder message = new StringBuilder();
        HttpURLConnection urlConnection = null;
        URL url = null;
        try {
            url = new URL(badi[0]);
        }
        catch(MalformedURLException e){
            Log.v("URL", e.toString());
            return "";
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;

            while ((line = reader.readLine()) != null) {
                message.append(line);
            }
        }
        catch(IOException e){
            Log.v("HTTP", e.toString());
        }
        return message.toString();
    }

    protected void onPostExecute(String result){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(result);
            JSONObject becken = jsonObject.getJSONObject("becken");
            Iterator<String> keys = becken.keys();
            while(keys.hasNext()){
                String key = keys.next();
                JSONObject subObject = becken.getJSONObject(key);
                String name = subObject.getString("beckenname");
                Double temp = Double.parseDouble(subObject.getString("temp"));
                beckenTemperaturen.put(name, temp);
                Log.v(this.getClass().getCanonicalName(), beckenTemperaturen.size()+"");
            }
        }
        catch(JSONException e){
            Log.v("PARSE MSG", result);
        }
    }
}
