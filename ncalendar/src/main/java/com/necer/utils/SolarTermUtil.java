package com.necer.utils;

import android.util.Log;

import com.necer.calendar.CangGanShiShenBean;
import com.necer.calendar.ShiErGongBean;
import com.necer.calendar.ShiShenBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 节气工具
 */
public class SolarTermUtil {

    private final static String TAG = "SolarTermUtil";

    private static final double D = 0.2422;
    private final static Map<String, Integer[]> INCREASE_OFFSETMAP = new HashMap<String, Integer[]>();// +1偏移
    private final static Map<String, Integer[]> DECREASE_OFFSETMAP = new HashMap<String, Integer[]>();// -1偏移
    //十神
    public static String[][] shiShens = {
            {"比肩", "劫财", "食神", "伤官", "偏财", "正财", "七杀", "正官", "偏印", "正印"},
            {"劫财", "比肩", "伤官", "食神", "正财", "偏财", "正官", "七杀", "正印", "偏印"},
            {"偏印", "正印", "比肩", "劫财", "食神", "伤官", "偏财", "正财", "七杀", "正官"},
            {"正印", "偏印", "劫财", "比肩", "伤官", "食神", "正财", "偏财", "正官", "七杀"},
            {"七杀", "正官", "偏印", "正印", "比肩", "劫财", "食神", "伤官", "偏财", "正财"},
            {"正官", "七杀", "正印", "偏印", "劫财", "比肩", "伤官", "食神", "正财", "偏财"},
            {"偏财", "正财", "七杀", "正官", "偏印", "正印", "比肩", "劫财", "食神", "伤官"},
            {"正财", "偏财", "正官", "七杀", "正印", "偏印", "劫财", "比肩", "伤官", "食神"},
            {"食神", "伤官", "偏财", "正财", "七杀", "正官", "偏印", "正印", "比肩", "劫财"},
            {"伤官", "食神", "正财", "偏财", "正官", "七杀", "正印", "偏印", "劫财", "比肩"},
    };
    //十二宫
    public static String[][] shiErGong = {
            {"亥", "寅", "寅", "巳", "申", "午", "酉", "酉", "子", "卯"},
            {"子", "卯", "卯", "午", "酉", "巳", "申", "申", "亥", "寅"},
            {"丑", "辰", "辰", "未", "戌", "辰", "未", "未", "戌", "丑"},
            {"寅", "巳", "巳", "申", "亥", "卯", "午", "午", "酉", "子"},
            {"卯", "午", "午", "酉", "子", "寅", "巳", "巳", "申", "亥"},
            {"辰", "未", "未", "戌", "丑", "丑", "辰", "辰", "未", "戌"},
            {"巳", "申", "申", "亥", "寅", "子", "卯", "卯", "午", "酉"},
            {"午", "酉", "酉", "子", "卯", "亥", "寅", "寅", "巳", "申"},
            {"未", "戌", "戌", "丑", "辰", "戌", "丑", "丑", "辰", "未"},
            {"申", "亥", "亥", "寅", "巳", "酉", "子", "子", "卯", "午"},
            {"酉", "子", "子", "卯", "午", "申", "亥", "亥", "寅", "巳"},
            {"戌", "丑", "丑", "辰", "未", "未", "戌", "戌", "丑", "辰"}
    };

    //十二宫天干
    public static final String[] shiErGongTianGan = new String[]{"甲", "丙", "戊", "庚", "壬", "乙", "丁", "己", "辛", "癸"};

    //十二宫名称
    public static final String[] shiErGongName = new String[]{"长生", "沐浴", "冠带", "临官", "帝旺", "衰", "病", "死", "墓", "绝", "胎", "养"};


    //十二地支循藏
    public static String[][] diZhiXunCang = {
            {"葵"},
            {"己", "癸", "辛"},
            {"甲", "丙", "戊"},
            {"乙"},
            {"戊", "乙", "癸"},
            {"丙", "戊", "庚"},
            {"丁", "己"},
            {"己", "乙", "丁"},
            {"庚", "壬", "戊"},
            {"辛"},
            {"戊", "丁", "辛"},
            {"壬", "甲"},
    };

    public static final String[] Gan = new String[]{"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};

    public static final String[] Zhi = new String[]{"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    public final static ArrayList<String> specialJieQi = new ArrayList<String>() {{
        add("立春");
        add("惊蛰");
        add("清明");
        add("立夏");
        add("芒种");
        add("小暑");
        add("立秋");
        add("白露");
        add("寒露");
        add("立冬");
        add("大雪");
        add("小寒");
    }};


    /**
     * 24节气
     **/
    private static enum SolarTermsEnum {
        LICHUN, // --立春
        YUSHUI, // --雨水
        JINGZHE, // --惊蛰
        CHUNFEN, // 春分
        QINGMING, // 清明
        GUYU, // 谷雨
        LIXIA, // 立夏
        XIAOMAN, // 小满
        MANGZHONG, // 芒种
        XIAZHI, // 夏至
        XIAOSHU, // 小暑
        DASHU, // 大暑
        LIQIU, // 立秋
        CHUSHU, // 处暑
        BAILU, // 白露
        QIUFEN, // 秋分
        HANLU, // 寒露
        SHUANGJIANG, // 霜降
        LIDONG, // 立冬
        XIAOXUE, // 小雪
        DAXUE, // 大雪
        DONGZHI, // 冬至
        XIAOHAN, // 小寒
        DAHAN;// 大寒
    }

    static {
        DECREASE_OFFSETMAP.put(SolarTermsEnum.YUSHUI.name(),
                new Integer[]{2026});// 雨水
        INCREASE_OFFSETMAP.put(SolarTermsEnum.CHUNFEN.name(),
                new Integer[]{2084});// 春分
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOMAN.name(),
                new Integer[]{2008});// 小满
        INCREASE_OFFSETMAP.put(SolarTermsEnum.MANGZHONG.name(),
                new Integer[]{1902});// 芒种
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAZHI.name(),
                new Integer[]{1928});// 夏至
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOSHU.name(), new Integer[]{
                1925, 2016});// 小暑
        INCREASE_OFFSETMAP.put(SolarTermsEnum.DASHU.name(),
                new Integer[]{1922});// 大暑
        INCREASE_OFFSETMAP.put(SolarTermsEnum.LIQIU.name(),
                new Integer[]{2002});// 立秋
        INCREASE_OFFSETMAP.put(SolarTermsEnum.BAILU.name(),
                new Integer[]{1927});// 白露
        INCREASE_OFFSETMAP.put(SolarTermsEnum.QIUFEN.name(),
                new Integer[]{1942});// 秋分
        INCREASE_OFFSETMAP.put(SolarTermsEnum.SHUANGJIANG.name(),
                new Integer[]{2089});// 霜降
        INCREASE_OFFSETMAP.put(SolarTermsEnum.LIDONG.name(),
                new Integer[]{2089});// 立冬
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOXUE.name(),
                new Integer[]{1978});// 小雪
        INCREASE_OFFSETMAP.put(SolarTermsEnum.DAXUE.name(),
                new Integer[]{1954});// 大雪
        DECREASE_OFFSETMAP.put(SolarTermsEnum.DONGZHI.name(), new Integer[]{
                1918, 2021});// 冬至

        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOHAN.name(),
                new Integer[]{1982});// 小寒
        DECREASE_OFFSETMAP.put(SolarTermsEnum.XIAOHAN.name(),
                new Integer[]{2019});// 小寒

        INCREASE_OFFSETMAP.put(SolarTermsEnum.DAHAN.name(),
                new Integer[]{2082});// 大寒
    }

    // 定义一个二维数组，第一维数组存储的是20世纪的节气C值，第二维数组存储的是21世纪的节气C值,0到23个，依次代表立春、雨水...大寒节气的C值
    private static final double[][] CENTURY_ARRAY = {
            {4.6295, 19.4599, 6.3826, 21.4155, 5.59, 20.888, 6.318, 21.86,
                    6.5, 22.2, 7.928, 23.65, 8.35, 23.95, 8.44, 23.822, 9.098,
                    24.218, 8.218, 23.08, 7.9, 22.6, 6.11, 20.84},
            {3.87, 18.73, 5.63, 20.646, 4.81, 20.1, 5.52, 21.04, 5.678, 21.37,
                    7.108, 22.83, 7.5, 23.13, 7.646, 23.042, 8.318, 23.438,
                    7.438, 22.36, 7.18, 21.94, 5.4055, 20.12}};

    /**
     * @param year 年份
     * @param name 节气的名称
     * @return 返回节气是相应月份的第几天
     */
    public static int getSolarTermNum(int year, String name) {

        double centuryValue = 0;// 节气的世纪值，每个节气的每个世纪值都不同
        name = name.trim().toUpperCase();
        int ordinal = SolarTermsEnum.valueOf(name).ordinal();

        int centuryIndex = -1;
        if (year >= 1901 && year <= 2000) {// 20世纪
            centuryIndex = 0;
        } else if (year >= 2001 && year <= 2100) {// 21世纪
            centuryIndex = 1;
        } else {
            throw new RuntimeException("不支持此年份：" + year
                    + "，目前只支持1901年到2100年的时间范围");
        }
        centuryValue = CENTURY_ARRAY[centuryIndex][ordinal];
        int dateNum = 0;
        /**
         * 计算 num =[Y*D+C]-L这是传说中的寿星通用公式
         * 公式解读：年数的后2位乘0.2422加C(即：centuryValue)取整数后，减闰年数
         */
        int y = year % 100;// 步骤1:取年分的后两位数
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {// 闰年
            if (ordinal == SolarTermsEnum.XIAOHAN.ordinal()
                    || ordinal == SolarTermsEnum.DAHAN.ordinal()
                    || ordinal == SolarTermsEnum.LICHUN.ordinal()
                    || ordinal == SolarTermsEnum.YUSHUI.ordinal()) {
                // 注意：凡闰年3月1日前闰年数要减一，即：L=[(Y-1)/4],因为小寒、大寒、立春、雨水这两个节气都小于3月1日,所以
                // y = y-1
                y = y - 1;// 步骤2
            }
        }
        dateNum = (int) (y * D + centuryValue) - (int) (y / 4);// 步骤3，使用公式[Y*D+C]-L计算
        dateNum += specialYearOffset(year, name);// 步骤4，加上特殊的年分的节气偏移量
        return dateNum;
    }

    /**
     * 特例,特殊的年分的节气偏移量,由于公式并不完善，所以算出的个别节气的第几天数并不准确，在此返回其偏移量
     *
     * @param year 年份
     * @param name 节气名称
     * @return 返回其偏移量
     */
    private static int specialYearOffset(int year, String name) {
        int offset = 0;
        offset += getOffset(DECREASE_OFFSETMAP, year, name, -1);
        offset += getOffset(INCREASE_OFFSETMAP, year, name, 1);

        return offset;
    }

    private static int getOffset(Map<String, Integer[]> map, int year,
                                 String name, int offset) {
        int off = 0;
        Integer[] years = map.get(name);
        if (null != years) {
            for (int i : years) {
                if (i == year) {
                    off = offset;
                    break;
                }
            }
        }
        return off;
    }

    private static int mYear;
    private static List<String> mSolarData = new ArrayList<String>();
    private static List<String> mSolarName = new ArrayList<String>();

    /**
     * 判断一天是什么节气  公历日期
     *
     * @param year 年
     * @param data 月份占两位，日不确定，如一月一日为：011，五月十日为0510
     * @return 节气，不是节气返回null
     */
    public static String getSolarName(int year, String data) {
        if (year != mYear) {
            solarTermToString(year);
        }
        if (mSolarData.contains(data)) {
            return mSolarName.get(mSolarData.indexOf(data));
        } else {
            return null;
        }
    }

    /**
     * 获取季节
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return 返回季节
     */
    public static int getSeason(int year, int month, int day) {
        int season = 0;
        if (mSolarData != null && mSolarData.size() > 0) {
            try {
                String liChun = mSolarData.get(0);
                String liXia = mSolarData.get(6);
                String liQiu = mSolarData.get(12);
                String liDong = mSolarData.get(18);
                boolean isAfterLiChun = isAfterSeason(month, day, liChun);
                boolean isAfterLiXia = isAfterSeason(month, day, liXia);
                boolean isAfterLiQiu = isAfterSeason(month, day, liQiu);
                boolean isAfterLiDong = isAfterSeason(month, day, liDong);
                if (isAfterLiChun && !isAfterLiXia) {
                    return Const.CHUN;
                } else if (isAfterLiXia && !isAfterLiQiu) {
                    return Const.XIA;
                } else if (isAfterLiQiu && !isAfterLiDong) {
                    return Const.QIU;
                } else {
                    return Const.DONG;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return season;
    }

    /**
     * 判断日期是否在节气之后
     *
     * @param month  月
     * @param day    日
     * @param season 节气
     * @return 是否在节气之后
     */
    public static boolean isAfterSeason(int month, int day, String season) {
        try {
            String sMonth, sDay;
            if (season.startsWith("0")) {
                season = season.substring(1);
                sMonth = season.substring(0, 1);
                sDay = season.substring(1);
            } else {
                sMonth = season.substring(0, 2);
                sDay = season.substring(2);
            }
            Log.d(TAG, "isAfterSeason sMonth:" + sMonth + " sDay:" + sDay);
            int iMonth = Integer.parseInt(sMonth);
            int iDay = Integer.parseInt(sDay);
            if (month > iMonth || (month == iMonth && day > iDay)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void solarTermToString(int year) {
        mYear = year;
        if (mSolarData != null) {
            mSolarData.clear();
        } else {
            mSolarData = new ArrayList<String>();
        }
        if (mSolarName != null) {
            mSolarName.clear();
        } else {
            mSolarName = new ArrayList<String>();
        }
        // 1
        mSolarName.add("立春");
        mSolarData.add("02" + getSolarTermNum(year, SolarTermsEnum.LICHUN.name()));
        // 2
        mSolarName.add("雨水");
        mSolarData.add("02" + getSolarTermNum(year, SolarTermsEnum.YUSHUI.name()));
        // 3
        mSolarName.add("惊蛰");
        mSolarData.add("03" + getSolarTermNum(year, SolarTermsEnum.JINGZHE.name()));
        // 4
        mSolarName.add("春分");
        mSolarData.add("03" + getSolarTermNum(year, SolarTermsEnum.CHUNFEN.name()));
        // 5
        mSolarName.add("清明");
        mSolarData.add("04" + getSolarTermNum(year, SolarTermsEnum.QINGMING.name()));
        // 6
        mSolarName.add("谷雨");
        mSolarData.add("04" + getSolarTermNum(year, SolarTermsEnum.GUYU.name()));
        // 7
        mSolarName.add("立夏");
        mSolarData.add("05" + getSolarTermNum(year, SolarTermsEnum.LIXIA.name()));
        // 8
        mSolarName.add("小满");
        mSolarData.add("05" + getSolarTermNum(year, SolarTermsEnum.XIAOMAN.name()));
        // 9
        mSolarName.add("芒种");
        mSolarData.add("06" + getSolarTermNum(year, SolarTermsEnum.MANGZHONG.name()));
        // 10
        mSolarName.add("夏至");
        mSolarData.add("06" + getSolarTermNum(year, SolarTermsEnum.XIAZHI.name()));
        // 11
        mSolarName.add("小暑");
        mSolarData.add("07" + getSolarTermNum(year, SolarTermsEnum.XIAOSHU.name()));
        // 12
        mSolarName.add("大暑");
        mSolarData.add("07" + getSolarTermNum(year, SolarTermsEnum.DASHU.name()));
        // 13
        mSolarName.add("立秋");
        mSolarData.add("08" + getSolarTermNum(year, SolarTermsEnum.LIQIU.name()));
        // 14
        mSolarName.add("处暑");
        mSolarData.add("08" + getSolarTermNum(year, SolarTermsEnum.CHUSHU.name()));
        // 15
        mSolarName.add("白露");
        mSolarData.add("09" + getSolarTermNum(year, SolarTermsEnum.BAILU.name()));
        // 16
        mSolarName.add("秋分");
        mSolarData.add("09" + getSolarTermNum(year, SolarTermsEnum.QIUFEN.name()));
        // 17
        mSolarName.add("寒露");
        mSolarData.add("10" + getSolarTermNum(year, SolarTermsEnum.HANLU.name()));
        // 18
        mSolarName.add("霜降");
        mSolarData.add("10" + getSolarTermNum(year, SolarTermsEnum.SHUANGJIANG.name()));
        // 19
        mSolarName.add("立冬");
        mSolarData.add("11" + getSolarTermNum(year, SolarTermsEnum.LIDONG.name()));
        // 20
        mSolarName.add("小雪");
        mSolarData.add("11" + getSolarTermNum(year, SolarTermsEnum.XIAOXUE.name()));
        // 21
        mSolarName.add("大雪");
        mSolarData.add("12" + getSolarTermNum(year, SolarTermsEnum.DAXUE.name()));
        // 22
        mSolarName.add("冬至");
        mSolarData.add("12" + getSolarTermNum(year, SolarTermsEnum.DONGZHI.name()));
        // 23
        mSolarName.add("小寒");
        mSolarData.add("01" + getSolarTermNum(year, SolarTermsEnum.XIAOHAN.name()));
        // 24
        mSolarName.add("大寒");
        mSolarData.add("01" + getSolarTermNum(year, SolarTermsEnum.DAHAN.name()));

    }

    /**
     * 获取第一次大运的年龄
     *
     * @param calendar  日期
     * @param siZhuData 四柱信息
     * @param sex       性别
     * @return 大运年龄
     * ToDo 跨年边界问题
     */
    public static int getDaYunAge(Calendar calendar, SiZhuData siZhuData, int sex) {
        int year = calendar.get(Calendar.YEAR);
        int realMonth = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayInYear = calendar.get(Calendar.DAY_OF_YEAR);
        String countType = getConutType(siZhuData, sex);
        JieQiBean jieQiBean = getSolarTermData(year);
        JieQiBean.JieQi preJieQi = getPreJieQi(year);
        JieQiBean.JieQi nextJieQi = getNextPreJieQi(year);
        List<JieQiBean.JieQi> jieQiList = jieQiBean.getJieQiList();
        for (int i = 0; i < jieQiList.size(); i++) {
            int tempMonth = jieQiList.get(i).getMonth();
            int tempDay = jieQiList.get(i).getDayCountInMonth();
            int interval;
            int remainder;
            int result;
            if (tempMonth == (realMonth + 1)) {
                if (tempDay > day && countType.equals(Const.FORWARD)) {
                    interval = tempDay - day;
                    remainder = interval % 3;
                    result = interval / 3;
                    if (remainder > 1) {
                        result++;
                    }
                    if (result == 0) {
                        result++;
                    }
                    return result;
                } else if (tempDay < day && countType.equals(Const.FORWARD)) {
                    if (i + 1 >= jieQiList.size()) {
                        return 0;
                    }
                    tempDay = jieQiList.get(i + 1).getDayCountInMonth();
                    Calendar tempCalendar = Calendar.getInstance();
                    tempCalendar.set(year, tempMonth, tempDay);
                    int tempDayInYear = tempCalendar.get(Calendar.DAY_OF_YEAR);
                    interval = tempDayInYear - dayInYear;
                    remainder = interval % 3;
                    result = interval / 3;
                    if (remainder > 1) {
                        result++;
                    }
                    if (result == 0) {
                        result++;
                    }
                    return result;
                } else if (tempDay > day && countType.equals(Const.BACKWARD)) {
                    if (i - 1 < 0) {
                        return 0;
                    }
                    tempDay = jieQiList.get(i - 1).getDayCountInMonth();
                    Calendar tempCalendar = Calendar.getInstance();
                    tempCalendar.set(year, tempMonth - 2, tempDay);
                    int tempDayInYear = tempCalendar.get(Calendar.DAY_OF_YEAR);
                    interval = dayInYear - tempDayInYear;
                    remainder = interval % 3;
                    result = interval / 3;
                    if (remainder > 1) {
                        result++;
                    }
                    if (result == 0) {
                        result++;
                    }
                    return result;
                } else if (tempDay < day && countType.equals(Const.BACKWARD)) {
                    interval = day - tempDay;
                    remainder = interval % 3;
                    result = interval / 3;
                    if (remainder > 1) {
                        result++;
                    }
                    if (result == 0) {
                        result++;
                    }
                    return result;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    /**
     * 获取本年度前一年的最后一个节气
     *
     * @param year 当前年
     * @return 当前年的节气列表
     */
    public static JieQiBean.JieQi getPreJieQi(int year) {
        JieQiBean.JieQi jieQi = new JieQiBean.JieQi();
        jieQi.setJieqiName("小寒");
        jieQi.setMonth(1);
        jieQi.setDayCountInMonth(getSolarTermNum(year - 1, SolarTermsEnum.XIAOHAN.name()));
        return jieQi;
    }

    /**
     * 获取下一年第一个节气
     *
     * @param year 当前年
     * @return 当前年的节气列表
     */
    public static JieQiBean.JieQi getNextPreJieQi(int year) {
        JieQiBean.JieQi jieQi = new JieQiBean.JieQi();
        jieQi.setYear(year);
        jieQi.setJieqiName("立春");
        jieQi.setMonth(2);
        jieQi.setDayCountInMonth(getSolarTermNum(year + 1, SolarTermsEnum.LICHUN.name()));
        return jieQi;
    }

    /**
     * 获取本年度需要的12个节气的日期
     *
     * @param year 当前年
     * @return 当前年的节气列表
     */
    public static JieQiBean getSolarTermData(int year) {
        mYear = year;
        List<JieQiBean.JieQi> jieQiList = new ArrayList<>();
        JieQiBean jieQiBean = new JieQiBean();
        JieQiBean.JieQi jieQi = new JieQiBean.JieQi();
        JieQiBean.JieQi jieQi2 = new JieQiBean.JieQi();
        JieQiBean.JieQi jieQi3 = new JieQiBean.JieQi();
        JieQiBean.JieQi jieQi4 = new JieQiBean.JieQi();
        JieQiBean.JieQi jieQi5 = new JieQiBean.JieQi();
        JieQiBean.JieQi jieQi6 = new JieQiBean.JieQi();
        JieQiBean.JieQi jieQi7 = new JieQiBean.JieQi();
        JieQiBean.JieQi jieQi8 = new JieQiBean.JieQi();
        JieQiBean.JieQi jieQi9 = new JieQiBean.JieQi();
        JieQiBean.JieQi jieQi10 = new JieQiBean.JieQi();
        JieQiBean.JieQi jieQi11 = new JieQiBean.JieQi();
        JieQiBean.JieQi jieQi12 = new JieQiBean.JieQi();
        jieQi2.setYear(year);
        jieQi2.setJieqiName("立春");
        jieQi2.setMonth(2);
        jieQi2.setDayCountInMonth(getSolarTermNum(year, SolarTermsEnum.LICHUN.name()));
        jieQiList.add(jieQi2);
        jieQi3.setJieqiName("惊蛰");
        jieQi3.setMonth(3);
        jieQi3.setDayCountInMonth(getSolarTermNum(year, SolarTermsEnum.JINGZHE.name()));
        jieQiList.add(jieQi3);
        jieQi4.setJieqiName("清明");
        jieQi4.setMonth(4);
        jieQi4.setDayCountInMonth(getSolarTermNum(year, SolarTermsEnum.QINGMING.name()));
        jieQiList.add(jieQi4);
        jieQi5.setJieqiName("立夏");
        jieQi5.setMonth(5);
        jieQi5.setDayCountInMonth(getSolarTermNum(year, SolarTermsEnum.LIXIA.name()));
        jieQiList.add(jieQi5);
        jieQi6.setJieqiName("芒种");
        jieQi6.setMonth(6);
        jieQi6.setDayCountInMonth(getSolarTermNum(year, SolarTermsEnum.MANGZHONG.name()));
        jieQiList.add(jieQi6);
        jieQi7.setJieqiName("小暑");
        jieQi7.setMonth(7);
        jieQi7.setDayCountInMonth(getSolarTermNum(year, SolarTermsEnum.XIAOSHU.name()));
        jieQiList.add(jieQi7);
        jieQi8.setJieqiName("立秋");
        jieQi8.setMonth(8);
        jieQi8.setDayCountInMonth(getSolarTermNum(year, SolarTermsEnum.LIQIU.name()));
        jieQiList.add(jieQi8);
        jieQi9.setJieqiName("白露");
        jieQi9.setMonth(9);
        jieQi9.setDayCountInMonth(getSolarTermNum(year, SolarTermsEnum.BAILU.name()));
        jieQiList.add(jieQi9);
        jieQi10.setJieqiName("寒露");
        jieQi10.setMonth(10);
        jieQi10.setDayCountInMonth(getSolarTermNum(year, SolarTermsEnum.HANLU.name()));
        jieQiList.add(jieQi10);
        jieQi11.setJieqiName("立冬");
        jieQi11.setMonth(11);
        jieQi11.setDayCountInMonth(getSolarTermNum(year, SolarTermsEnum.LIDONG.name()));
        jieQiList.add(jieQi11);
        jieQi12.setJieqiName("大雪");
        jieQi12.setMonth(12);
        jieQi12.setDayCountInMonth(getSolarTermNum(year, SolarTermsEnum.DAXUE.name()));
        jieQiList.add(jieQi12);
        jieQi.setJieqiName("小寒");
        jieQi.setMonth(1);
        jieQi.setDayCountInMonth(getSolarTermNum(year, SolarTermsEnum.XIAOHAN.name()));
        jieQiList.add(jieQi);
        jieQiBean.setJieQiList(jieQiList);
        return jieQiBean;
    }

    /**
     * 获取计算大运顺排运还是逆排运
     *
     * @param siZhuData 四柱
     * @param sex       性别
     * @return 顺排运还是逆排运
     */
    public static String getConutType(SiZhuData siZhuData, int sex) {
        String yearType = getYearType(siZhuData);
        if ((sex == Const.MAN & yearType.equals(Const.YANG)) || (sex == Const.WOMAN & yearType.equals(Const.YIN))) {
            return Const.FORWARD;
        } else {
            return Const.BACKWARD;
        }
    }

    /**
     * 获取是阴年还是阳年
     *
     * @param siZhuData 四柱
     * @return 阳年或者阴年
     */
    public static String getYearType(SiZhuData siZhuData) {
        String nianZhu = siZhuData.getNianZhu();
        if (nianZhu.contains("子") || nianZhu.contains("寅") || nianZhu.contains("辰") || nianZhu.contains("午") || nianZhu.contains("申") || nianZhu.contains("戌")) {
            return Const.YANG;
        } else {
            return Const.YIN;
        }
    }

    /**
     * 获取喜用神
     *
     * @param season     季节
     * @param wuXingList 五行列表
     * @return 喜用神
     */
    public static String getXiYongShen(int season, List<String> wuXingList) {
        String xiYongShen = "unknown";
        int jinSize = 0;
        int muSize = 0;
        int shuiSize = 0;
        int huoSize = 0;
        int tuSize = 0;
        String zhuWuxing = wuXingList.get(4);
        for (int i = 0; i < wuXingList.size(); i++) {
            String wuXing = wuXingList.get(i);
            if (wuXing.contains("金")) {
                jinSize++;
            } else if (wuXing.contains("木")) {
                muSize++;
            } else if (wuXing.contains("水")) {
                shuiSize++;
            } else if (wuXing.contains("火")) {
                huoSize++;
            } else if (wuXing.contains("土")) {
                tuSize++;
            }
        }
        if (zhuWuxing.contains("木")) {
            if (muSize < 2) {
                return Const.MU;
            }
            switch (season) {
                case Const.CHUN:
                    if (muSize == 2) {
                        return Const.SHUI;
                    }
                    if (shuiSize == 2) {
                        return Const.HUO;
                    }
                    if (huoSize == 2) {
                        return Const.JIN;
                    }
                    if (shuiSize > 2) {
                        return Const.TU;
                    }
                    if (tuSize > 2) {
                        return Const.JIN;
                    }
                    if (huoSize > 2) {
                        return Const.TU;
                    }
                    break;
                case Const.XIA:
                    if (muSize == 2) {
                        return Const.SHUI;
                    }
                    if (shuiSize == 2) {
                        return Const.TU;
                    }
                    if (tuSize == 2) {
                        return Const.JIN;
                    }
                    if (shuiSize > 2) {
                        return Const.TU;
                    }
                    if (tuSize > 2) {
                        return Const.JIN;
                    }
                    break;
                case Const.QIU:
                    if (muSize == 2) {
                        return Const.SHUI;
                    }
                    if (shuiSize == 2) {
                        return Const.TU;
                    }
                    if (tuSize == 2) {
                        return Const.JIN;
                    }
                    break;
                case Const.DONG:
                    if (muSize == 2) {
                        return Const.TU;
                    }
                    if (tuSize == 2) {
                        return Const.HUO;
                    }
                    if (huoSize == 2) {
                        return Const.SHUI;
                    }
                    if (tuSize > 2) {
                        return Const.JIN;
                    }
                    break;
            }
        } else if (zhuWuxing.contains("火")) {
            if (huoSize < 2) {
                return Const.HUO;
            }
            switch (season) {
                case Const.CHUN:
                    if (huoSize == 2) {
                        return Const.MU;
                    }
                    if (muSize == 2) {
                        return Const.SHUI;
                    }
                    if (shuiSize == 2) {
                        return Const.MU;
                    }
                    if (huoSize > 2) {
                        return Const.JIN;
                    }
                    break;
                case Const.XIA:
                    if (huoSize == 2) {
                        return Const.SHUI;
                    }
                    if (shuiSize == 2) {
                        return Const.TU;
                    }
                    if (tuSize == 2) {
                        return Const.JIN;
                    }
                    if (huoSize > 2) {
                        return Const.SHUI;
                    }
                    if (shuiSize > 2) {
                        return Const.TU;
                    }
                    if (tuSize > 2) {
                        return Const.JIN;
                    }
                    break;
                case Const.QIU:
                    if (huoSize == 2) {
                        return Const.MU;
                    }
                    if (muSize == 2) {
                        return Const.SHUI;
                    }
                    if (shuiSize == 2) {
                        return Const.HUO;
                    }
                    break;
                case Const.DONG:
                    if (huoSize == 2) {
                        return Const.MU;
                    }
                    if (muSize == 2) {
                        return Const.TU;
                    }
                    if (tuSize == 2) {
                        return Const.HUO;
                    }
                    break;
            }
        } else if (zhuWuxing.contains("土")) {
            if (tuSize < 2) {
                return Const.TU;
            }
            switch (season) {
                case Const.CHUN:
                    if (tuSize == 2) {
                        return Const.HUO;
                    }
                    if (huoSize == 2) {
                        return Const.JIN;
                    }
                    if (jinSize == 2) {
                        return Const.SHUI;
                    }
                    if (huoSize > 2) {
                        return Const.JIN;
                    }
                    break;
                case Const.XIA:
                    if (tuSize == 2) {
                        return Const.SHUI;
                    }
                    if (shuiSize == 2) {
                        return Const.JIN;
                    }
                    if (jinSize == 2) {
                        return Const.MU;
                    }
                    if (tuSize > 2) {
                        return Const.SHUI;
                    }
                    if (shuiSize > 2) {
                        return Const.MU;
                    }
                    break;
                case Const.QIU:
                    if (tuSize == 2) {
                        return Const.MU;
                    }
                    if (tuSize > 2) {
                        return Const.HUO;
                    }
                    break;
                case Const.DONG:
                    if (tuSize == 2) {
                        return Const.SHUI;
                    }
                    if (shuiSize == 2) {
                        return Const.JIN;
                    }
                    if (jinSize == 2) {
                        return Const.HUO;
                    }
                    if (tuSize > 2) {
                        return Const.SHUI;
                    }
                    break;
            }
        } else if (zhuWuxing.contains("金")) {
            if (jinSize < 2) {
                return Const.JIN;
            }
            switch (season) {
                case Const.CHUN:
                    if (jinSize == 2) {
                        return Const.HUO;
                    }
                    if (huoSize == 2) {
                        return Const.TU;
                    }
                    break;
                case Const.XIA:
                    if (jinSize == 2) {
                        return Const.SHUI;
                    }
                    break;
                case Const.QIU:
                    if (jinSize == 2) {
                        return Const.HUO;
                    }
                    if (huoSize == 2) {
                        return Const.SHUI;
                    }
                    if (shuiSize == 2) {
                        return Const.MU;
                    }
                    break;
                case Const.DONG:
                    if (jinSize == 2) {
                        return Const.TU;
                    }
                    if (tuSize == 2) {
                        return Const.HUO;
                    }
                    if (huoSize == 2) {
                        return Const.SHUI;
                    }
                    break;
            }
        } else if (zhuWuxing.contains("水")) {
            if (shuiSize < 2) {
                return Const.SHUI;
            }
            switch (season) {
                case Const.CHUN:
                    if (shuiSize == 2) {
                        return Const.TU;
                    }
                    if (tuSize == 2) {
                        return Const.HUO;
                    }
                    if (huoSize == 2) {
                        return Const.MU;
                    }
                    break;
                case Const.XIA:
                    if (shuiSize == 2) {
                        return Const.JIN;
                    }
                    break;
                case Const.QIU:
                    if (shuiSize == 2) {
                        return Const.JIN;
                    }
                    if (jinSize == 2) {
                        return Const.HUO;
                    }
                    if (huoSize == 2) {
                        return Const.MU;
                    }
                    if (shuiSize > 2) {
                        return Const.TU;
                    }
                    break;
                case Const.DONG:
                    if (shuiSize == 2) {
                        return Const.HUO;
                    }
                    if (huoSize == 2) {
                        return Const.TU;
                    }
                    if (tuSize == 2) {
                        return Const.MU;
                    }
                    if (shuiSize > 2) {
                        return Const.TU;
                    }
                    break;
            }
        }

        return xiYongShen;
    }

    /**
     * 获取十神
     *
     * @param siZhuData 四柱
     * @return 喜用神
     */
    public static ShiShenBean getShiShen(SiZhuData siZhuData) {
        String nianGan = siZhuData.getNianZhu().substring(0, 1);
        String yueGan = siZhuData.getYueZhu().substring(0, 1);
        String riGan = siZhuData.getRiZhu().substring(0, 1);
        String shiGan = siZhuData.getShiZhu().substring(0, 1);
        int yearIndex = getTianGanPosition(nianGan);
        int monthIndex = getTianGanPosition(yueGan);
        int dayIndex = getTianGanPosition(riGan);
        int hourIndex = getTianGanPosition(shiGan);
        ShiShenBean shiShenBean = new ShiShenBean();
        shiShenBean.setNianShiShen(shiShens[dayIndex][yearIndex]);
        shiShenBean.setYueShiShen(shiShens[dayIndex][monthIndex]);
        shiShenBean.setShiShiShen(shiShens[dayIndex][hourIndex]);
        return shiShenBean;
    }

    /**
     * 获取十二宫
     *
     * @param siZhuData 四柱
     * @return 十二宫
     */
    public static ShiErGongBean getShiErGong(SiZhuData siZhuData) {
        String riGan = siZhuData.getRiZhu().substring(0, 1);

        String nianZhi = siZhuData.getNianZhu().substring(1, 2);
        String yueZhi = siZhuData.getYueZhu().substring(1, 2);
        String riZhi = siZhuData.getRiZhu().substring(1, 2);
        String shiZhi = siZhuData.getShiZhu().substring(1, 2);

        ShiErGongBean shiErGongBean = new ShiErGongBean();
        shiErGongBean.setNianShiErGong(getSingleShiErGong(riGan, nianZhi));
        shiErGongBean.setYueShiErGong(getSingleShiErGong(riGan, yueZhi));
        shiErGongBean.setRiShiErGong(getSingleShiErGong(riGan, riZhi));
        shiErGongBean.setShiShiErGong(getSingleShiErGong(riGan, shiZhi));
        return shiErGongBean;
    }

    /**
     * 获取单个的十二宫
     *
     * @param gan 天干
     * @param zhi 地支
     * @return 十二宫
     */
    public static String getSingleShiErGong(String gan, String zhi) {
        int tianGanIndex = getShiErGongTianGanPosition(gan);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == tianGanIndex && shiErGong[i][j].equals(zhi)) {
                    return shiErGongName[i];
                }
            }
        }
        return "";
    }

    /**
     * 获取藏干十神
     *
     * @param siZhuData 四柱
     * @return 藏干十神
     */
    public static CangGanShiShenBean CangGanShiShen(SiZhuData siZhuData) {
        CangGanShiShenBean cangGanShiShenBean = new CangGanShiShenBean(siZhuData);
        return cangGanShiShenBean;
    }

    /**
     * 获取十二宫天干的位置
     *
     * @param gan 天干
     * @return 位置
     */
    public static int getShiErGongTianGanPosition(String gan) {
        for (int i = 0; i < shiErGongTianGan.length; i++) {
            if (shiErGongTianGan[i].equals(gan)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 获取天干的位置
     *
     * @param gan 天干
     * @return 位置
     */
    public static int getTianGanPosition(String gan) {
        for (int i = 0; i < Gan.length; i++) {
            if (Gan[i].equals(gan)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 获取地支的位置
     *
     * @param dizhi 天干
     * @return 位置
     */
    public static int getDizhiPosition(String dizhi) {
        for (int i = 0; i < Zhi.length; i++) {
            if (Zhi[i].equals(dizhi)) {
                return i;
            }
        }
        return 0;
    }
}
