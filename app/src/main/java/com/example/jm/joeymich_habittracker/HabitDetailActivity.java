package com.example.jm.joeymich_habittracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class HabitDetailActivity extends AppCompatActivity {
    /**
     * This work, "MainHabitActivity," contains a derivative
     * "Android - How to create clickable listview?" by "Delpes," a stackoverflow user,
     * used under CC-BY-SA by Joey-Michael Fallone.
     * (Available here:
     * http://stackoverflow.com/questions/13281197/android-how-to-create-clickable-listview)
     *
     */
    private int habitID;
    private Habit habit;
    FileManager fm;
    private int day;
    private ListView displayCompletions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail);
    }
    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        this.habitID = intent.getIntExtra("id", 0);
        this.day = intent.getIntExtra("day", 0);
        this.fm = new FileManager(getApplicationContext());
        this.habit = fm.getHabit(this.habitID, this.day);

        // information panels
        TextView habitName = (TextView) findViewById(R.id.habitName);
        habitName.setText(this.habit.getMessage());
        TextView createdOn = (TextView) findViewById(R.id.createdOn);
        createdOn.setText(this.habit.getDateEntered().toString());

        // completions list
        displayCompletions = (ListView) findViewById(R.id.completionList);
        displayCompletions.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                Intent nIntent = new Intent(v.getContext(), HabitDetailActivity.class);
                nIntent.putExtra("id", position);
                nIntent.putExtra("day", day);
                startActivity(nIntent);
            }
        });
        ArrayAdapter<Completion> adapter = new ArrayAdapter<Completion>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, this.habit.getCompletes());
        displayCompletions.setAdapter(adapter);


    }

    public void deleteHabit(View v) {
        /**
         * The following dialog contained in "deleteHabit" is a derivative of an answer to
         * "How to display a Yes/No dialog box on Android?" by "Steve Haley," a user on
         * stack overflow, used under CC-BY-SA by Joey-Michael Fallone.
         * Available here:
         * http://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
         */
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        deleteHabit();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // do nada
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    private void deleteHabit() {
        this.fm.deleteHabit(habitID, day);
        finish();
    }

    public void addCompletion(View v) {
        fm.addCompletion(habitID, day, new Date());
        /**
         * The following dialog contained in "addCompletion" is a derivative of an answer to
         * "How to display a Yes/No dialog box on Android?" by "Steve Haley," a user on
         * stack overflow, used under CC-BY-SA by Joey-Michael Fallone.
         * Available here:
         * http://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
         */
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage("Added Completion.").setPositiveButton("OK", dialogClickListener).show();
    }

    public void changeStartDate(View v) {
        Intent newIntent = new Intent(this, ChooseDate.class);
        newIntent.putExtra("id", this.habitID);
        startActivity(newIntent);
        finish();
    }
}
