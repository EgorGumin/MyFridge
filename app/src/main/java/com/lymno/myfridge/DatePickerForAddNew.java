package com.lymno.myfridge;

import android.widget.TextView;

import com.lymno.myfridge.DatePicker;

/**
 * Created by Colored on 31.10.2015.
 */
public class DatePickerForAddNew extends DatePicker{
    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year,
                          int month, int day) {

        TextView tv = (TextView) getActivity().findViewById(R.id.add_new_date_add);
        String stMonth;
        String stDay;
        if (month < 9){
            stMonth = "0" + String.valueOf(month + 1);
        }
        else{
            stMonth = String.valueOf(month + 1);
        }
        if (day < 10){
            stDay = "0" + String.valueOf(day);
        }
        else{
            stDay = String.valueOf(day);
        }
        tv.setText(String.valueOf(year) + stMonth + stDay);
    }
}
