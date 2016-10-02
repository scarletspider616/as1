package com.example.jm.joeymich_habittracker;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.io.BufferedReader;

import com.google.gson.reflect.TypeToken;


import java.util.Date;


/**
 * Created by jm on 2016-09-27.
 */

public class FileManager {
    private String filename = "save.dat";
    private ArrayList<Habit> habitList;
    private FileOutputStream outputStream;
    private Context context;

    public FileManager(Context cntxt) {
        habitList = new ArrayList<Habit>();
        this.context = cntxt;

        // must load from file & rewrite
        loadFromFile();
    }



    public void addHabit(Habit habit) {
        this.habitList.add(habit);
        this.saveInFile();
    }

    public ArrayList<Habit> getHabitList() {

        return this.habitList;
    }

    public Habit getHabit(String message) {
        // for now take habit message as key, even though ideally generate UUID
        for (Habit habit : this.habitList) {
            if (habit.getMessage().equals(message)) {
                return habit;
            }
        }
        return null;
    }

    private Habit getHabit(Integer position) {
        return this.habitList.get(position);
    }

    public Habit getHabit(int position, int day) {
        int actualPosition = this.habitList.indexOf(getTodaysHabits(day).get(position));
        return getHabit(actualPosition);
    }

    public void deleteHabit(int position, int day) {
        this.habitList.remove(this.habitList.indexOf(getTodaysHabits(day).get(position)));
        saveInFile();
    }

    public void deleteHabit(String message) {
        this.habitList.remove(message);
        saveInFile();
    }

    private void cleanup() {
        for (Habit habit : habitList) {
            if (habit.getMessage() == null) {
                this.deleteHabit(habit.getMessage());
            } else if (habit.getMessage().equals("")) {
                this.deleteHabit(habit.getMessage());
            } else if (habit.getMessage().equals("null")) {
                this.deleteHabit(habit.getMessage());
            }
        }
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
            this.cleanup();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            this.habitList = new ArrayList<Habit>();
            this.cleanup();
        }
    }

    private void saveInFile() {
        this.cleanup();
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

    public void deleteCompletion(int id, int day, int compID) {
        this.habitList.get(getActualId(id, day)).deleteCompletion(compID);
        saveInFile();
    }

    public int getActualId(int id, int day) {
        return this.habitList.indexOf(getTodaysHabits(day).get(id));
    }

    public void addCompletion(int id, int day, Date date) {
        this.habitList.get(getActualId(id, day)).addCompletion(date);
        saveInFile();
    }

    public ArrayList<Habit> getTodaysHabits(int day) {
        ArrayList<Habit> newList = new ArrayList<Habit>();
        for (Habit habit: this.habitList) {
            if (habit.toBeCompletedOn(day).equals(Boolean.TRUE)) {
                newList.add(habit);
            }
        }
        return newList;
    }

    public void changeHabitCreationDate(int id, int day, Date date) {
        this.habitList.get(getActualId(id, day)).changeDateEntered(date);
        saveInFile();
    }
}