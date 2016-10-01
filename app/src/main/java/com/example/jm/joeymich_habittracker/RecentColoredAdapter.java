package com.example.jm.joeymich_habittracker;

import android.content.ClipData.Item;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by jm on 2016-10-01.
 *
 * The following contains derivaties of the following two works from stackexchange:
 * Android ListView Text Color by Shardul
 * Custom Adapter for List View by Rakhita edited by Anirudh
 *
 */

public class RecentColoredAdapter<A> extends ArrayAdapter<A> {

    private Context context;
    private FileManager fm;
    private int day;
    private List<A> list;

    public RecentColoredAdapter(Context context, int id, List<A> objs, int day) {
        super(context, id, objs);
        this.context = context;
        fm = new FileManager(context);
        this.day = day;
        this.list = objs;
    }

    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = View.inflate(context, android.R.layout.simple_list_item_1, null);
        TextView textView=(TextView) view.findViewById(android.R.id.text1);
        if (fm.getHabit(position, day).completedRecently(new Date())) {
//            textView.setTextColor(Color.GREEN);
            textView.setText(this.list.get(position).toString());
        }
        else {
            textView.setTextColor(Color.RED);
            textView.setText(this.list.get(position).toString());
        }
        return view;
    }
}
