package com.example.jm.joeymich_habittracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by jm on 2016-09-26.
 * https://developer.android.com/guide/topics/ui/dialogs.html
 */

public class ChooseDayDialog extends DialogFragment {

    private Boolean[] daysList = new Boolean[7];

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {
        final ArrayList mSelectedItems = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Please choose day(s) to complete on").setMultiChoiceItems(
                R.array.days_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            // If the user selected the item...
                            mSelectedItems.add(which);
                        }
                        else if (mSelectedItems.contains(which)) {
                            mSelectedItems.remove(Integer.valueOf(which));
                        }
                    }
                })
                .setPositiveButton(R.string.button_send, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                      // leave for now...
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // do somehting.
                    }
                });
        return builder.create();


    }
}
