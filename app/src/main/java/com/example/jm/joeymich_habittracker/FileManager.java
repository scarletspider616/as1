package com.example.jm.joeymich_habittracker;

import android.content.Context;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.io.BufferedReader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

    public void addHabit(Habit habit) {
        this.habitList.add(habit);
        this.saveInFile();
    }

    public ArrayList<Habit> getHabitList() {

        return this.habitList;
    }

    // the following two methods were taken from lonelyTwitter class code
    private void loadFromFile() {
        try {
            FileInputStream fis = this.context.openFileInput(this.filename);
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
            FileOutputStream fos = this.context.openFileOutput(this.filename,
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

