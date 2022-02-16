package com.example.applauncher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<App> {
    Context appContext;
    List<App> apps;

    public MyAdapter(Context context, List<App> apps) {
        super(context, R.layout.applist_item, apps);
        this.appContext = context;
        this.apps = apps;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        App thisApp = apps.get(position);
        LayoutInflater appInflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = appInflater.inflate(R.layout.applist_item, parent, false);
        }

        ImageView appIcon = convertView.findViewById(R.id.appIcon);
        TextView appName = convertView.findViewById(R.id.appName);

        appIcon.setImageDrawable(thisApp.appIcon);
        appName.setText(thisApp.appName);

        return convertView;
    }
}
