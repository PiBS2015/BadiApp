package net.ict_campus.burkharta.myapplication;

import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonWriter;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by Burkharta on 19.05.2016.
 */
public class BadiModel {
    public static final String BADI_NAME_KEY = "name";
    AsyncTask<String, String, String> tempTask;
    private Context appContext;

    private String badiid;
    private String name;

    private HashMap<String, Double> beckenTemperaturen;

    public BadiModel(Context context, String badiid, String name){
        this.appContext = context;
        beckenTemperaturen = new HashMap<>();

        this.badiid = badiid;
        this.name = name;
    }

    public String getBadiid(){
        return badiid;
    }

    public void ladeTemperaturen(){
        String temperaturQuelle = appContext.getString(R.string.badi_url_prefix) + badiid;

        tempTask = new BadiLoader(beckenTemperaturen);
        tempTask.execute(temperaturQuelle);

    }

    private void waitForData() throws Exception{

        if(tempTask == null){
            throw new Exception(appContext.getString(R.string.badi_load_error));
        }
        else if(tempTask.isCancelled()) {
            return;
        }
        else{
            try {
                tempTask.get(10, TimeUnit.SECONDS);
            }
            catch(Exception e){
                throw new Exception(appContext.getString(R.string.badi_load_error));
            }
        }
    }

    public String getJSONPackage() throws JSONException, Exception {
        waitForData();
        JSONObject payLoad = new JSONObject(beckenTemperaturen);
        Log.v(this.getClass().getCanonicalName(), beckenTemperaturen.size()+"");
        payLoad.put(BADI_NAME_KEY, name);
        Log.v(this.getClass().getCanonicalName(), payLoad.toString());
        return payLoad.toString();
    }

    @Override
    public String toString(){
        return name;
    }
}
