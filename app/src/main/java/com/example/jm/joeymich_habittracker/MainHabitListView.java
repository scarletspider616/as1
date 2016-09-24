package com.example.jm.joeymich_habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainHabitListView extends AppCompatActivity {

    // set up some fields
    private ArrayAdapter<Habit> adapter;
    private ArrayList<Habit> habitList = new habitList<Habit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_habit_list_view);
    }
}
