package com.example.jm.joeymich_habittracker;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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

    private void loadFromFile() {
        /**
            * This work, "loadFromFile," is a derivative of examples from
            * "Saving Files" by "Delpes," used under Apache 2.0 by Joey-Michael Fallone.
            * (Available here:
            * https://developer.android.com/training/basics/data-storage/files.html)
            *
        */

        try {
            InputStream inRead = this.context.openFileInput(filename);

            String data = this.dumpData(inRead);
            this.populateHabits(data);

            inRead.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String dumpData(InputStream inData) {
        String dump = "";
        try {
            while (inData.available() > 0) {
                try {
                    dump = dump + inData.read();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dump;
    }

    private void updateFile() {
        /**
             * This work, "updateFile," is a derivative of examples from
             * "Saving Files" by "Delpes," used under Apache 2.0 by Joey-Michael Fallone.
             * (Available here:
             * https://developer.android.com/training/basics/data-storage/files.html)
             *
         */
        OutputStream outWrite = null;
        try {
            outWrite = this.context.openFileOutput(filename, Context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.context.deleteFile(filename);
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

    private void populateHabits(String data) {
        /**
         * This work, "populateHabits," is a derivative of
         * "Read String line by line in Java" by "notnoop," and edited by "gregko, "
         * both stack overflow users, used under CC-BY-SA by Joey-Michael Fallone.
         * (Available here:
         * http://stackoverflow.com/questions/1096621/read-string-line-by-line-in-java)
         *
         */
        // wish this was python...
        ArrayList<String> strings = new ArrayList<String>();
        Scanner scanner = new Scanner(data);
        while (scanner.hasNextLine()) {
            strings.add(scanner.nextLine());
        }
        scanner.close();

        // now update habitList
        for (String string:strings) {
            this.habitList.add(converter.fromJson(string, Habit.class));
        }
    }

    public ArrayList<Habit> getHabitList() {
        return this.habitList;
    }

}

