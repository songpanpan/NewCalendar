package com.necer.ncalendar.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.util.LogUtils;

public class TestMonthActivity extends BaseActivity {


    private TextView tv_result, tv_selected_date;
    private MonthCalendar monthCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        tv_result = findViewById(R.id.tv_result);
        tv_selected_date = findViewById(R.id.tv_selected_date);
        monthCalendar = findViewById(R.id.monthCalendar);
        monthCalendar.setSelectedMode(selectedModel);
        onYearMonthDayTimePicker();
        monthCalendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate) {

                Log.d(TAG, "setOnCalendarChangedListener:::" + year + "年" + month + "月" + "   当前页面选中 " + localDate);
                tv_selected_date.setText(year + "年" + month + "月");
                jumpToData(year + "", month + "", localDate.getDayOfMonth() + "");
                getDaYun(year + "", month + "", localDate.getDayOfMonth() + "", 12 + "", 30 + "");
            }
        });

        monthCalendar.setOnCalendarMultipleChangedListener(new OnCalendarMultipleChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, List<LocalDate> currectSelectList, List<LocalDate> allSelectList) {

                Log.d(TAG, year + "年" + month + "月");
                Log.d(TAG, "当前页面选中：：" + currectSelectList);
                Log.d(TAG, "全部选中：：" + allSelectList);
            }
        });

    }

    /**
     * 时间选择工具，只选择公历
     */
    public void onYearMonthDayTimePicker() {
        DateTimePicker picker = new DateTimePicker(this, DateTimePicker.HOUR_24);
        picker.setDateRangeStart(1920, 1, 1);
        picker.setDateRangeEnd(2099, 12, 31);
        picker.setTimeRangeStart(1, 0);
        picker.setTimeRangeEnd(23, 59);
        picker.setTopLineColor(0x99FF0000);
        picker.setLabelTextColor(0xFFFF0000);
        picker.setDividerColor(0xFFFF0000);
        picker.setSelectedItem(1990, 1, 1, 12, 30);
        picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
//                Toast.makeText(TestMonthActivity.this, year + "-" + month + "-" + day + " " + hour + ":" + minute, Toast.LENGTH_LONG).show();
                tv_selected_date.setText(year + "年" + month + "月");
                jumpToData(year, month, day);
                getDaYun(year, month, day, hour, minute);
            }
        });
        picker.show();
    }

    public void lastMonth(View view) {
        monthCalendar.toLastPager();
    }

    public void nextMonth(View view) {
        monthCalendar.toNextPager();
    }

    /**
     * 根据年月日跳转日历
     *
     * @param year  年
     * @param month 月
     * @param day   日
     */
    private void jumpToData(String year, String month, String day) {
        monthCalendar.jumpDate(year + "-" + month + "-" + day);
    }

    /**
     * 根据时间计算大运
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param minute 分
     */
    private void getDaYun(String year, String month, String day, String hour, String minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day),
                Integer.parseInt(hour), Integer.parseInt(minute));
        Log.e("spptag", "check time:" + calendar.getTime());
        PaiPan paiPan = new PaiPan(calendar);
        paiPan.getSiZhuString();
        Log.e("spptag", paiPan.getSiZhuString());
        SiZhuData siZhuData = paiPan.getSiZhuData();

        String[] dayun = paiPan.getDaYunString(1, siZhuData.getNianZhu(), siZhuData.getYueZhu());
        Log.e("spptag", "大运开始-----------------------------------------------------");
        StringBuffer dayunStr = new StringBuffer();
        if (dayun != null && dayun.length > 0) {
            for (int i = 0; i < dayun.length; i++) {
                Log.e("spptag", "i：" + i + " dayun:" + dayun[i]);
                dayunStr.append(" ").append(dayun[i]);
            }
        }
        Log.e("spptag", "大运年龄：" + SolarTermUtil.getDaYunAge(calendar, siZhuData, Const.MAN));
        Log.e("spptag", "大运结束-----------------------------------------------------");
        Log.e("spptag", "惊蛰" + SolarTermUtil.getSolarTermNum(2020, "JINGZHE") + "");
        SolarTermUtil.solarTermToString(2020);

        tv_result.setText(year + "年" + month + "月" + day + "日" + "四柱为：\n" + siZhuData.getNianZhu() + "(" +
                paiPan.getNaYin(siZhuData.getNianZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getNianZhu())
                + ")\n" + siZhuData.getYueZhu() + "(" + paiPan.getNaYin(siZhuData.getYueZhu())
                + ":" + paiPan.getOtherNayin(siZhuData.getYueZhu()) + ")\n" + siZhuData.getRiZhu()
                + "(" + paiPan.getNaYin(siZhuData.getRiZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getRiZhu())
                + ")\n" + siZhuData.getShiZhu() + "(" + paiPan.getNaYin(siZhuData.getShiZhu())
                + ":" + paiPan.getOtherNayin(siZhuData.getShiZhu()) + ")\n" + "大运：\n" + dayunStr+
                "\n大运年龄："+SolarTermUtil.getDaYunAge(calendar, siZhuData, Const.MAN));

    }
//生日数据
//    calendar.set(1992,2,13,22,0);
//    calendar.set(1990,1,11,12,30);
//    calendar.set(1990,9,25,12,30);
//    calendar.set(1970,9,13,12,30);
//    calendar.set(1971,6,31,12,30);
//    calendar.set(1980,6,31,2,30);
//    calendar.set(2020,0,13,10,50);
//    calendar.set(1995,5,20,10,50);


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
