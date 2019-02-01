package com.markpaveszka.pavlosdrinkinggame;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Acer on 2018. 01. 05..
 */

public class ListAdapater extends ArrayAdapter<forTheList> {
    Context context;
    int layoutResourceId;
    ArrayList<forTheList> data = null;

    public ListAdapater (Context context, int layoutResourceId, ArrayList<forTheList> data){
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ListHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ListHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.textTitleTextView);

            row.setTag(holder);
        }
        else
        {
            holder = (ListHolder)row.getTag();
        }

        forTheList ftheList = data.get(position);
        holder.txtTitle.setText(ftheList.title);


        return row;
    }

    static class ListHolder
    {

        TextView txtTitle;
    }
}
