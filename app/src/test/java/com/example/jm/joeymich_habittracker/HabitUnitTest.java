package com.example.jm.joeymich_habittracker;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by jm on 2016-10-01.
 */

public class HabitUnitTest {
    @Test
    public void getDaysToCompleteOnTest() {
        Habit habit = new Habit("Test", 0);
        assertEquals(true, habit.getDaysToCompleteOnList()[0]);
    }
    @Test
    public void getMessageTest() {
        Habit habit = new Habit("Test", 0);
        assertEquals("Test", habit.getMessage());
    }
    @Test
    public void addCompletionsTest() {
        Habit habit = new Habit("Test", 0);
        Date date = new Date();
        habit.addCompletion(date);
        assertEquals(date, habit.getCompletes().get(0).getCompletionDate());
    }
    @Test
    public void addCompletionTest() {
        Habit habit = new Habit("Test", 0);
        Date date = new Date();
        habit.addCompletion(date);
        assertEquals(Integer.valueOf(1), habit.getNumberOfCompletes());
    }
    @Test
    public void deleteCompletionTest() {
        Habit habit = new Habit("Test", 0);
        habit.addCompletion(new Date());
        habit.addCompletion(new Date());
        habit.deleteCompletion(0);
        assertEquals(Integer.valueOf(1), habit.getNumberOfCompletes());
    }
//    @Test
//    public void


}
