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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

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
        this.habitList = new ArrayList<Habit>();
        converter = new Gson();
        loadFromFile();
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
            Intent nIntent = getIntent();
            String message = nIntent.getStringExtra("description");
            if (message != null) {
                habitList.add(new Habit(message, 0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        updateFile();
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


    private void loadFromFile() {
        /**
         * This work, "loadFromFile," is a derivative of examples from
         * "Saving Files" by "Delpes," used under Apache 2.0 by Joey-Michael Fallone.
         * (Available here:
         * https://developer.android.com/training/basics/data-storage/files.html)
         *
         */

        try {
            InputStream inRead = openFileInput(filename);
            this.dumpData(inRead);
            inRead.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void dumpData(InputStream inData) {
        /**
         * This work, "dumpData," is a derivative of
         * "Read String line by line in Java" by "notnoop," and edited by "gregko, "
         * both stack overflow users, used under CC-BY-SA by Joey-Michael Fallone.
         * (Available here:
         * http://stackoverflow.com/questions/1096621/read-string-line-by-line-in-java)
         *
         */

        // THIS IS THE BUG I FOUND THE DAMN BUG ITS HERE BATTERY ABOUT TO DIE
        ArrayList<String> strings = new ArrayList<String>();
        Scanner scanner = new Scanner(new InputStreamReader(inData));
        while (scanner.hasNextLine()) {
            strings.add(scanner.nextLine());
        }
        scanner.close();

        // now update habitList
        for (String string: strings) {
            Habit temp = converter.fromJson(string, Habit.class);
            this.habitList.add(converter.fromJson(string, Habit.class));
        }
    }

    private void updateFile() {
        /**
         * This work, "updateFile," is a derivative of examples from
         * "Saving Files" by "Delpes," used under Apache 2.0 by Joey-Michael Fallone.
         * (Available here:
         * https://developer.android.com/training/basics/data-storage/files.html)
         *
         */

        deleteFile(filename);
        OutputStream outWrite = null;
        try {
            outWrite = openFileOutput(filename, Context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Habit habit: this.habitList) {
            String tempConverted = converter.toJson(habit);
            try {
                outWrite.write(tempConverted.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            outWrite.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addHabit(Habit habit) {
        this.habitList.add(habit);
        this.updateFile();
    }

    public ArrayList<Habit> getHabitList() {
//        return this.habitList;
//        String size = new Integer(habitList.size()).toString();
//        habitList.clear();
//        habitList.add(new Habit(size, 0));

        return this.habitList;
    }

//    public ArrayList<Habit> getSaveString() {
//        String result = "";
//        try {
//            InputStream inRead = this.context.openFileInput(filename);
//            result = dumpData(inRead);
//        } catch (Exception e) {
//            // pass
//        }
//        this.habitList = new ArrayList<Habit>();
//        this.habitList.add(new Habit(result, 0));
//        return this.habitList;
//    }

}
