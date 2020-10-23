package com.necer.utils;

import java.util.ArrayList;
import java.util.List;

public class ShiGanLuList {

    private List<ShiGanLuBean> shiGanLuBeanList;

    public ShiGanLuList() {
        shiGanLuBeanList = new ArrayList<>();
        shiGanLuBeanList.add(new ShiGanLuBean("甲", "丙", "寅", "（福星禄）吉)"));
        shiGanLuBeanList.add(new ShiGanLuBean("甲", "戊", "寅", "（伏马禄）吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("甲", "庚", "寅", "（破禄）半凶半吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("甲", "壬", "寅", "（正禄）姻缘不顺"));
        shiGanLuBeanList.add(new ShiGanLuBean("甲", "甲", "寅", "（长生禄）大吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("乙", "乙", "卯", "（喜神望禄）主吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("乙", "丁", "卯", "（截路空亡）主凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("乙", "己", "卯", "（进神禄）半吉半凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("乙", "辛", "卯", "（破禄）半吉半凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("乙", "癸", "卯", "（死禄）虽贵终贫"));
        shiGanLuBeanList.add(new ShiGanLuBean("丙", "己", "巳", "（九天库禄）主吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("丙", "辛", "巳", "（截路空亡）半吉半凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("丙", "癸", "巳", "（伏贵神禄）半吉半凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("丙", "乙", "巳", "（旺马禄）吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("丙", "丁", "巳", "（库禄）吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("丁", "庚", "午", "（截路空亡）凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("丁", "壬", "午", "（德合禄）吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("丁", "甲", "午", "（进神禄）吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("丁", "丙", "午", "（喜神禄）交羊刃半吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("丁", "戊", "午", "（伏羊刃禄）多凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("戊", "辛", "巳", "（截路空亡）吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("戊", "癸", "巳", "（贵神禄）"));
        shiGanLuBeanList.add(new ShiGanLuBean("戊", "癸", " ", " 有官位重"));
        shiGanLuBeanList.add(new ShiGanLuBean("戊", "乙", "巳", "（驿马同乡禄）吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("戊", "丁", "巳", "（旺库禄）吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("己", "庚", "午", "（截路空亡）凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("己", "壬", "午", "（死鬼禄）凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("己", "甲", "午", "（进神合禄）显达之象"));
        shiGanLuBeanList.add(new ShiGanLuBean("己", "丙", "午", "（喜神禄）交羊刃半吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("己", "戊", "午", "（伏神单刀禄）凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("庚", "壬", "申", "（大败禄）凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("庚", "甲", "申", "（截路空亡）凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("庚", "丙", "申", "（大败禄）多成败"));
        shiGanLuBeanList.add(new ShiGanLuBean("庚", "戊", "申", "（伏马禄）多滞吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("庚", "庚", "申", "（长生禄）大吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("辛", "癸", "酉", "（伏神禄）凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("辛", "乙", "酉", "（破禄）半吉半凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("辛", "丁", "酉", "（贵神禄）主奸淫事凶"));
        shiGanLuBeanList.add(new ShiGanLuBean("辛", "己", "酉", "（进神禄）吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("辛", "辛", "酉", "（正禄）吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("壬", "丁", "亥", "（贵神合禄）大吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("壬", "乙", "亥", "（天德禄）大吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("壬", "己", "亥", "（旺禄）大吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("壬", "辛", "亥", "（同马乡禄）大吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("壬", "癸", "亥", "（大败禄）贫薄"));
        shiGanLuBeanList.add(new ShiGanLuBean("癸", "甲", "子", "（进神禄）主学业有成"));
        shiGanLuBeanList.add(new ShiGanLuBean("癸", "丙", "子", "（羊刃禄）有权"));
        shiGanLuBeanList.add(new ShiGanLuBean("癸", "戊", "子", "（合贵禄）半吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("癸", "庚", "子", "（邙禄）吉"));
        shiGanLuBeanList.add(new ShiGanLuBean("癸", "壬", "子", "（正羊刃禄）凶"));


    }

    public List<ShiGanLuBean> getShiGanLuBeanList() {
        return shiGanLuBeanList;
    }

    public void setShiGanLuBeanList(List<ShiGanLuBean> shiGanLuBeanList) {
        this.shiGanLuBeanList = shiGanLuBeanList;
    }


    public static class ShiGanLuBean {
        private String riGan;
        private String otherGan;
        private String otherZhi;
        private String result;

        public ShiGanLuBean(String riGan, String otherGan, String otherZhi, String result) {
            this.riGan = riGan;
            this.otherGan = otherGan;
            this.otherZhi = otherZhi;
            this.result = result;
        }

        public String getRiGan() {
            return riGan;
        }

        public void setRiGan(String riGan) {
            this.riGan = riGan;
        }

        public String getOtherGan() {
            return otherGan;
        }

        public void setOtherGan(String otherGan) {
            this.otherGan = otherGan;
        }

        public String getOtherZhi() {
            return otherZhi;
        }

        public void setOtherZhi(String otherZhi) {
            this.otherZhi = otherZhi;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

}
