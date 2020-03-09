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
import com.necer.ncalendar.tools.MyDate;
import com.necer.ncalendar.tools.PaiPan;
import com.necer.utils.CalendarUtil;
import com.necer.utils.Const;
import com.necer.utils.SiZhuData;
import com.necer.utils.SolarTermUtil;

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
                calendar.set(1992, 2, 13, 22, 0);
//                calendar.set(1990, 1, 11, 12, 30);
//                calendar.set(2020, 0, 13, 10, 50);

                Log.e("spptag", "check time:" + calendar.getTime());
                PaiPan paiPan = new PaiPan(calendar);
                paiPan.getSiZhuString();
                Log.e("spptag", paiPan.getSiZhuString());
                SiZhuData siZhuData = paiPan.getSiZhuData();

                tv_result.setText("1992年3月13（农历2月初十 ）22:00 四柱为：" + siZhuData.getNianZhu() + "(" + paiPan.getNaYin(siZhuData.getNianZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getNianZhu()) + ")" + siZhuData.getYueZhu() + "(" + paiPan.getNaYin(siZhuData.getYueZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getYueZhu()) + ")" + siZhuData.getRiZhu() + "(" + paiPan.getNaYin(siZhuData.getRiZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getRiZhu()) + ")" + siZhuData.getShiZhu() + "(" + paiPan.getNaYin(siZhuData.getShiZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getShiZhu()) + ")");
//                tv_result.setText("1990年2月11（农历正月十六）12:30 四柱为：" + siZhuData.getNianZhu() + "(" + paiPan.getNaYin(siZhuData.getNianZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getNianZhu()) + ")" + siZhuData.getYueZhu() + "(" + paiPan.getNaYin(siZhuData.getYueZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getYueZhu()) + ")" + siZhuData.getRiZhu() + "(" + paiPan.getNaYin(siZhuData.getRiZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getRiZhu()) + ")" + siZhuData.getShiZhu() + "(" + paiPan.getNaYin(siZhuData.getShiZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getShiZhu()) + ")");
                monthCalendar.jumpDate("1990-2-11");
                String[] dayun = paiPan.getDaYunString(1, siZhuData.getNianZhu(), siZhuData.getYueZhu());
                Log.e("spptag", "大运开始-----------------------------------------------------");

                if (dayun != null && dayun.length > 0) {
                    for (int i = 0; i < dayun.length; i++) {
                        Log.e("spptag", "i：" + i + " dayun:" + dayun[i]);
                    }
                }
                Log.e("spptag", "大运年龄：" + SolarTermUtil.getDaYunAge(calendar, siZhuData, Const.MAN));
                Log.e("spptag", "大运结束-----------------------------------------------------");
                Log.e("spptag", "惊蛰" + SolarTermUtil.getSolarTermNum(2020, "JINGZHE") + "");
                SolarTermUtil.solarTermToString(2020);
//                monthCalendar.jumpDate("1992-3-13");
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
