package com.necer.ncalendar.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

public class PaiPan {

    private int DAYUN_COUNT = 8;// 需要的大运的数目
    // ==========干支数组===================
    final String[] Gan = new String[]{"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    final String[] Zhi = new String[]{"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    final String[] NaYin = new String[]{"海中金", "海中金", "炉中火", "炉中火", "大林木", "大林木", "路旁土", "路旁土", "剑锋金", "剑锋金", "山头火",
            "山头火", "涧下水", "涧下水", "城头土", "城头土", "白腊金", "白腊金", "杨柳木", "杨柳木", "泉中水", "泉中水", "屋上土", "屋上土", "霹雳火", "霹雳火",
            "松柏木", "松柏木", "长流水", "长流水", "沙中金", "沙中金", "山下火", "山下火", "平地木", "平地木", "壁上土", "壁上土", "金箔金", "金箔金", "佛灯火",
            "佛灯火", "天河水", "天河水", "大驿土", "大驿土", "钗钏金", "钗钏金", "桑松木", "桑松木", "大溪水", "大溪水", "沙中土", "沙中土", "天上火", "天上火",
            "石榴木", "石榴木", "大海水", "大海水"};
    public final String[][] nayinArray = {
            //干支，纳音，岁数，男，女
            {"丙戌", "屋上土", "1", "3", "3"},
            {"乙酉", "泉中水", "2", "4", "2"},
            {"甲申", "泉中水", "3", "5", "1"},
            {"癸未", "杨柳木", "4", "6", "9"},
            {"壬午", "杨柳木", "5", "7", "8"},
            {"辛巳", "白蜡金", "6", "8", "7"},
            {"庚辰", "白蜡金", "7", "9", "6"},
            {"己卯", "城墙土", "8", "1", "5"},
            {"戊寅", "城墙土", "9", "2", "4"},
            {"丁丑", "涧下水", "10", "3", "3"},
            {"丙子", "涧下水", "11", "4", "2"},
            {"乙亥", "山头火", "12", "5", "1"},
            {"甲戌", "山头火", "13", "6", "9"},
            {"癸酉", "剑锋金", "14", "7", "8"},
            {"壬申", "剑锋金", "15", "8", "7"},
            {"辛未", "路旁土", "16", "9", "6"},
            {"庚午", "路旁土", "17", "1", "5"},
            {"己巳", "大林木", "18", "2", "4"},
            {"戊辰", "大林木", "19", "3", "3"},
            {"丁卯", "炉中火", "20", "4", "2"},
            {"丙寅", "炉中火", "21", "5", "1"},
            {"乙丑", "海中金", "22", "6", "9"},
            {"甲子", "海中金", "23", "7", "8"},
            {"癸亥", "大海水", "24", "8", "7"},
            {"壬戌", "大海水", "25", "9", "6"},
            {"辛酉", "石榴木", "26", "1", "5"},
            {"庚申", "石榴木", "27", "2", "4"},
            {"己未", "天上火", "28", "3", "3"},
            {"戊午", "天上火", "29", "4", "2"},
            {"丁巳", "沙中土", "30", "5", "1"},
            {"丙辰", "沙中土", "31", "6", "9"},
            {"乙卯", "大溪水", "32", "7", "8"},
            {"甲寅", "大溪水", "33", "8", "7"},
            {"癸丑", "桑松木", "34", "9", "6"},
            {"壬子", "桑松木", "35", "1", "5"},
            {"辛亥", "钗钏金", "36", "2", "4"},
            {"庚戌", "钗钏金", "37", "3", "3"},
            {"己酉", "大驿土", "38", "4", "2"},
            {"戊申", "大驿土", "39", "5", "1"},
            {"丁未", "天河水", "40", "6", "9"},
            {"丙午", "天河水", "41", "7", "8"},
            {"乙巳", "佛灯火", "42", "8", "7"},
            {"甲辰", "佛灯火", "43", "9", "6"},
            {"癸卯", "金箔金", "44", "1", "5"},
            {"壬寅", "金箔金", "45", "2", "4"},
            {"辛丑", "壁上土", "46", "3", "3"},
            {"庚子", "壁上土", "47", "4", "2"},
            {"己亥", "平地木", "48", "5", "1"},
            {"戊戌", "平地木", "49", "6", "9"},
            {"丁酉", "山下火", "50", "7", "8"},
            {"丙申", "山下火", "51", "8", "7"},
            {"乙未", "沙中金", "52", "9", "6"},
            {"甲午", "沙中金", "53", "1", "5"},
            {"癸巳", "长流水", "54", "2", "4"},
            {"壬辰", "长流水", "55", "3", "3"},
            {"辛卯", "松柏木", "56", "4", "2"},
            {"庚寅", "松柏木", "57", "5", "1"},
            {"己丑", "霹雳火", "58", "6", "9"},
            {"戊子", "霹雳火", "59", "7", "8"},
            {"丁亥", "屋上土", "60", "8", "7"},};

    public final String[] otherNayin = new String[]{
            "甲子甲木子中归水",
            "乙丑乙木丑中己土",
            "丙寅丙火寅中甲木",
            "丁卯丁火卯中乙木",
            "戊辰戊土辰中戊土",
            "己已己土已中丁火",
            "庚午庚金午中丁火",
            "辛未辛金未中己土",
            "壬申壬水申下庚金",
            "癸酉癸水酉下辛金",
            "甲戌甲木戌中午土",
            "乙亥乙木亥中癸水",
            "丙子丙火子中癸水",
            "丁丑丁火丑中己土",
            "戊寅午土寅中甲木",
            "己卯己土卯中乙木",
            "庚辰庚金辰中午土",
            "辛已辛金已中丁火",
            "壬午壬水午中丙火",
            "癸未癸水未中己土",
            "甲申甲木申下庚金",
            "乙酉乙木酉中辛金",
            "丙戌丙火戌中午土",
            "丁亥丁火亥中癸水",
            "戊子午土子中壬水",
            "己丑己土丑中己土",
            "庚寅庚金寅中甲木",
            "辛卯辛金卯中乙木",
            "壬辰壬水辰中午土",
            "癸已癸水已中丁水",
            "甲午甲木午中丙火",
            "乙未乙木未中己土",
            "丙申丙火申下庚金",
            "丁酉丁火酉中辛金",
            "戌戊午土戊中午土",
            "己亥己土亥中癸水",
            "庚子庚金子中壬水",
            "辛丑辛金丑中己土",
            "壬寅壬水寅中甲木",
            "癸卯癸水卯中乙木",
            "甲辰甲木辰中午土",
            "乙已乙木已中丁火",
            "丙午丙火午中丙火",
            "丁未丁火未中己土",
            "午申午土申下庚金",
            "己酉己土酉中辛金",
            "庚戊庚金戊中午土",
            "辛亥辛金亥中癸水",
            "壬子壬水子中壬水",
            "癸丑癸水丑中己土",
            "甲寅甲木寅中甲木",
            "乙卯乙木卯中乙木",
            "丙辰丙火辰中午土",
            "丁已丁火已中己土",
            "午午午土午中丙火",
            "己未己土未中己土",
            "庚申庚金申下庚金",
            "辛酉辛金酉中辛金",
            "壬戊壬水戊中午土",
            "癸亥癸水亥中癸水",
    };
    // 计算干支的偏移值
    private int yearCyl, dayCyl, hourCyl;
    private String monthGanZhi = null;
    // 三种时间格式化格式
    static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH");
    static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    static SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 构造函数
     *
     * @param cal
     */
    public PaiPan(Calendar cal) {

        Date baseDate = null;
        try {
            baseDate = sdf1.parse("1900-1-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        yearCyl = getCheckedYear(cal) - 1864;

        // 求出月的干支
        monthGanZhi = getMonthGanZhiString(cal, getCheckedYear(cal));
        // 求出日和时的偏移值
        int offset = (int) ((cal.getTime().getTime() - baseDate.getTime()) / 86400000L);
        dayCyl = offset + 40;
        hourCyl = (int) ((cal.getTime().getTime() - baseDate.getTime() + 3300000L) / 7200000L);

    }

    /**
     * 输出四柱
     *
     * @return
     */
    public String getSiZhuString() {// 返回四柱
        return cyclicalm(yearCyl) + monthGanZhi + cyclicalm(dayCyl) + cyclicalm(hourCyl);
    }

    /**
     * 获取4柱
     *
     * @return
     */
    public SiZhuData getSiZhuData() {
        SiZhuData siZhuData = new SiZhuData();
        siZhuData.setNianZhu(cyclicalm(yearCyl));
        siZhuData.setYueZhu(monthGanZhi);
        siZhuData.setRiZhu(cyclicalm(dayCyl));
        siZhuData.setShiZhu(cyclicalm(hourCyl));
        return siZhuData;
    }

    /**
     * 获取四柱对应的纳音
     *
     * @return
     */
    public String getNaYin(String sizhu) {
        for (int i = 0; i < nayinArray.length; i++) {
            if (nayinArray[i][0].equals(sizhu)) {
                return nayinArray[i][1];
            }
        }
        return "";
    }

    public String getOtherNayin(String sizhu) {
        for (int i = 0; i < otherNayin.length; i++) {
            if (otherNayin[i].contains(sizhu)) {
                return otherNayin[i];
            }
        }
        return "";
    }

    /**
     * 传入 月日的offset 传回干支, 0=甲子
     *
     * @param num
     * @return
     */
    final private String cyclicalm(int num) {
        return (Gan[num % 10] + Zhi[num % 12]);
    }

    /**
     * 输入日干支，返回空亡
     *
     * @param rigan
     * @return
     */
    public String getKWString(String rigan) {
        int gan = 0, zhi = 0, kong = 0;
        gan = getGanPosition(String.valueOf(rigan.charAt(0)));
        zhi = getZhiPosition(String.valueOf(rigan.charAt(1)));

        kong = (10 - gan - (12 - zhi) + 12) % 12;
        String kw = Zhi[kong] + Zhi[kong + 1];
        return kw;
    }

    /**
     * 输入性别和年干，返回排大运的方向
     *
     * @param gender  1、男 0、女
     * @param yeargan
     * @return
     */
    public int getPaiDaYunDir(int gender, String yeargan) {

        int dir = 1;
        int yg = getGanPosition(yeargan);
        if ((yg % 2 == 0 && gender == 1) || (yg % 2 == 1 && gender == 0)) {// 男阳/女阴
            dir = 1;
        } else if ((yg % 2 == 1 && gender == 1) || (yg % 2 == 0 && gender == 0)) {// 男阴/女阳
            dir = -1;
        }
        return dir;
    }

    /**
     * 排大运方法
     *
     * @param gender
     * @param yearganzhi
     * @param monthganzhi
     * @return
     */
    public String[] getDaYunString(int gender, String yearganzhi, String monthganzhi) {

        int dir = 1, monthgan = 0, i, monthzhi = 0;
        String[] daYun = new String[DAYUN_COUNT];

        dir = getPaiDaYunDir(gender, String.valueOf(yearganzhi.charAt(0)));

        monthgan = getGanPosition(String.valueOf(monthganzhi.charAt(0)));
        monthzhi = getZhiPosition(String.valueOf(monthganzhi.charAt(1)));
        // Log.e("tag", "transfer-->"+monthzhi);
        // Log.e("tag", "transfer-->"+monthganzhi.charAt(1));

        if (dir == 1) {
            for (i = 0; i < DAYUN_COUNT; i++) {
                daYun[i] = Gan[(++monthgan) % 10].toString() + (Zhi[(++monthzhi) % 12].toString());
            }
        } else {
            for (i = 0; i < DAYUN_COUNT; i++) {
                daYun[i] = Gan[((--monthgan) + 10) % 10].toString() + (Zhi[((--monthzhi) + 12) % 12].toString());
                // Log.e("tag", "dayundizhi-->"+(Zhi[((monthzhi) + 12) %
                // 12].toString()));
            }
        }

        return daYun;
    }

    /**
     * 返回输入的地支所对应的位置
     *
     * @param gan
     * @return
     */
    public int getGanPosition(String gan) {// 返回输入的天干所对应的位置

        int ganBack = 0, i = 0;
        for (i = 0; i < 10; i++) {
            if (Gan[i].toString().equals(gan)) {
                ganBack = i;
                break;
            } else {
                continue;
            }
        }
        return ganBack;
    }

    /**
     * 返回输入的地支所对应的位置
     *
     * @param zhi
     * @return
     */
    public int getZhiPosition(String zhi) {

        int zhiBack = 0, i = 0;
        for (i = 0; i < 12; i++) {
            if (Zhi[i].toString().equals(zhi)) {
                zhiBack = i;
                break;
            } else {
                continue;
            }
        }
        return zhiBack;
    }

    /**
     * 输入日期，返回月干支
     *
     * @param cal
     * @param year
     * @return
     */
    public String getMonthGanZhiString(Calendar cal, int year) {

        String monthGan = "丙戊庚壬甲";
        String monBeginGan = String.valueOf(monthGan.charAt((year - 1864) % 5));

        int gan = getGanPosition(monBeginGan);
        int zhi = getZhiPosition("寅");

        String[] monthDate = getWholeYearJieQis(year);

        // 创建当年的所有月的干支字符数组monthGanZhis
        String[] monthGanZhis = new String[12];
        for (int i = 0; i < 12; i++) {
            monthGanZhis[i] = Gan[(gan++) % 10] + Zhi[(zhi++) % 12];
        }

        return monthGanZhis[getPostionOfTheYear(cal, monthDate)];

    }

    /**
     * 输入校对后的年份，返回当年的所有节气的日期（立春~大寒）
     *
     * @param year
     * @return
     */
    public String[] getWholeYearJieQis(int year) {

        SolarTerm solarTerm = new SolarTerm();
        String[] nowYear = solarTerm.getLunarJieQisDateOfTheYear(year);
        String[] nextYear = solarTerm.getLunarJieQisDateOfTheYear(year + 1);

        String[] monthDate = new String[12];
        for (int i = 2; i < 12; i++) {
            monthDate[i - 2] = nowYear[i];
        }
        monthDate[10] = nextYear[0];
        monthDate[11] = nextYear[1];

        return monthDate;
    }

    /**
     * 返回立春日历时间
     *
     * @param year
     * @return
     */
    public Calendar getLiChunCalendar(int year) {
        SolarTerm stLiChun = new SolarTerm();
        Calendar calLiChun = Calendar.getInstance();
        try {
            calLiChun.setTime(sdf3.parse(stLiChun.getLiChunString(year)));// 找出当年立春时间

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calLiChun;
    }

    /**
     * 输入输入日期和当年节气的数组，返回所在节气的位置
     *
     * @param calInput
     * @param jqStrings
     * @return
     */
    public int getPostionOfTheYear(Calendar calInput, String[] jqStrings) {

        int outPut = 0, i = 0;
        String timeString = null;
        Calendar calTime = Calendar.getInstance();// 一个一个取出节气数组中的日期进行比较
        long inputTime = calInput.getTimeInMillis();
        long jqTime = 0;// 一个一个取出节气数组中的日期变为长整形数据进行比较

        for (i = 0; i < jqStrings.length; i++) {

            timeString = jqStrings[i];
            try {
                calTime.setTime(sdf4.parse(timeString));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            jqTime = calTime.getTimeInMillis();

            if (jqTime < inputTime) {
                continue;
            } else {
                break;
            }
        }
        outPut = i - 1;

        return outPut;

    }

    /**
     * 输入日期，返回校正后的年份
     *
     * @param cal
     * @return
     */
    final public int getCheckedYear(Calendar cal) {

        Calendar calLiChun = getLiChunCalendar(cal.get(Calendar.YEAR));
        return (cal.getTimeInMillis() > calLiChun.getTimeInMillis()) ? calLiChun.get(Calendar.YEAR)
                : (calLiChun.get(Calendar.YEAR) - 1);
    }

    /**
     * 输入日期返回相应的纳音
     *
     * @param cal
     * @return
     */
    final public String getNaYinString(Calendar cal) {

        Calendar calLiChun = getLiChunCalendar(cal.get(Calendar.YEAR));
        int year = (cal.getTimeInMillis() > calLiChun.getTimeInMillis()) ? calLiChun.get(Calendar.YEAR)
                : (calLiChun.get(Calendar.YEAR) - 1);

        return NaYin[(year - 1864) % 60];
    }

    // //=============================TestArea=========================================//
    // public static void main(String[] arg) {
    //
    // String birthday = 2014 + "-" + 12 + "-" + 12 + " " + 12;
    // dataCalc.SolarTerm stLiChun = new SolarTerm();
    //
    // Calendar calInput = Calendar.getInstance();
    // try {
    // calInput.setTime(sdf2.parse(birthday));
    // } catch (ParseException e) {
    // e.printStackTrace();
    // }
    //
    // PaiPan pp=new PaiPan(calInput);
    //
    // SolarTerm solarTerm=new SolarTerm();
    // String [] test=solarTerm.getLunarMonthsDateOfTheYear(2014);
    // for(int j=0;j<test.length;j++){
    // System.out.println("Here:"+test[j]);
    // }
    // }

}
