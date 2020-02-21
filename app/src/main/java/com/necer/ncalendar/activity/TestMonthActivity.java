package com.necer.ncalendar.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.necer.calendar.BaseCalendar;
import com.necer.calendar.MonthCalendar;
import com.necer.entity.CalendarDate;
import com.necer.listener.OnCalendarChangedListener;
import com.necer.listener.OnCalendarMultipleChangedListener;
import com.necer.ncalendar.R;
import com.necer.ncalendar.tools.BaZi;
import com.necer.ncalendar.tools.CalendarUtils;
import com.necer.ncalendar.tools.MyDate;
import com.necer.ncalendar.tools.PaiPan;
import com.necer.utils.CalendarUtil;

import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.List;

public class TestMonthActivity extends BaseActivity {


    private TextView tv_result;
    private MonthCalendar monthCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        tv_result = findViewById(R.id.tv_result);


        monthCalendar = findViewById(R.id.monthCalendar);
        monthCalendar.setSelectedMode(selectedModel);


        monthCalendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate) {
//                tv_result.setText(year + "年" + month + "月" + "   当前页面选中 " + localDate);

                CalendarDate calendarDate = CalendarUtil.getCalendarDate(localDate);
                MyDate.getInstance().initGanZhi(2020, 2, 21);
                String result = MyDate.getInstance().getGanZhi();
                Calendar calendar = Calendar.getInstance();
//                calendar.set(2020, 2, 21, 13, 17);
                String finalResult = CalendarUtils.getYearGanZhi(calendar);

                BaZi baZi = new BaZi(calendar);
                String secondFinalResult = baZi.getYearGanZhi(1);

                PaiPan paiPan = new PaiPan(calendar);
                paiPan.getSiZhuString();
                Log.e("spptag", paiPan.getSiZhuString());
                tv_result.setText(localDate + " 四柱为：" + paiPan.getSiZhuString());

                Log.d(TAG, "setOnCalendarChangedListener:::" + year + "年" + month + "月" + "   当前页面选中 " + localDate);
            }
        });

        monthCalendar.setOnCalendarMultipleChangedListener(new OnCalendarMultipleChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, List<LocalDate> currectSelectList, List<LocalDate> allSelectList) {
                tv_result.setText(year + "年" + month + "月" + " 当前页面选中 " + currectSelectList.size() + "个  总共选中" + allSelectList.size() + "个");

                Log.d(TAG, year + "年" + month + "月");
                Log.d(TAG, "当前页面选中：：" + currectSelectList);
                Log.d(TAG, "全部选中：：" + allSelectList);

            }
        });

    }

    public void lastMonth(View view) {
        monthCalendar.toLastPager();
    }

    public void nextMonth(View view) {
        monthCalendar.toNextPager();
    }

    public void jump_2018_10_10(View view) {
        monthCalendar.jumpDate("2018-10-10");
    }

    public void jump_2019_10_10(View view) {
        monthCalendar.jumpDate("2019-10-10");
    }

    public void jump_2019_6_10(View view) {
        monthCalendar.jumpDate("2019-6-10");
    }


    public void today(View view) {
        monthCalendar.toToday();
    }
}
