package com.example.jm.joeymich_habittracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jm on 2016-09-24.
 */

public class Habit {
    private Date dateEntered;
    private String name;
    private ArrayList<Boolean> daysToCompleteOn; // sunday = 0, saturday = 6 (index)
    private ArrayList<Completion> completes;

    public Habit (Date date, String name, Integer... toCompleteOn) throws dayNotInWeekException {
        this.dateEntered = date;
        this.name = name;
        completes = new ArrayList<Completion>();
        this.populateDaysToCompleteOn(this.daysToCompleteOn);

        for (Integer day: toCompleteOn) {
            // make sure the day is in the working week
            if (day < 0) {
                throw new dayNotInWeekException("This day is not in a working week!");
            }
            else if (day > 6) {
                throw new dayNotInWeekException("This day is not in a working week!");
            }
            this.daysToCompleteOn.set(day, true);
        }
    }

    private void populateDaysToCompleteOn(ArrayList<Boolean> daysList) {
        for (int i = 0; i < 7; i++) {
            daysList.add(false);
        }
    }
    // setters
    public void addDaysToCompleteOn (Integer... toCompleteOn) {
        for (Integer day: toCompleteOn) {

        }
    }
    public void changeMessage (String name) {
        this.name = name;
    }
    public void changeDateEntered (Date date) {
        this.dateEntered = date;
    }
    public void addCompletion (Date date) {
        completes.add(new Completion (date));
    }

    // getters
    public String getMessage () {
        return this.name;
    }
    public Date getDateEntered () {
        return this.dateEntered;
    }
    public ArrayList<Boolean> getDaysToCompleteOnList () {
        return this.daysToCompleteOn;
    }
    public ArrayList<Completion> getCompletes () {
        return this.completes;
    }

}
