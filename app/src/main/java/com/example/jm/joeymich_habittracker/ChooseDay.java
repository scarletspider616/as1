package com.example.jm.joeymich_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
http://theopentutorials.com/tutorials/android/listview/android-multiple-selection-listview/
 */

public class ChooseDay extends AppCompatActivity {

    private ListView daysList;
    private String[] dayStrings;
    private ArrayAdapter<String> dayAdapter;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_day);
        Intent saveDescription = getIntent();
        description = saveDescription.getStringExtra("description");

        TextView message = (TextView) findViewById(R.id.chooseDayMessage);
        message.setText(R.string.chooseDays);

        dayStrings = new String[7];
        this.populateStrings(dayStrings);
        String description = saveDescription.getStringExtra("description");
        daysList = (ListView) findViewById(R.id.days_list);
        daysList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        dayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1, this.dayStrings);
        daysList.setAdapter(dayAdapter);



    }

    public void onClick(View v) {
        // http://theopentutorials.com/tutorials/android/listview/android-multiple-selection-listview/
        SparseBooleanArray checked = daysList.getCheckedItemPositions();
        ArrayList<Integer> daysSelected = new ArrayList<Integer>();
        for (int i = 0; i < checked.size(); i++) {
            daysSelected.add(i);
        }
        this.headBackToMainScreen(daysSelected);
    }

    private void populateStrings(String[] inStrings) {
        inStrings[0] = "Sunday";
        inStrings[1] = "Monday";
        inStrings[2] = "Tuesday";
        inStrings[3] = "Wednesday";
        inStrings[4] = "Thursday";
        inStrings[5] = "Friday";
        inStrings[6] = "Saturday";
    }

    private void headBackToMainScreen(ArrayList<Integer> daysSelected) {
        Intent nIntent = new Intent(this, MainHabitActivity.class);
        nIntent.putIntegerArrayListExtra("days", daysSelected);
        nIntent.putExtra("description", description);
    }
}
