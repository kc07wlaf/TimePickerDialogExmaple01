package com.example.android.timepickerdialogexmaple01;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

//sample code adopted from:
//https://developer.android.com/guide/topics/ui/controls/pickers.html
//declared (separateJavaClass_)TimePickerFragment as an inner class, rather than a separate Java class file


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showTimePickerDialog(View v) {
        android.app.DialogFragment newFragment = new TimePickerFragment();
        //newFragment.show(getSupportFragmentManager(), "timePicker");//this is for android.support.v4.app.FragmentManager
        newFragment.show(getFragmentManager(), "timePicker");
    }

    @SuppressLint("ValidFragment")
    //When declared as an inner class, Android complains and says that this should be declared as static;
    //thus above suppressLint statement was added;
    //wehn dedclared as a separate Java class file, no such complaints

    public class TimePickerFragment extends DialogFragment
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

}
