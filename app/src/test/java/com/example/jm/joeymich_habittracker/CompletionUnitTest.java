package com.example.jm.joeymich_habittracker;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jm on 2016-10-01.
 */

public class CompletionUnitTest {
    @Test
    public void getCompletionDate() {
        Date date = new Date();
        Completion completion = new Completion(date);
        assertEquals(date, completion.getCompletionDate());
    }
}
