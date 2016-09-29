package com.example.jm.joeymich_habittracker;

import android.app.Activity;
import android.content.Context;
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

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static android.app.PendingIntent.getActivity;

/**
 * This work, "MainHabitActivity," is a derivative of
 * "Android - How to create clickable listview?" by "Delpes," a stackoverflow user,
 * used under CC-BY-SA by Joey-Michael Fallone.
 * (Available here:
 * http://stackoverflow.com/questions/13281197/android-how-to-create-clickable-listview)
 *
*/
// http://stackoverflow.com/questions/13281197/android-how-to-create-clickable-listview


public class MainHabitActivity extends AppCompatActivity {
    private String filename = "save.dat";
    private ArrayList<Habit> habitList;
    private Gson converter;
    private FileOutputStream outputStream;

    private String[] daysList;
    private Button newHabit;
    private ArrayAdapter<Habit> habitAdapter;
    private Integer currDay;
    private ListView displayHabits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_habit);
        // check the day of the week:

//        fileManager = new FileManager(getApplicationContext());
        loadFromFile();
        this.converter = new Gson();
        // ^ common knowledge attribution (see readme)
        // http://stackoverflow.com/questions/4721626/how-to-get-the-current-context

        this.displayHabits();

    }

    private void displayHabits() {
        // http://stackoverflow.com/questions/5574673/what-is-the-easiest-way-to-get-the-current-day-of-the-week-in-android
        Integer day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1; // 0 = Sunday
        TextView currDate = (TextView) findViewById(R.id.currDateText);

        currDate.setText(this.convertToDayString(day));
        displayHabits = (ListView) findViewById(R.id.habit_list);

        try {
            loadFromFile();
            Intent nIntent = getIntent();
            String message = nIntent.getStringExtra("description");
            if (message != null) {
                habitList.add(new Habit(message, 0));
                saveInFile();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        ArrayList<Habit> temp = this.habitLIst();

        habitAdapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, this.habitList);

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


    public void addHabit(Habit habit) {
        this.habitList.add(habit);
        this.saveInFile();
    }

    public ArrayList<Habit> getHabitList() {
//        return this.habitList;
//        String size = new Integer(habitList.size()).toString();
//        habitList.clear();
//        habitList.add(new Habit(size, 0));

        return this.habitList;
    }
    

    // the following two methods were taken from lonelyTwitter class code
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(this.filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit>>() {
            }.getType();

            this.habitList = gson.fromJson(in, listType);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            this.habitList = new ArrayList<Habit>();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(this.filename,
                    0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(this.habitList, out);
            out.flush();

            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
