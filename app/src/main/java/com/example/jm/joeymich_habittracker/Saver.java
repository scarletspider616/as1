package com.example.jm.joeymich_habittracker;

/**
 * Created by jm on 2016-09-26.
 */

public class Saver {
    private String filename = "sav.dat";

    public Saver () {
        // https://developer.android.com/training/basics/data-storage/files.html
        File save = new File(context.getFilesDir(), filename);
    }
}
