package com.example.jm.joeymich_habittracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.app.PendingIntent.getActivity;

// http://stackoverflow.com/questions/13281197/android-how-to-create-clickable-listview
public class MainHabitActivity extends AppCompatActivity {
    private String[] daysList;
    private Button newHabit;
    private ArrayList<Habit> habitList;
    private ArrayAdapter<Habit> habitAdapter;
    private Integer currDay;
    private ListView displayHabits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_habit);
        // check the day of the week:
        // http://stackoverflow.com/questions/5574673/what-is-the-easiest-way-to-get-the-current-day-of-the-week-in-android
        Integer day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1; // 0 = Sunday
        TextView currDate = (TextView) findViewById(R.id.currDateText);


        currDate.setText(this.convertToDayString(day));
        displayHabits = (ListView) findViewById(R.id.habit_list);
        habitList = new ArrayList<Habit> ();
        habitList.add(new Habit("Clean room", 0, 1, 2, 3, 4, 5, 6));
        try {
            Intent nIntent = getIntent();
            String message = nIntent.getStringExtra("description");
            habitList.add(new Habit(message, 0));

        } catch (Exception e) {
            //pass for now
        }
        habitAdapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, habitList);

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
