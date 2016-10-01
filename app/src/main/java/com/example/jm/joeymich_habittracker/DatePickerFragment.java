package com.example.jm.joeymich_habittracker;

 import android.app.DatePickerDialog;
 import android.app.Dialog;
 import android.app.DialogFragment;
 import android.content.DialogInterface;
 import android.content.Intent;
 import android.os.Bundle;
 import android.support.v7.app.AlertDialog;
 import android.widget.DatePicker;

 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;

/**
 * Created by jm on 2016-09-30.
 *
 * The following work, 'DatePickerFragment" is an derivative of Android Developer Tutorial code
 * article "Pickers," and is used under Apache 2.0 by Joey-Michael Fallone.
  *
  * Available here:
  * https://developer.android.com/guide/topics/ui/controls/pickers.html
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Bundle infoPkg = this.getArguments();
        FileManager fm = new FileManager(view.getContext());

        fm.changeHabitCreationDate(infoPkg.getInt("id"), infoPkg.getInt("currDay"),
                createDate(year, month, day));

//        Intent intent = new Intent(view.getContext(), HabitDetailActivity.class);
//        startActivity(intent);

        getActivity().finish();
    }

    private Date createDate(Integer year, Integer month, Integer day) {
        String sYear = year.toString();
        String sMonth = null;
        String sDay = null;

        if (Integer.valueOf(month+1).toString().length() < 2) {
            sMonth = "0" + (Integer.valueOf(month+1)).toString();
        }
        else {
            sMonth = (Integer.valueOf(month+1)).toString();
        }

        if (day.toString().length() < 2) {
            sDay = "0" + day.toString();
        }
        else {
            sDay = day.toString();
        }


        SimpleDateFormat pattern = new SimpleDateFormat("yyyyMMdd");
        try {
            return pattern.parse(sYear + sMonth + sDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public void finalDialog(String message) {
//        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                switch (which) {
//                    case DialogInterface.BUTTON_POSITIVE:
//                        break;
//                }
//            }
//        };
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage(message).setPositiveButton("OK",
//                dialogClickListener).show();
//    }

}