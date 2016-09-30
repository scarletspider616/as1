package com.example.jm.joeymich_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HabitDetailActivity extends AppCompatActivity {
    private int habitID;
    private Habit habit;

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
        FileManager fm = new FileManager(getApplicationContext());
        this.habit = fm.getHabit(this.habitID);

        TextView habitName = (TextView) findViewById(R.id.habitName);
        habitName.setText(this.habit.getMessage());

    }
}
