package com.qhyccd.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.qhyccd.R;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tx
 * @date 2018/8/9
 */
public class CalendarActivity extends AppCompatActivity {
    private Map<String, Calendar> map = new HashMap<>();
    private CalendarView calendarView;
    private LinearLayout picker;
    private TextView tvMonth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendarView);
        picker = findViewById(R.id.picker);
        tvMonth = findViewById(R.id.tv_month);

        initData();
        tvMonth.setText(calendarView.getCurYear() + "年" + calendarView.getCurMonth() + "月");
        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                tvMonth.setText(year + "年" + month + "月");
            }
        });
        final boolean[] type = {true, true, false, false, false, false};
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerBuilder(CalendarActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        java.util.Calendar c = java.util.Calendar.getInstance();
                        c.setTime(date);
                        int year = c.get(java.util.Calendar.YEAR);
                        int month = c.get(java.util.Calendar.MONTH);
                        calendarView.scrollToCalendar(year, month + 1, 1);
                    }
                }).setType(type).build();
                pvTime.show();
            }
        });
    }

    private void initData() {
        Calendar calendar1 = getSchemeCalendar(2018, 8, 11, "1");
        Calendar calendar2 = getSchemeCalendar(2018, 8, 12, "2");
        Calendar calendar3 = getSchemeCalendar(2018, 8, 13, "3");
        Calendar calendar4 = getSchemeCalendar(2018, 8, 6, "4");
        map.put(calendar1.toString(), calendar1);
        map.put(calendar2.toString(), calendar2);
        map.put(calendar3.toString(), calendar3);
        map.put(calendar4.toString(), calendar4);
        calendarView.setSchemeDate(map);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setScheme(text);
        return calendar;
    }

}
