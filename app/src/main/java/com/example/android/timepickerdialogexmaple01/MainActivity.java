package com.example.android.timepickerdialogexmaple01;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//sample code adopted from:
//https://neurobin.org/docs/android/android-time-picker-example/


public class MainActivity extends AppCompatActivity {

    Button pickTime;
    DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pickTime = findViewById(R.id.button_PickTime);

    pickTime.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            dialogFragment = new TimePickerFragment();
            dialogFragment.show(getSupportFragmentManager(), "Time Picker");

        }
    });

    }


    //public void showTimePickerDialog(View v) {
    //    DialogFragment newFragment = new TimePickerFragment();
    //    newFragment.show(getSupportFragmentManager(), "timePicker");

    //}

}
