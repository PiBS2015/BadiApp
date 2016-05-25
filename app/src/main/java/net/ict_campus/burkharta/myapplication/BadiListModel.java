package net.ict_campus.burkharta.myapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Burkharta on 19.05.2016.
 */
public class BadiListModel {

    private final ArrayList<BadiModel> badis;

    public BadiListModel(Context appContext){
        badis = new ArrayList<>();
        badis.add(new BadiModel(
                appContext,
                appContext.getString(R.string.badi_grenchen_id),
                appContext.getString(R.string.badi_grenchen_name))
        );
        badis.add(new BadiModel(
                appContext,
                appContext.getString(R.string.badi_solothurn_id),
                appContext.getString(R.string.badi_solothurn_name))
        );
        badis.add(new BadiModel(
                appContext,
                appContext.getString(R.string.badi_aarberg_id),
                appContext.getString(R.string.badi_aarberg_name))
        );
    }

    public List<BadiModel> getBadisAsList(){
        return badis;
    }
}
