package com.example.jm.joeymich_habittracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jm on 2016-09-24.
 */

public class Habit {
    private Date dateEntered;
    private String name;
    private Boolean [] daysToCompleteOn;
    private ArrayList<Completion> completes;

    public Habit (String name, Integer... toCompleteOn) {
        this.dateEntered = new Date();
        this.name = name;
        completes = new ArrayList<Completion>();
        this.daysToCompleteOn = new Boolean[7];

        this.populateDaysToCompleteOn(this.daysToCompleteOn);

        for (int i = 0; i < toCompleteOn.length; i++) {
            this.daysToCompleteOn[toCompleteOn[i]] = Boolean.TRUE;
        }
    }
    public Habit (String name, ArrayList<Integer> toCompleteOn) {
        this.dateEntered = new Date();
        this.name = name;
        completes = new ArrayList<Completion>();
        this.daysToCompleteOn = new Boolean[7];

        this.populateDaysToCompleteOn(this.daysToCompleteOn);

        for (Integer day:toCompleteOn) {
            this.daysToCompleteOn[day] = Boolean.TRUE;
        }
    }

    private void populateDaysToCompleteOn(Boolean [] daysList) {
        for (int i = 0; i < 7; i++) {
            daysList[i] = Boolean.FALSE;
        }
    }
    // setters
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
    public Boolean[] getDaysToCompleteOnList () {
        return this.daysToCompleteOn;
    }
    public ArrayList<Completion> getCompletes () {
        return this.completes;
    }

    @Override
    public String toString() {
        return name;
    }
    public Boolean toBeCompletedOn(int day) {
        return this.daysToCompleteOn[day];
    }


}
