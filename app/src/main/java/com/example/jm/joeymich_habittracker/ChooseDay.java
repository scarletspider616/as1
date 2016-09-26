package com.example.jm.joeymich_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseDay extends AppCompatActivity {

    private ListView daysList;
    private String[] dayStrings;
    private ArrayAdapter<String> dayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_day);
        Intent saveDescription = getIntent();
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

    private void populateStrings(String[] inStrings) {
        inStrings[0] = "Sunday";
        inStrings[1] = "Monday";
        inStrings[2] = "Tuesday";
        inStrings[3] = "Wednesday";
        inStrings[4] = "Thursday";
        inStrings[5] = "Friday";
        inStrings[6] = "Saturday";
    }
}
