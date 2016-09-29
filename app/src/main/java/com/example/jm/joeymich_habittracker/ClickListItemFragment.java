package com.example.jm.joeymich_habittracker;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This work, "ClickListItemFragment," contains derivatives of
 * "Dialogs" Android developer tutorial example code
 * used under CC-BY-SA by Joey-Michael Fallone.
 * (Available here:
 * https://developer.android.com/guide/topics/ui/dialogs.html)
 *
 */

public class ClickListItemFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.prompt)
                .setPositiveButton(R.string.details, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // new activity needs to be started here
                    }
                }).setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // do something
                    }
                })
                .setNegativeButton(R.string.completion, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}