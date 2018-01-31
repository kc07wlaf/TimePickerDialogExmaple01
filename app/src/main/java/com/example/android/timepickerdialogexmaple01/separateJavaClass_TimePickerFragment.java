package com.example.android.timepickerdialogexmaple01;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by kcc on 26-Jan-18.
 */

public class separateJavaClass_TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, hourOfDay, minute,
                DateFormat.is24HourFormat(getActivity()));
        //this is Android Studio's original TimePickerDialog, which contains a bug-- it does not show
        //the spinner, which should be used in the THEME_HOLO_LIGHT and THEME_HOLO_Dark
        //other default themes include THEME_DEVICE_DEFAULT_DARK, THEME_DEVICE_DEFAULT_LIGHT, THEME_TRADITIONAL

        //return new TimePickerDialogFixedNougatSpinner(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, hour, minute,
        //        DateFormat.is24HourFormat(getActivity()));
        //the above is the TimePickerDialog with the fix
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        //int selectedHour = hourOfDay;
        //int selectedMinute = minute;
        String selectedAM_PM;

        if (hourOfDay<=11){selectedAM_PM="AM";}
        else {
            selectedAM_PM = "PM";
            if (hourOfDay>12){hourOfDay=hourOfDay-12;}
        }

        TextView editTextDisplayTime= getActivity().findViewById(R.id.textView_TimeDisplay);
        editTextDisplayTime.setText(Integer.toString(hourOfDay)+":"+Integer.toString(minute)+" "+selectedAM_PM);

        //TextView tv1=(EditText) getActivity().findViewById(R.id.textView2);
        //tv1.setText("Hour: "+view.getCurrentHour()+" Minute: "+view.getCurrentMinute());
        //getCurrentHour() is a TimePicker built-in method; deprecated in API23; use getHour() instead
        ///However, because I am using minimum API version number as 15, thus using getHour would
        //cause an error, though the error could be surpressed
        //tv1.setText("Hour: "+view.getHour()+" Minute: "+view.getMinute());
    }

}
