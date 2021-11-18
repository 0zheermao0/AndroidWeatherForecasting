package edu.neu.weatherforecasting.utils;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static Date temp;
    private static Calendar calendar;

    public static void setDateDialog(Context context, EditText etDate){
        //选择日期
        etDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Calendar calendar = Calendar.getInstance();
                    int YEAR = calendar.get(Calendar.YEAR);
                    int MONTH = calendar.get(Calendar.MONTH);
                    int DAY = calendar.get(Calendar.DAY_OF_MONTH);
                    @SuppressLint("ResourceType")
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            String date = String.format("%04d", year)+"-"+ String.format("%02d", month + 1)+"-"+ String.format("%02d", dayOfMonth);
                            etDate.setText(date);
                        }
                    }, YEAR, MONTH, DAY);
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == DialogInterface.BUTTON_NEGATIVE) {
                                //取消操作
                                etDate.clearFocus();
                            }
                        }
                    });
                    datePickerDialog.show();
                }
                etDate.clearFocus();
            }
        });
    }

    public static void setTimeDialog(Context context, EditText etTime){
        etTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Calendar calendar = Calendar.getInstance();
                    int HOUR = calendar.get(Calendar.HOUR_OF_DAY);
                    int MINUTE = calendar.get(Calendar.MINUTE);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            etTime.setText(String.format("%02d", hourOfDay)+":"+ String.format("%02d", minute));
                        }
                    }, HOUR, MINUTE, true);
                    timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == DialogInterface.BUTTON_NEGATIVE) {
                                //取消操作
                                etTime.clearFocus();
                            }
                        }
                    });
                    timePickerDialog.show();
                }
                etTime.clearFocus();
            }
        });
    }
}
