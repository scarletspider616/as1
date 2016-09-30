package com.example.jm.joeymich_habittracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HabitDetailActivity extends AppCompatActivity {
    private int habitID;
    private Habit habit;
    FileManager fm;

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
        this.fm = new FileManager(getApplicationContext());
        this.habit = fm.getHabit(this.habitID);

        TextView habitName = (TextView) findViewById(R.id.habitName);
        habitName.setText(this.habit.getMessage());

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
        this.fm.deleteHabit(habitID);
        finish();
    }
}
