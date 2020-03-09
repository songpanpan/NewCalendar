package com.necer.utils;

import java.util.List;

public class JieQiBean {

    private List<JieQi> jieQiList;

    public List<JieQi> getJieQiList() {
        return jieQiList;
    }

    public void setJieQiList(List<JieQi> jieQiList) {
        this.jieQiList = jieQiList;
    }

    public static class JieQi {
        private int year;
        private String jieqiName;
        private int month;
        private int dayCountInMonth;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getJieqiName() {
            return jieqiName;
        }

        public void setJieqiName(String jieqiName) {
            this.jieqiName = jieqiName;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDayCountInMonth() {
            return dayCountInMonth;
        }

        public void setDayCountInMonth(int dayCountInMonth) {
            this.dayCountInMonth = dayCountInMonth;
        }
    }
}
