package com.example.android.friendship;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class AddNewTask extends Activity {

    // 7 days in milliseconds - 7 * 24 * 60 * 60 * 1000
    private static final int SEVEN_DAYS = 604800000;

    private static final String TAG = "Lab-UserInterface";

    private static String timeString;
    private static String dateString;
    private static TextView dateView;
    private static TextView timeView;


    private Date mDate;
    private EditText mTitleText;
    private TimePicker timePicker;
    private EditText mPointsText;
    private TextView textAlarmPrompt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        mTitleText = (EditText) findViewById(R.id.title);
        mPointsText = (EditText) findViewById(R.id.pointsText);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        textAlarmPrompt = (TextView) findViewById(R.id.alarmprompt);

        setDefaultDateTime();
        Calendar then=Calendar.getInstance();


        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Entered cancelButton.OnClickListener.onClick()");

                //TODO - Implement onClick().
                finish();

            }
        });

        //timeString = String.valueOf(then.()) + ":" + String.valueOf(timePicker.getCurrentMinute());



        final Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                log("Entered submitButton.OnClickListener.onClick()");

                // Gather ToDoItem data

                //TODO - Get Priority


                //TODO -  Get Status


                //TODO -  Title
                String titleString = mTitleText.getText().toString();


                int heure = timePicker.getCurrentHour();
                int minut = timePicker.getCurrentMinute();

                Calendar calNow = Calendar.getInstance();
                Calendar calSet = (Calendar) calNow.clone();

                calSet.set(Calendar.HOUR_OF_DAY, heure);
                calSet.set(Calendar.MINUTE, minut);
                calSet.set(Calendar.SECOND, 0);
                calSet.set(Calendar.MILLISECOND, 0);

                if (calSet.compareTo(calNow) <= 0) {
                    // Today Set time passed, count to tomorrow
                    calSet.add(Calendar.DATE, 1);
                }

                setAlarm(calSet);

                //Calendar thing = Calendar.getInstance();
                //thing.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                //thing.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                //timeString = thing.getTime().toString();
                //timeString =                 // Date
                String time = unFuckShit(heure, minut);
                String words = mPointsText.getText().toString();
                log(words);
                //Log.d("TIME",timeString);
                // Package ToDoItem data into an Intent
                Intent data = new Intent();
                Task.packageIntent(data, titleString, time, words);

                //TODO - return data Intent and finish
                setResult(RESULT_OK, data);
                finish();



            }
        });
    }

    private void setAlarm(Calendar targetCal) {

//        textAlarmPrompt.setText("\n\n***\n" + "Alarm is set "
//                + targetCal.getTime() + "\n" + "***\n");

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), 1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);


    }

    // Do not modify below here

    // Use this method to set the default date and time

    private void setDefaultDateTime() {

        // Default is current time + 7 days
        mDate = new Date();
        mDate = new Date(mDate.getTime() + SEVEN_DAYS);

        Calendar c = Calendar.getInstance();
        c.setTime(mDate);

//        setDateString(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
//                c.get(Calendar.DAY_OF_MONTH));



        //setTimeString(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
         //       c.get(Calendar.MILLISECOND));

        //timeView.setText("lol");
    }
    private static String unFuckShit(int hr, int min){
        String hour = "" + hr;
        String minute = "" + min;

        if (hr < 10)
            hour = "0" + hr;
        if (min < 10)
            minute = "0" + min;

        return hour + ":" + minute;
    }

//

    // DialogFragment used to pick a ToDoItem deadline date

//    public static class DatePickerFragment extends DialogFragment implements
//            DatePickerDialog.OnDateSetListener {
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//            // Use the current date as the default date in the picker
//
//            final Calendar c = Calendar.getInstance();
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//
//            // Create a new instance of DatePickerDialog and return it
//            return new DatePickerDialog(getActivity(), this, year, month, day);
//        }
//
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear,
//                              int dayOfMonth) {
//            setDateString(year, monthOfYear, dayOfMonth);
//
//            dateView.setText(dateString);
//        }
//
//    }

    // DialogFragment used to pick a ToDoItem deadline time

//    public static class TimePickerFragment extends DialogFragment implements
//            TimePickerDialog.OnTimeSetListener {
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//            // Use the current time as the default values for the picker
//            final Calendar c = Calendar.getInstance();
//            int hour = c.get(Calendar.HOUR_OF_DAY);
//            int minute = c.get(Calendar.MINUTE);
//
//            // Create a new instance of TimePickerDialog and return
//            return new TimePickerDialog(getActivity(), this, hour, minute,
//                    true);
//        }
//
//        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//            setTimeString(hourOfDay, minute);
//
//            timeView.setText(timeString);
//        }
//    }
    //@Override



//    private void showDatePickerDialog() {
//        DialogFragment newFragment = new DatePickerFragment();
//        newFragment.show(getFragmentManager(), "datePicker");
//    }
//private void showTimePickerDialog() {
//    DialogFragment newFragment = new TimePickerFragment();
//    newFragment.show(getFragmentManager(), "timePicker");
//}
//
    public void log(String msg) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, msg);
    }

}