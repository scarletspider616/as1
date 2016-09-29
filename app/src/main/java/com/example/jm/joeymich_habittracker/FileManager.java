package com.example.jm.joeymich_habittracker;

import android.content.Context;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.io.BufferedReader;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


/**
 * Created by jm on 2016-09-27.
 */

public class FileManager {
    private String filename = "save.dat";
    private ArrayList<Habit> habitList;
    private Gson converter;
    private FileOutputStream outputStream;
    private Context context;

    public FileManager (Context cntxt) {
        habitList = new ArrayList<Habit>();
        converter = new Gson();
        this.context = cntxt;

        // must load from file & rewrite
        loadFromFile();
    }

//    private void loadFromFile() {
//        /**
//            * This work, "loadFromFile," is a derivative of examples from
//            * "Saving Files" by "Delpes," used under Apache 2.0 by Joey-Michael Fallone.
//            * (Available here:
//            * https://developer.android.com/training/basics/data-storage/files.html)
//            *
//        */
//
//        try {
//            InputStream inRead = this.context.openFileInput(filename);
//            this.dumpData(inRead);
//            inRead.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    private void dumpData(InputStream inData) {
        /**
         * This common knowledge process is attributed to lonelyTwitter.
         *
         */
        ArrayList<String> strings = new ArrayList<String>();
        BufferedReader inBuff = new BufferedReader(new InputStreamReader(inData));
        try {
            String line = inBuff.readLine();
            while (line != null) {
                strings.add(line);
                line = inBuff.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // now update habitList
        for (String string: strings) {
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

        this.context.deleteFile(filename);
        OutputStream outWrite = null;
        try {
            outWrite = this.context.openFileOutput(filename, Context.MODE_PRIVATE);
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

    private ArrayList<String> loadFromFile() {
        ArrayList<String> strings = new ArrayList<String>();
        try {
            FileInputStream fis = this.context.openFileInput(this.filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                strings.add(line);
                line = in.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return strings;

    }

    private void updateList() {
        ArrayList<String> strings = this.loadFromFile();

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

