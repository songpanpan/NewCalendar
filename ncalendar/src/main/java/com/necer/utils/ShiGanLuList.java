package com.necer.utils;

import java.util.ArrayList;
import java.util.List;

public class ShiGanLuList {

    private List<ShiGanLuBean> shiGanLuBeanList;

    public ShiGanLuList() {
        shiGanLuBeanList = new ArrayList<>();
        shiGanLuBeanList.add(new ShiGanLuBean("甲", "丙", "寅", "（福星禄）吉)"));
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
