package com.example.jm.joeymich_habittracker;

import java.util.Date;

/**
 * Created by jm on 2016-09-23.
 */

public class Completion {

    // using aggregation
    private Date completionDate;

    public Completion (Date date) {
        this.completionDate = date;
    }
    public Date getCompletionDate () {
        return this.completionDate;
    }
}
