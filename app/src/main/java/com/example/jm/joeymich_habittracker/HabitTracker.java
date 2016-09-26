package com.example.jm.joeymich_habittracker;

/**
 * Created by jm on 2016-09-26.
 */

public class HabitTracker extends Application {
    private Saver saver;

    public HabitTracker {
        super();
        saver = new Saver();
    }
    public Saver getSaver() {
        return this.saver;
    }
}
