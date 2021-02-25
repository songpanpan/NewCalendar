package com.necer.ncalendar.tools;

import com.necer.utils.SiZhuData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LiuNianTools {
    /**
     * 六十甲子
     */
    public static final List<String> jiaZi = Arrays.asList(
            "甲子", "乙丑", "丙寅", "丁卯", "戊辰", "己巳", "庚午", "辛未", "壬申", "癸酉",
            "甲戌", "乙亥", "丙子", "丁丑", "戊寅", "己卯", "庚辰", "辛巳", "壬午", "癸未",
            "甲申", "乙酉", "丙戌", "丁亥", "戊子", "己丑", "庚寅", "辛卯", "壬辰", "癸巳",
            "甲午", "乙未", "丙申", "丁酉", "戊戌", "己亥", "庚子", "辛丑", "壬寅", "癸卯",
            "甲辰", "乙巳", "丙午", "丁未", "戊申", "己酉", "庚戌", "辛亥", "壬子", "癸丑",
            "甲寅", "乙卯", "丙辰", "丁巳", "戊午", "己未", "庚申", "辛酉", "壬戌", "癸亥"
    );

    public HashMap<Integer, LiuNian> createLiuNian(int daYunAge, SiZhuData siZhuData,
                                                   String[] daYunArray, List<String> baZiList) {
        if (siZhuData == null) {
            return null;
        }
        String nianZhu = siZhuData.getNianZhu();
        HashMap<Integer, LiuNian> liuNianHashMap = new HashMap<>();
        String dayun = "";
        for (int i = 1; i < 80; i++) {
            LiuNian t = new LiuNian();
            t.age = i;
            t.nianGanZhi = getCurrentNianGanZhi(i, nianZhu);
            t.siZhuNian = siZhuData.getNianZhu();
            t.siZhuYue = siZhuData.getYueZhu();
            t.siZhuRi = siZhuData.getRiZhu();
            t.siZhuShi = siZhuData.getShiZhu();
            if (i < daYunAge) {
                t.isDaYunNian = false;
                t.daYun = dayun;
                t.shiTianGanXiangHe = HeHuaTools.getShiTianGanXiangHe(baZiList, t.nianGanZhi);
                t.shiErZhiLiuHe = HeHuaTools.getShiErZhiLiuHe(baZiList, t.nianGanZhi);
                t.shengHe = HeHuaTools.getShengHe(baZiList, t.nianGanZhi);
                t.keHe = HeHuaTools.getKeHe(baZiList, t.nianGanZhi);
                t.shiErZhiSanHe = HeHuaTools.getShiErZhiSanHe(baZiList, t.nianGanZhi);
                t.shiErZhiXiangChong = HeHuaTools.getShiErZhiXiangChong(baZiList, t.nianGanZhi);
                t.shiErZhiXiangChuan = HeHuaTools.getShiErZhiXiangChuan(baZiList, t.nianGanZhi);
                t.shiErZhiXiangXing = HeHuaTools.getShiErZhiXiangXing(baZiList, t.nianGanZhi);
                t.tianBi = HeHuaTools.dealWithBaziTianGanList(baZiList, t.nianGanZhi);
                t.tianBi = HeHuaTools.dealWithBaziDizhiList(baZiList, t.nianGanZhi);
                t.tianDiBi = HeHuaTools.checkTianDiBi(baZiList, t.nianGanZhi);
            } else {
                int tenAge = i / 10;
                int smallAge = i % 10;
                if (smallAge == daYunAge) {
                    t.isDaYunNian = true;
                    t.daYun = daYunArray[tenAge];
                    t.fenGeJieQi = "芒种";
                } else if (smallAge < daYunAge) {
                    t.isDaYunNian = false;
                    t.daYun = daYunArray[tenAge - 1];
                } else {
                    t.isDaYunNian = false;
                    t.daYun = daYunArray[tenAge];
                }
                t.shiTianGanXiangHe = HeHuaTools.getShiTianGanXiangHe(baZiList, t.nianGanZhi, t.daYun);
                t.shiErZhiLiuHe = HeHuaTools.getShiErZhiLiuHe(baZiList, t.nianGanZhi, t.daYun);
                t.shengHe = HeHuaTools.getShengHe(baZiList, t.nianGanZhi, t.daYun);
                t.keHe = HeHuaTools.getKeHe(baZiList, t.nianGanZhi, t.daYun);
                t.shiErZhiSanHe = HeHuaTools.getShiErZhiSanHe(baZiList, t.nianGanZhi, t.daYun);
                t.shiErZhiXiangChong = HeHuaTools.getShiErZhiXiangChong(baZiList, t.nianGanZhi, t.daYun);
                t.shiErZhiXiangChuan = HeHuaTools.getShiErZhiXiangChuan(baZiList, t.nianGanZhi, t.daYun);
                t.shiErZhiXiangXing = HeHuaTools.getShiErZhiXiangXing(baZiList, t.nianGanZhi, t.daYun);
                t.tianBi = HeHuaTools.dealWithBaziTianGanList(baZiList, t.nianGanZhi, t.nianGanZhi);
                t.tianBi = HeHuaTools.dealWithBaziDizhiList(baZiList, t.nianGanZhi, t.nianGanZhi);
                t.tianDiBi = HeHuaTools.checkTianDiBi(baZiList, t.nianGanZhi, t.nianGanZhi);
            }
            liuNianHashMap.put(i, t);
        }
        return liuNianHashMap;
    }

    private String getCurrentNianGanZhi(int age, String nianZhu) {
        int position = jiaZi.indexOf(nianZhu);
        int index = position + age;
        if (index > 60) {
            index = index % 60;
        }
        if (index == 0) {
            return jiaZi.get(59);
        } else {
            return jiaZi.get(index - 1);
        }
    }
}
