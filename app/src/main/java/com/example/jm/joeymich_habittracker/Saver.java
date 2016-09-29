package com.example.jm.joeymich_habittracker;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;



/**
 * Created by jm on 2016-09-27.
 */

public class Saver {
    private String filename = "save.dat";
    private ArrayList<Habit> habitList;
    private Gson converter;
    private FileOutputStream outputStream;
    private Context context;

    public Saver (Context cntxt) {
        habitList = new ArrayList<Habit>();
        converter = new Gson();
        this.context = cntxt;

        // must load from file & rewrite
        updateData();
    }

    private void updateData() {
        this.habitList = this.loadFromFile();
    }

    private ArrayList<Habit> loadFromFile() {
        /**
            * This work, "loadFromFile," is a derivative of examples from
            * "Saving Files" by "Delpes," used under Apache 2.0 by Joey-Michael Fallone.
            * (Available here:
            * https://developer.android.com/training/basics/data-storage/files.html)
            *
        */

        try {
            InputStream inRead = this.context.openFileInput(filename);
            
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addHabit(Habit habit) {
        this.habitList.add(habit);
        updateData();
    }


}

