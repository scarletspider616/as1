package com.example.jm.joeymich_habittracker;

/**
 * Created by jm on 2016-09-23.
 */

public class dayNotInWeekException extends Exception {
    public dayNotInWeekException (String message) {
        super(message);
    }
}
