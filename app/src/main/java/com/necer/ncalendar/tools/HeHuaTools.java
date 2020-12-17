package com.necer.ncalendar.tools;

import android.util.Log;

import com.necer.ncalendar.activity.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 合化工具
 */
public class HeHuaTools {

    /**
     * 判断list a 是否包含list b
     *
     * @param aList list a
     * @param bList list b
     * @return list a 是否包含list b
     */
    private static boolean aContainsB(List<String> aList, List<String> bList) {
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
     * 地支三合化五行
     *
     * @param baZiList 八字list
     * @return 返回克合的十天干
     */
    public static String getSanHeHuaWuXing(List<String> baZiList) {
        List<String> dizhiList = new ArrayList<>();
        dizhiList.add(baZiList.get(1));
        dizhiList.add(baZiList.get(3));
        dizhiList.add(baZiList.get(5));
        dizhiList.add(baZiList.get(7));
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
        list3.add("巳");
        list3.add("酉");
        list3.add("丑");
        if (aContainsB(dizhiList, list1)) {
            result = "申子辰合水局 ";
        }
        if (aContainsB(dizhiList, list2)) {
            result += "亥卯未合木局 ";
        }
        if (aContainsB(dizhiList, list3)) {
            result += "寅午戌合火局 ";
        }
        if (aContainsB(dizhiList, list4)) {
            result += "巳酉丑合金局 ";
        }
        return result;
    }

    /**
     * 地支三会化五行
     *
     * @param baZiList 八字list
     * @return 返回克合的十天干
     */
    public static String getSanHuiHuaWuXing(List<String> baZiList) {
        List<String> dizhiList = new ArrayList<>();
        dizhiList.add(baZiList.get(1));
        dizhiList.add(baZiList.get(3));
        dizhiList.add(baZiList.get(5));
        dizhiList.add(baZiList.get(7));
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("寅");
        list1.add("卯");
        list1.add("辰");
        List<String> list2 = new ArrayList<>();
        list2.add("巳");
        list2.add("午");
        list2.add("未");
        List<String> list3 = new ArrayList<>();
        list3.add("申");
        list3.add("酉");
        list3.add("戌");
        List<String> list4 = new ArrayList<>();
        list3.add("亥");
        list3.add("子");
        list3.add("丑");
        if (aContainsB(dizhiList, list1)) {
            result += "寅卯辰三会东方木 ";
        }
        if (aContainsB(dizhiList, list2)) {
            result += "巳午未三会东方火 ";
        }
        if (aContainsB(dizhiList, list3)) {
            result += "申酉戌三会西方金 ";
        }
        if (aContainsB(dizhiList, list4)) {
            result += "亥子丑三会北方水 ";
        }
        return result;
    }

    /**
     * 天干合化五行
     * <p>
     * 甲乙合化土
     * 乙庚合化金
     * 丙辛合化水
     * 丁壬合化木
     * 戊癸合化火
     *
     * @param baZiList 八字list
     * @return 返回克合的十天干
     */
    public static String getTianGanHeHuaWuXing(List<String> baZiList) {
        String sanHe = getSanHeHuaWuXing(baZiList);
        String sanHui = getSanHuiHuaWuXing(baZiList);
        if (sanHe != null) {
            return "有三合化五行，合化不成立";
        }
        if (sanHui != null) {
            return "有三会化五行，合化不成立";
        }
        List<String> tianGanList = new ArrayList<>();
        tianGanList.add(baZiList.get(0));
        tianGanList.add(baZiList.get(2));
        tianGanList.add(baZiList.get(4));
        tianGanList.add(baZiList.get(6));
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("甲");
        list1.add("乙");
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
        if (aContainsB(tianGanList, list1) && list1.contains(baZiList.get(4))) {
            result += "甲乙合化土 ";
        }
        if (aContainsB(tianGanList, list2) && list2.contains(baZiList.get(4))) {
            result += "乙庚合化金 ";
        }
        if (aContainsB(tianGanList, list3) && list3.contains(baZiList.get(4))) {
            result += "丙辛合化水 ";
        }
        if (aContainsB(tianGanList, list4) && list4.contains(baZiList.get(4))) {
            result += "丁壬合化木 ";
        }
        if (aContainsB(tianGanList, list5) && list5.contains(baZiList.get(4))) {
            result += "戊癸合化火 ";
        }
        return result;
    }

    /**
     * 地支合化五行
     * <p>
     * 子丑合土
     * 午未合土
     * 寅亥合木
     * 卯戌合火
     * 辰酉合金
     * 巳申合水
     *
     * @param baZiList 八字list
     * @return 返回克合的十天干
     */
    public static String getDiZhiHeHuaWuXing(List<String> baZiList) {
        String sanHe = getSanHeHuaWuXing(baZiList);
        String sanHui = getSanHuiHuaWuXing(baZiList);
        if (sanHe != null) {
            return "有三合化五行，合化不成立";
        }
        if (sanHui != null) {
            return "有三会化五行，合化不成立";
        }
        List<String> dizhiList = new ArrayList<>();
        dizhiList.add(baZiList.get(1));
        dizhiList.add(baZiList.get(3));
        dizhiList.add(baZiList.get(5));
        dizhiList.add(baZiList.get(7));
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("子");
        list1.add("丑");
        List<String> list2 = new ArrayList<>();
        list2.add("午");
        list2.add("未");
        List<String> list3 = new ArrayList<>();
        list3.add("寅");
        list3.add("亥");
        List<String> list4 = new ArrayList<>();
        list4.add("卯");
        list4.add("戌");
        List<String> list5 = new ArrayList<>();
        list5.add("辰");
        list5.add("酉");
        List<String> list6 = new ArrayList<>();
        list6.add("巳");
        list6.add("申");
        if (aContainsB(dizhiList, list1) && list1.contains(baZiList.get(5))) {
            result += "子丑合土 ";
            if (list1.contains(baZiList.get(1))) {
                result += "遥合";
            }
        }
        if (aContainsB(dizhiList, list2) && list2.contains(baZiList.get(5))) {
            result += "午未合土 ";
            if (list2.contains(baZiList.get(1))) {
                result += "遥合";
            }
        }
        if (aContainsB(dizhiList, list3) && list3.contains(baZiList.get(5))) {
            result += "寅亥合木 ";
            if (list3.contains(baZiList.get(1))) {
                result += "遥合";
            }
        }
        if (aContainsB(dizhiList, list4) && list4.contains(baZiList.get(5))) {
            result += "卯戌合火 ";
            if (list4.contains(baZiList.get(1))) {
                result += "遥合";
            }
        }
        if (aContainsB(dizhiList, list5) && list5.contains(baZiList.get(5))) {
            result += "辰酉合金 ";
            if (list5.contains(baZiList.get(1))) {
                result += "遥合";
            }
        }
        if (aContainsB(dizhiList, list6) && list6.contains(baZiList.get(5))) {
            result += "巳申合水 ";
            if (list6.contains(baZiList.get(1))) {
                result += "遥合";
            }
        }
        return result;
    }

    /**
     * 获取十天干相合
     *
     * @param baZiList 八字list
     * @return 返回相合的十天干
     */
    public static String getShiTianGanXiangHe(List<String> baZiList) {
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
    public static String getShiErZhiLiuHe(List<String> baZiList) {
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
    public static String getShengHe(List<String> baZiList) {
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
    public static String getKeHe(List<String> baZiList) {
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
    public static String getShiErZhiSanHe(List<String> baZiList) {
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
    public static String getShiErZhiXiangChong(List<String> baZiList) {
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
    public static String getShiErZhiXiangChuan(List<String> baZiList) {
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
    public static String getShiErZhiXiangXing(List<String> baZiList) {
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
     * 地支三合化五行
     *
     * @param baZiList 八字list
     * @return 返回克合的十天干
     */
    public static String getSanHeHuaWuXing(List<String> baZiList, String daYunZhu) {
        List<String> dizhiList = new ArrayList<>();
        dizhiList.add(baZiList.get(1));
        dizhiList.add(baZiList.get(3));
        dizhiList.add(baZiList.get(5));
        dizhiList.add(baZiList.get(7));
        dizhiList.add(daYunZhu.substring(1));
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
        list3.add("巳");
        list3.add("酉");
        list3.add("丑");
        if (aContainsB(dizhiList, list1)) {
            result = "申子辰合水局 ";
        }
        if (aContainsB(dizhiList, list2)) {
            result += "亥卯未合木局 ";
        }
        if (aContainsB(dizhiList, list3)) {
            result += "寅午戌合火局 ";
        }
        if (aContainsB(dizhiList, list4)) {
            result += "巳酉丑合金局 ";
        }
        return result;
    }

    /**
     * 地支三会化五行
     *
     * @param baZiList 八字list
     * @return 返回克合的十天干
     */
    public static String getSanHuiHuaWuXing(List<String> baZiList, String daYunZhu) {
        List<String> dizhiList = new ArrayList<>();
        dizhiList.add(baZiList.get(1));
        dizhiList.add(baZiList.get(3));
        dizhiList.add(baZiList.get(5));
        dizhiList.add(baZiList.get(7));
        dizhiList.add(daYunZhu.substring(1));
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("寅");
        list1.add("卯");
        list1.add("辰");
        List<String> list2 = new ArrayList<>();
        list2.add("巳");
        list2.add("午");
        list2.add("未");
        List<String> list3 = new ArrayList<>();
        list3.add("申");
        list3.add("酉");
        list3.add("戌");
        List<String> list4 = new ArrayList<>();
        list3.add("亥");
        list3.add("子");
        list3.add("丑");
        if (aContainsB(dizhiList, list1)) {
            result += "寅卯辰三会东方木 ";
        }
        if (aContainsB(dizhiList, list2)) {
            result += "巳午未三会东方火 ";
        }
        if (aContainsB(dizhiList, list3)) {
            result += "申酉戌三会西方金 ";
        }
        if (aContainsB(dizhiList, list4)) {
            result += "亥子丑三会北方水 ";
        }
        return result;
    }

    /**
     * 天干合化五行
     * <p>
     * 甲乙合化土
     * 乙庚合化金
     * 丙辛合化水
     * 丁壬合化木
     * 戊癸合化火
     *
     * @param baZiList 八字list
     * @return 返回克合的十天干
     */
    public static String getTianGanHeHuaWuXing(List<String> baZiList, String daYunZhu) {
        List<String> tianGanList = new ArrayList<>();
        tianGanList.add(baZiList.get(0));
        tianGanList.add(baZiList.get(2));
        tianGanList.add(baZiList.get(4));
        tianGanList.add(baZiList.get(6));
        tianGanList.add(daYunZhu.substring(0, 1));
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("甲");
        list1.add("乙");
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
        if (aContainsB(tianGanList, list1) && list1.contains(daYunZhu.substring(0, 1))) {
            result += "甲乙合化土 ";
        }
        if (aContainsB(tianGanList, list2) && list2.contains(daYunZhu.substring(0, 1))) {
            result += "乙庚合化金 ";
        }
        if (aContainsB(tianGanList, list3) && list3.contains(daYunZhu.substring(0, 1))) {
            result += "丙辛合化水 ";
        }
        if (aContainsB(tianGanList, list4) && list4.contains(daYunZhu.substring(0, 1))) {
            result += "丁壬合化木 ";
        }
        if (aContainsB(tianGanList, list5) && list5.contains(daYunZhu.substring(0, 1))) {
            result += "戊癸合化火 ";
        }
        return result;
    }

    /**
     * 地支合化五行
     * <p>
     * 子丑合土
     * 午未合土
     * 寅亥合木
     * 卯戌合火
     * 辰酉合金
     * 巳申合水
     *
     * @param baZiList 八字list
     * @return 返回克合的十天干
     */
    public static String getDiZhiHeHuaWuXing(List<String> baZiList, String daYunZhu) {
        List<String> dizhiList = new ArrayList<>();
        dizhiList.add(baZiList.get(1));
        dizhiList.add(baZiList.get(3));
        dizhiList.add(baZiList.get(5));
        dizhiList.add(baZiList.get(7));
        dizhiList.add(daYunZhu.substring(1));
        String result = "";
        List<String> list1 = new ArrayList<>();
        list1.add("子");
        list1.add("丑");
        List<String> list2 = new ArrayList<>();
        list2.add("午");
        list2.add("未");
        List<String> list3 = new ArrayList<>();
        list3.add("寅");
        list3.add("亥");
        List<String> list4 = new ArrayList<>();
        list4.add("卯");
        list4.add("戌");
        List<String> list5 = new ArrayList<>();
        list5.add("辰");
        list5.add("酉");
        List<String> list6 = new ArrayList<>();
        list6.add("巳");
        list6.add("申");
        if (aContainsB(dizhiList, list1)) {
            result += "子丑合土 ";
            if (list1.contains(baZiList.get(1))) {
                result += "遥合 ";
            }
        }
        if (aContainsB(dizhiList, list2)) {
            result += "午未合土 ";
            if (list2.contains(baZiList.get(1))) {
                result += "遥合 ";
            }
        }
        if (aContainsB(dizhiList, list3)) {
            result += "寅亥合木 ";
            if (list3.contains(baZiList.get(1))) {
                result += "遥合 ";
            }
        }
        if (aContainsB(dizhiList, list4)) {
            result += "卯戌合火 ";
            if (list4.contains(baZiList.get(1))) {
                result += "遥合 ";
            }
        }
        if (aContainsB(dizhiList, list5)) {
            result += "辰酉合金 ";
            if (list5.contains(baZiList.get(1))) {
                result += "遥合 ";
            }
        }
        if (aContainsB(dizhiList, list6)) {
            result += "巳申合水 ";
            if (list6.contains(baZiList.get(1))) {
                result += "遥合";
            }
        }
        return result;
    }

    /**
     * 获取十天干相合
     *
     * @param oldBaZiList 八字list
     * @return 返回相合的十天干
     */
    public static String getShiTianGanXiangHe(List<String> oldBaZiList, String daYunZhu) {
        String result = "";
        List<String> baZiList = new ArrayList<>(oldBaZiList);
        baZiList.add(daYunZhu.substring(0, 1));
        baZiList.add(daYunZhu.substring(1));
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
     * @param oldBaZiList 八字list
     * @return 返回十二支六合
     */
    public static String getShiErZhiLiuHe(List<String> oldBaZiList, String daYunZhu) {
        String result = "";
        List<String> baZiList = new ArrayList<>(oldBaZiList);
        baZiList.add(daYunZhu.substring(0, 1));
        baZiList.add(daYunZhu.substring(1));
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
        Log.d("", "getShiErZhiLiuHe: " + result);
        return result;
    }

    /**
     * 生合
     *
     * @param oldBaZiList 八字list
     * @return 返回生合的十天干
     */
    public static String getShengHe(List<String> oldBaZiList, String daYunZhu) {
        List<String> baZiList = new ArrayList<>(oldBaZiList);
        baZiList.add(daYunZhu.substring(0, 1));
        baZiList.add(daYunZhu.substring(1));
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
     * @param oldBaZiList 八字list
     * @return 返回克合的十天干
     */
    public static String getKeHe(List<String> oldBaZiList, String daYunZhu) {
        List<String> baZiList = new ArrayList<>(oldBaZiList);
        baZiList.add(daYunZhu.substring(0, 1));
        baZiList.add(daYunZhu.substring(1));
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
     * @param oldBaZiList 八字list
     * @return 返回相合的十天干
     */
    public static String getShiErZhiSanHe(List<String> oldBaZiList, String daYunZhu) {
        List<String> baZiList = new ArrayList<>(oldBaZiList);
        baZiList.add(daYunZhu.substring(0, 1));
        baZiList.add(daYunZhu.substring(1));
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
     * @param oldBaZiList 八字list
     * @return 返回十二支六合
     */
    public static String getShiErZhiXiangChong(List<String> oldBaZiList, String daYunZhu) {
        List<String> baZiList = new ArrayList<>(oldBaZiList);
        baZiList.add(daYunZhu.substring(0, 1));
        baZiList.add(daYunZhu.substring(1));
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
     * @param oldBaZiList 八字list
     * @return 返回十二支六合
     */
    public static String getShiErZhiXiangChuan(List<String> oldBaZiList, String daYunZhu) {
        List<String> baZiList = new ArrayList<>(oldBaZiList);
        baZiList.add(daYunZhu.substring(0, 1));
        baZiList.add(daYunZhu.substring(1));
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
     * @param oldBaZiList 八字list
     * @return 返回相合的十天干
     */
    public static String getShiErZhiXiangXing(List<String> oldBaZiList, String daYunZhu) {
        List<String> baZiList = new ArrayList<>(oldBaZiList);
        baZiList.add(daYunZhu.substring(0, 1));
        baZiList.add(daYunZhu.substring(1));
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
}
