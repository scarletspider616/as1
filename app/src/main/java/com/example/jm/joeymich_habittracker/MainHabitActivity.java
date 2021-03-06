package com.example.jm.joeymich_habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;

/**
 * This work, "MainHabitActivity," contains a derivative of
 * "Android - How to create clickable listview?" by "Delpes," a stackoverflow user,
 * used under CC-BY-SA by Joey-Michael Fallone.
 * (Available here:
 * http://stackoverflow.com/questions/13281197/android-how-to-create-clickable-listview)
 *
*/


// http://stackoverflow.com/questions/13281197/android-how-to-create-clickable-listview


public class MainHabitActivity extends AppCompatActivity {
    private String[] daysList;
    private Button newHabit;
    private ArrayAdapter<Habit> habitAdapter;
    private ListView displayHabits;
    private FileManager fileManager;
    private Integer day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_habit);
        // check the day of the week:

        fileManager = new FileManager(getApplicationContext());
        // ^ common knowledge attribution (see readme)
        // http://stackoverflow.com/questions/4721626/how-to-get-the-current-context

//        this.displayHabits();

        // http://stackoverflow.com/questions/5574673/what-is-the-easiest-way-to-get-the-current-day-of-the-week-in-android
        this.day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1; // 0 = Sunday

    }

    public void onResume() {
        super.onResume();
        this.displayHabits();
    }

    private void displayHabits() {
        // check if we have changed day.
        try {
            Intent rIntent = getIntent();
            if (rIntent.hasExtra("day")) {
                this.day = rIntent.getIntExtra("day", 0);
            }
        } catch (Exception e) {
            // no worries, we didn't change days
            this.day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        }

        TextView currDate = (TextView) findViewById(R.id.currDateText);
        currDate.setText(this.convertToDayString(day));
        displayHabits = (ListView) findViewById(R.id.habit_list);
        displayHabits.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                Intent nIntent = new Intent(v.getContext(), HabitDetailActivity.class);
                nIntent.putExtra("id", position);
                nIntent.putExtra("day", day);
                startActivity(nIntent);
            }
        });

        fileManager = new FileManager(getApplicationContext());
        ArrayList<Habit> currHabits = fileManager.getTodaysHabits(day);
        ArrayList<Habit> temp = fileManager.getHabitList();
        habitAdapter = new RecentColoredAdapter<Habit>(this,
                android.R.id.text1, currHabits, this.day);

        displayHabits.setAdapter(habitAdapter);
        // create some fake habits for testing

//        habitAdapter.notifyDataSetChanged();

        // create new habit button.
        newHabit = (Button) findViewById(R.id.newHabit);
        newHabit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent addHabit;
                addHabit = new Intent(v.getContext(), NewHabit.class);
                startActivity(addHabit);

            }
        });

    }

    public void changeDay(View v) {
        Intent intent = new Intent(v.getContext(), ChangeDay.class);
        startActivity(intent);
        finish();
    }

    private String convertToDayString(int dayOfWeek) {
        if (dayOfWeek == 0) {
            return "Sunday";
        }
        else if (dayOfWeek == 1) {
            return "Monday";
        }
        else if (dayOfWeek == 2) {
            return "Tuesday";
        }
        else if (dayOfWeek == 3) {
            return "Wednesday";
        }
        else if (dayOfWeek == 4) {
            return "Thursday";
        }
        else if (dayOfWeek == 5) {
            return "Friday";
        }
        else {
            return "Saturday";
        }
    }
}
