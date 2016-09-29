package com.example.jm.joeymich_habittracker;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v7.app.AlertDialog;
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

/**
 * This work, "MainHabitActivity," is a derivative of
 * "Android - How to create clickable listview?" by "Delpes," a stackoverflow user,
 * used under CC-BY-SA by Joey-Michael Fallone.
 * (Available here:
 * http://stackoverflow.com/questions/13281197/android-how-to-create-clickable-listview)
 *
*/


// http://stackoverflow.com/questions/13281197/android-how-to-create-clickable-listview


public class MainHabitActivity extends AppCompatActivity
        implements ClickListItemFragment.ClickListDialogListener{
    private String[] daysList;
    private Button newHabit;
    private ArrayAdapter<Habit> habitAdapter;
    private Integer currDay;
    private ListView displayHabits;
    private FileManager fileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_habit);
        // check the day of the week:

        fileManager = new FileManager(getApplicationContext());
        // ^ common knowledge attribution (see readme)
        // http://stackoverflow.com/questions/4721626/how-to-get-the-current-context

        this.displayHabits();

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        ClickListItemFragment dialog = new ClickListItemFragment();
        FragmentManager fm = getFragmentManager();
        finish();
        System.exit(0);
        dialog.show(fm, "onItemClick");
    }

    private void displayHabits() {
        // http://stackoverflow.com/questions/5574673/what-is-the-easiest-way-to-get-the-current-day-of-the-week-in-android
        Integer day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1; // 0 = Sunday
        TextView currDate = (TextView) findViewById(R.id.currDateText);

        currDate.setText(this.convertToDayString(day));
        displayHabits = (ListView) findViewById(R.id.habit_list);
        displayHabits.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                ClickListItemFragment dialog = new ClickListItemFragment();
                FragmentManager fm = getFragmentManager();
                dialog.show(fm, "onItemClick");
            }
        });

        try {
            fileManager = new FileManager(getApplicationContext());
            Intent nIntent = getIntent();
            String message = nIntent.getStringExtra("description");
            fileManager.addHabit(new Habit(message, 0));

        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Habit> temp = fileManager.getHabitList();

        habitAdapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, fileManager.getHabitList());

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
    // api taken from https://developer.android.com/guide/topics/ui/dialogs.html
    // (should it be licensed here again???)
    @Override
    public void onDetailClick(DialogFragment dialog) {

    }

    @Override
    public void onCancelClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        
    }

    @Override
    public void onCompletionClick(DialogFragment dialog) {
        // fill this
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
