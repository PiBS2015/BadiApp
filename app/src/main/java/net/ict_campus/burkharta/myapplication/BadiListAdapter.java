package net.ict_campus.burkharta.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Burkharta on 20.05.2016.
 */
public class BadiListAdapter extends ArrayAdapter<BadiModel> {

    private static class ViewHolder{
        private TextView itemView;
        private Drawable pictureView;
    }
    private int viewRessource;

    public BadiListAdapter(Context context, int viewRessource, List<BadiModel> model){
        super(context, viewRessource, model);
        this.viewRessource = viewRessource;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(this.viewRessource, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            //viewHolder.itemView = tbd...
        }

        return convertView;
    }
}
