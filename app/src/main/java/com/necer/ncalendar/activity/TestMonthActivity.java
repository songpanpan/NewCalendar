package com.necer.ncalendar.activity;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
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
                CalendarDate calendarDate = CalendarUtil.getCalendarDate(localDate);
                Log.d(TAG, "setOnCalendarChangedListener:::" + year + "年" + month + "月" + "   当前页面选中 " + localDate);
                tv_selected_date.setText(year + "年" + month + "月 农历" + calendarDate.lunar.lunarYear
                        + "年" + calendarDate.lunar.lunarMonth + "月" + calendarDate.lunar.lunarDay + "日");
                jumpToData(year + "", month + "", localDate.getDayOfMonth() + "");
                getDaYun(year + "", month + "", localDate.getDayOfMonth() + "", 22 + "", 00 + "");

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
        picker.setSelectedItem(1995, 5, 5, 12, 30);
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
    @SuppressLint("SetTextI18n")
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
        List<String> wuXingList = getWuXingList(paiPan, siZhuData);
        String baZi = siZhuData.getNianZhu() + siZhuData.getYueZhu() + siZhuData.getRiZhu() + siZhuData.getShiZhu();
        String[] baZiArray = baZi.split("");
        List<String> baZiList = new ArrayList<>();
        for (int i = 0; i < baZiArray.length; i++) {
            if (!baZiArray[i].equals("")) {
                baZiList.add(baZiArray[i]);
            }
        }
        Log.e(TAG, "getDaYun: getShiTianGanXiangHe(baZiList)" + getShiTianGanXiangHe(baZiList));
        tv_result.setText(year + "年" + month + "月" + day + "日" + "四柱为：\n" +
                siZhuData.getNianZhu() + "(" + paiPan.getNaYin(siZhuData.getNianZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getNianZhu())
                + " 五行：" + wuXingList.get(0) + " " + wuXingList.get(1) + ")\n"
                + siZhuData.getYueZhu() + "(" + paiPan.getNaYin(siZhuData.getYueZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getYueZhu())
                + " 五行：" + wuXingList.get(2) + " " + wuXingList.get(3) + ")\n"
                + siZhuData.getRiZhu() + "(" + paiPan.getNaYin(siZhuData.getRiZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getRiZhu())
                + " 五行：" + wuXingList.get(4) + " " + wuXingList.get(5) + ")\n"
                + siZhuData.getShiZhu() + "(" + paiPan.getNaYin(siZhuData.getShiZhu()) + ":" + paiPan.getOtherNayin(siZhuData.getShiZhu())
                + " 五行：" + wuXingList.get(6) + " " + wuXingList.get(7) + ")\n"
                + "十天干相合:" + getShiTianGanXiangHe(baZiList) + "\n"
                + "十二支六合:" + getShiErZhiLiuHe(baZiList) + "\n"
                + "生合:" + getShengHe(baZiList) + "\n"
                + "克合:" + getKeHe(baZiList) + "\n"
                + "十二支三合:" + getShiErZhiSanHe(baZiList) + "\n"
                + "十二支相冲:" + getShiErZhiXiangChong(baZiList) + "\n"
                + "十二支相穿:" + getShiErZhiXiangChuan(baZiList) + "\n"
                + "十二支相刑:" + getShiErZhiXiangXing(baZiList) + "\n"
                + "大运：\n" + dayunStr + "\n大运年龄：" + SolarTermUtil.getDaYunAge(calendar, siZhuData, Const.MAN) + "\n" + "扎根运：" +
                PaiPan.getZhaGenYunFromRiZhu(siZhuData.getRiZhu()) + " 第" +
                PaiPan.getZhaGenYunLevel(SolarTermUtil.getDaYunAge(calendar, siZhuData, Const.MAN)) + "段\n"
                + "时辰：" + siZhuData.getShiZhu().substring(1) + "\n" + "建星：" + getJianXing(calendar) + "\n日柱挂："
                + paiPan.getOtherNayin(siZhuData.getRiZhu()) + "," +
                PaiPan.LiuShiGua.get(paiPan.getOtherNayin(siZhuData.getRiZhu())) + "\n五行\n" + "");

    }

    /**
     * 获取十天干相合
     *
     * @param baZiList 八字list
     * @return 返回相合的十天干
     */
    private String getShiTianGanXiangHe(List<String> baZiList) {
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("甲");
        list1.add("己");
        List<String> list2 = new ArrayList<>();
        list2.add("乙");
        list2.add("庚");
        List<String> list3 = new ArrayList<>();
        list3.add("丙");
        list3.add("辛");
        List<String> list4 = new ArrayList<>();
        list4.add("丁");
        list4.add("壬");
        List<String> list5 = new ArrayList<>();
        list5.add("戊");
        list5.add("癸");
        if (aContainsB(baZiList, list1)) {
            result += "甲与己合 ";
        }
        if (aContainsB(baZiList, list2)) {
            result += "乙与庚合 ";
        }
        if (aContainsB(baZiList, list3)) {
            result += "丙与辛合 ";
        }
        if (aContainsB(baZiList, list4)) {
            result += "丁与壬合 ";
        }
        if (aContainsB(baZiList, list5)) {
            result += "戊与癸合 ";
        }

        return result;
    }

    /**
     * 获取十二支六合
     *
     * @param baZiList 八字list
     * @return 返回十二支六合
     */
    private String getShiErZhiLiuHe(List<String> baZiList) {
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("子");
        list1.add("丑");
        List<String> list2 = new ArrayList<>();
        list2.add("寅");
        list2.add("亥");
        List<String> list3 = new ArrayList<>();
        list3.add("卯");
        list3.add("戌");
        List<String> list4 = new ArrayList<>();
        list4.add("辰");
        list4.add("酉");
        List<String> list5 = new ArrayList<>();
        list5.add("巳");
        list5.add("申");
        List<String> list6 = new ArrayList<>();
        list6.add("午");
        list6.add("未");
        if (aContainsB(baZiList, list1)) {
            result += "子与丑合土 ";
        }
        if (aContainsB(baZiList, list2)) {
            result += "寅与亥合木 ";
        }
        if (aContainsB(baZiList, list3)) {
            result += "卯与戊合火 ";
        }
        if (aContainsB(baZiList, list4)) {
            result += "辰与酉合金 ";
        }
        if (aContainsB(baZiList, list5)) {
            result += "巳与申合水 ";
        }
        if (aContainsB(baZiList, list6)) {
            result += "午与未合午太阳，未太阴也 ";
        }

        return result;
    }

    /**
     * 生合
     *
     * @param baZiList 八字list
     * @return 返回生合的十天干
     */
    private String getShengHe(List<String> baZiList) {
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("寅");
        list1.add("亥");
        List<String> list2 = new ArrayList<>();
        list2.add("辰");
        list2.add("酉");
        List<String> list3 = new ArrayList<>();
        list3.add("午");
        list3.add("未");
        if (aContainsB(baZiList, list1)) {
            result += "寅亥合水生木 ";
        }
        if (aContainsB(baZiList, list2)) {
            result += "辰酉合土生金 ";
        }
        if (aContainsB(baZiList, list3)) {
            result += "午未合火生土 ";
        }
        return result;
    }

    /**
     * 克合
     *
     * @param baZiList 八字list
     * @return 返回克合的十天干
     */
    private String getKeHe(List<String> baZiList) {
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("子");
        list1.add("丑");
        List<String> list2 = new ArrayList<>();
        list2.add("巳");
        list2.add("申");
        List<String> list3 = new ArrayList<>();
        list3.add("卯");
        list3.add("戌");
        if (aContainsB(baZiList, list1)) {
            result += "子丑合土克水 ";
        }
        if (aContainsB(baZiList, list2)) {
            result += "巳申合火克金 ";
        }
        if (aContainsB(baZiList, list3)) {
            result += "卯戌合木克土 ";
        }
        return result;
    }

    /**
     * 获取十二支三合
     *
     * @param baZiList 八字list
     * @return 返回相合的十天干
     */
    private String getShiErZhiSanHe(List<String> baZiList) {
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("申");
        list1.add("子");
        list1.add("辰");
        List<String> list2 = new ArrayList<>();
        list2.add("亥");
        list2.add("卯");
        list2.add("未");
        List<String> list3 = new ArrayList<>();
        list3.add("寅");
        list3.add("午");
        list3.add("戌");
        List<String> list4 = new ArrayList<>();
        list4.add("巳");
        list4.add("酉");
        list4.add("丑");
        List<String> list5 = new ArrayList<>();
        list5.add("辰");
        list5.add("戌");
        list5.add("丑");
        list5.add("未");
        if (aContainsB(baZiList, list1)) {
            result += "申子辰水局 ";
        }
        if (aContainsB(baZiList, list2)) {
            result += "亥卯未木局 ";
        }
        if (aContainsB(baZiList, list3)) {
            result += "寅午戌火局 ";
        }
        if (aContainsB(baZiList, list4)) {
            result += "巳酉丑金局 ";
        }
        if (aContainsB(baZiList, list5)) {
            result += "辰戌丑未土局 ";
        }

        return result;
    }

    /**
     * 获取十二支相冲
     *
     * @param baZiList 八字list
     * @return 返回十二支六合
     */
    private String getShiErZhiXiangChong(List<String> baZiList) {
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("子");
        list1.add("午");
        List<String> list2 = new ArrayList<>();
        list2.add("寅");
        list2.add("申");
        List<String> list3 = new ArrayList<>();
        list3.add("卯");
        list3.add("酉");
        List<String> list4 = new ArrayList<>();
        list4.add("辰");
        list4.add("戌");
        List<String> list5 = new ArrayList<>();
        list5.add("巳");
        list5.add("亥");
        List<String> list6 = new ArrayList<>();
        list6.add("丑");
        list6.add("未");
        if (aContainsB(baZiList, list1)) {
            result += "子午相冲 ";
        }
        if (aContainsB(baZiList, list2)) {
            result += "寅申相冲 ";
        }
        if (aContainsB(baZiList, list3)) {
            result += "卯酉相冲 ";
        }
        if (aContainsB(baZiList, list4)) {
            result += "辰戌相冲 ";
        }
        if (aContainsB(baZiList, list5)) {
            result += "巳亥相冲 ";
        }
        if (aContainsB(baZiList, list6)) {
            result += "丑未相冲 ";
        }

        return result;
    }

    /**
     * 获取十二支相穿
     *
     * @param baZiList 八字list
     * @return 返回十二支六合
     */
    private String getShiErZhiXiangChuan(List<String> baZiList) {
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("子");
        list1.add("未");
        List<String> list2 = new ArrayList<>();
        list2.add("丑");
        list2.add("午");
        List<String> list3 = new ArrayList<>();
        list3.add("寅");
        list3.add("巳");
        List<String> list4 = new ArrayList<>();
        list4.add("卯");
        list4.add("辰");
        List<String> list5 = new ArrayList<>();
        list5.add("申");
        list5.add("亥");
        List<String> list6 = new ArrayList<>();
        list6.add("酉");
        list6.add("戌");
        if (aContainsB(baZiList, list1)) {
            result += "子未相穿 ";
        }
        if (aContainsB(baZiList, list2)) {
            result += "丑午相穿 ";
        }
        if (aContainsB(baZiList, list3)) {
            result += "寅巳相穿 ";
        }
        if (aContainsB(baZiList, list4)) {
            result += "卯辰相穿 ";
        }
        if (aContainsB(baZiList, list5)) {
            result += "申亥相穿 ";
        }
        if (aContainsB(baZiList, list6)) {
            result += "酉戌相穿 ";
        }

        return result;
    }

    /**
     * 获取十二支相刑
     *
     * @param baZiList 八字list
     * @return 返回相合的十天干
     */
    private String getShiErZhiXiangXing(List<String> baZiList) {
        String result = "";
        List<String> list11 = new ArrayList<>();
        list11.add("寅");
        list11.add("巳");
        List<String> list12 = new ArrayList<>();
        list12.add("巳");
        list12.add("申");
        List<String> list13 = new ArrayList<>();
        list13.add("寅");
        list13.add("申");
        List<String> list21 = new ArrayList<>();
        list21.add("丑");
        list21.add("戌");
        List<String> list22 = new ArrayList<>();
        list22.add("戌");
        list22.add("未");
        List<String> list23 = new ArrayList<>();
        list23.add("丑");
        list23.add("未");
        List<String> list3 = new ArrayList<>();
        list3.add("子");
        list3.add("卯");
        List<String> list41 = new ArrayList<>();
        list41.add("辰");
        List<String> list42 = new ArrayList<>();
        list42.add("午");
        List<String> list43 = new ArrayList<>();
        list43.add("酉");
        List<String> list44 = new ArrayList<>();
        list44.add("亥");
        if (aContainsB(baZiList, list11)) {
            result += "寅刑巳，为恃势之刑 ";
        }
        if (aContainsB(baZiList, list12)) {
            result += "巳刑申，为恃势之刑 ";
        }
        if (aContainsB(baZiList, list13)) {
            result += "申刑寅，为恃势之刑 ";
        }
        if (aContainsB(baZiList, list21)) {
            result += "丑刑戌，为无恩之刑 ";
        }
        if (aContainsB(baZiList, list22)) {
            result += "戌刑未，为无恩之刑 ";
        }
        if (aContainsB(baZiList, list23)) {
            result += "未刑丑，为无恩之刑 ";
        }
        if (aContainsB(baZiList, list3)) {
            result += "子刑卯，卯刑子，为无礼只刑 ";
        }
        if (aContainsB(baZiList, list41)) {
            result += "辰，自刑之刑 ";
        }
        if (aContainsB(baZiList, list42)) {
            result += "午，自刑之刑 ";
        }
        if (aContainsB(baZiList, list43)) {
            result += "酉，自刑之刑 ";
        }
        if (aContainsB(baZiList, list44)) {
            result += "亥，自刑之刑 ";
        }

        return result;
    }

    /**
     * 判断list a 是否包含list b
     *
     * @param aList list a
     * @param bList list b
     * @return list a 是否包含list b
     */
    private boolean aContainsB(List<String> aList, List<String> bList) {
        if (aList != null && aList.size() > 0 && bList != null && bList.size() > 0) {
            for (int i = 0; i < bList.size(); i++) {
                if (!aList.contains(bList.get(i))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 获取五行list
     *
     * @param paiPan    排盘
     * @param siZhuData 四柱
     * @return 阴阳五行list
     */
    private List<String> getWuXingList(PaiPan paiPan, SiZhuData siZhuData) {
        List<String> wuXingList = new ArrayList<>();
        String nianZhuNaYin = paiPan.getOtherNayin(siZhuData.getNianZhu());
        String yueZhuNaYin = paiPan.getOtherNayin(siZhuData.getYueZhu());
        String riZhuNaYin = paiPan.getOtherNayin(siZhuData.getRiZhu());
        String shiZhuNaYin = paiPan.getOtherNayin(siZhuData.getShiZhu());
        Log.e(TAG, "getWuXingList: " + nianZhuNaYin);
        Log.e(TAG, "getWuXingList: " + yueZhuNaYin);
        Log.e(TAG, "getWuXingList: " + riZhuNaYin);
        Log.e(TAG, "getWuXingList: " + shiZhuNaYin);
        Log.e(TAG, "getWuXingList: " + nianZhuNaYin + " " + nianZhuNaYin.substring(2, 4) + " " + nianZhuNaYin.substring(6));
        Log.e(TAG, "getWuXingList: " + yueZhuNaYin + " " + yueZhuNaYin.substring(2, 4) + " " + yueZhuNaYin.substring(6));
        Log.e(TAG, "getWuXingList: " + riZhuNaYin + " " + riZhuNaYin.substring(2, 4) + " " + riZhuNaYin.substring(6));
        Log.e(TAG, "getWuXingList: " + shiZhuNaYin + " " + shiZhuNaYin.substring(2, 4) + " " + shiZhuNaYin.substring(6));
        if (nianZhuNaYin.length() == 8) {
            wuXingList.add(0, PaiPan.yinYangWuXing.get(nianZhuNaYin.substring(2, 4)));
            wuXingList.add(1, PaiPan.yinYangWuXing.get(nianZhuNaYin.substring(6)));
        }
        if (yueZhuNaYin.length() == 8) {
            wuXingList.add(2, PaiPan.yinYangWuXing.get(yueZhuNaYin.substring(2, 4)));
            wuXingList.add(3, PaiPan.yinYangWuXing.get(yueZhuNaYin.substring(6)));
        }
        if (riZhuNaYin.length() == 8) {
            wuXingList.add(4, PaiPan.yinYangWuXing.get(riZhuNaYin.substring(2, 4)));
            wuXingList.add(5, PaiPan.yinYangWuXing.get(riZhuNaYin.substring(6)));
        }
        if (shiZhuNaYin.length() == 8) {
            wuXingList.add(6, PaiPan.yinYangWuXing.get(shiZhuNaYin.substring(2, 4)));
            wuXingList.add(7, PaiPan.yinYangWuXing.get(shiZhuNaYin.substring(6)));
        }
        return wuXingList;
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

    /**
     * 获取建星（哪天起建叫月令  月令之前按照上一个月令 之后按照本月月令）
     *
     * @return 建星
     */
    public String getJianXing(Calendar calendar) {
        Calendar tempCalendar = calendar;
        //建星后第几天
        int preDay = 0;
        PaiPan paiPan = new PaiPan(calendar);
        SiZhuData siZhuData = paiPan.getSiZhuData();
        String yueZhu = siZhuData.getYueZhu().substring(1);
        String riZhu = siZhuData.getRiZhu().substring(1);
        if (yueZhu.equals(riZhu)) {
            return "建";
        }
        for (int i = 0; i < 15; i++) {
            tempCalendar.add(Calendar.DATE, -1);

            String jieQi = getJieQi(tempCalendar);
            if (jieQi == null || !SolarTermUtil.specialJieQi.contains(jieQi)) {
                preDay++;
            }
            Log.e("getJieQi", "getJieQi:" + getJieQi(tempCalendar));
            paiPan = new PaiPan(tempCalendar);
            siZhuData = paiPan.getSiZhuData();
            yueZhu = siZhuData.getYueZhu().substring(1);
            riZhu = siZhuData.getRiZhu().substring(1);
            if (yueZhu.equals(riZhu)) {
                return PaiPan.JianXing[preDay];
            }
        }
        return "";
    }

    /**
     * 获取当前日期的节气
     *
     * @param calendar 日期
     * @return 节气
     */
    private String getJieQi(Calendar calendar) {
        int solarYear = calendar.get(Calendar.YEAR);
        int solarMonth = calendar.get(Calendar.MONTH);
        int solarDay = calendar.get(Calendar.DAY_OF_MONTH);
        Log.e("getJieQi", "solarYear:" + solarYear + "  solarMonth:" + solarMonth + " solarDay:" + solarDay);
        return SolarTermUtil.getSolatName(solarYear, (solarMonth < 10 ? ("0" + solarMonth) : (solarMonth + "")) + solarDay);
    }

    /**
     * 获取建星（哪天起建叫月令  月令之前按照上一个月令 之后按照本月月令）
     *
     * @param calendar 日期
     * @return 建星
     */
    public String getJianXingNew(Calendar calendar) {
        String nongLiMonth = PaiPan.getNongLiMonth(calendar);
        String[] JianXingZhi = null;
        if (nongLiMonth.contains("正")) {
            JianXingZhi = PaiPan.JianXingNew;
        } else if (nongLiMonth.contains("二")) {
            JianXingZhi = PaiPan.JianXingNew2;
        } else if (nongLiMonth.contains("三")) {
            JianXingZhi = PaiPan.JianXingNew3;
        } else if (nongLiMonth.contains("四")) {
            JianXingZhi = PaiPan.JianXingNew4;
        } else if (nongLiMonth.contains("五")) {
            JianXingZhi = PaiPan.JianXingNew5;
        } else if (nongLiMonth.contains("六")) {
            JianXingZhi = PaiPan.JianXingNew6;
        } else if (nongLiMonth.contains("七")) {
            JianXingZhi = PaiPan.JianXingNew7;
        } else if (nongLiMonth.contains("八")) {
            JianXingZhi = PaiPan.JianXingNew8;
        } else if (nongLiMonth.contains("九")) {
            JianXingZhi = PaiPan.JianXingNew9;
        } else if (nongLiMonth.contains("十")) {
            JianXingZhi = PaiPan.JianXingNew10;
        } else if (nongLiMonth.contains("冬")) {
            JianXingZhi = PaiPan.JianXingNew11;
        } else if (nongLiMonth.contains("腊")) {
            JianXingZhi = PaiPan.JianXingNew12;
        }
        PaiPan paiPan = new PaiPan(calendar);
        SiZhuData siZhuData = paiPan.getSiZhuData();
        String riZhu = siZhuData.getRiZhu().substring(1);
        for (int i = 0; i < PaiPan.Zhi.length; i++) {
            if (riZhu.equals(PaiPan.Zhi[i])) {
                return JianXingZhi[i];
            }
        }
        return nongLiMonth;
    }
}
