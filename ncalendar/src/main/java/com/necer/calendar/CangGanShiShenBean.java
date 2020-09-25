package com.necer.calendar;

import com.necer.utils.SiZhuData;

import java.util.HashMap;

import static com.necer.utils.SolarTermUtil.diZhiXunCang;
import static com.necer.utils.SolarTermUtil.getDizhiPosition;
import static com.necer.utils.SolarTermUtil.getTianGanPosition;
import static com.necer.utils.SolarTermUtil.shiShens;

public class CangGanShiShenBean {

    private String nianTianGan;
    private String yueTianGan;
    private String shiTianGan;
    private String riTianGan;

    private String[] nianCangGanList;
    private String[] yueCangGanList;
    private String[] shiCangGanList;
    private String[] riCangGanList;

    private HashMap<String, String> nianCangGanMap = new HashMap<>();
    private HashMap<String, String> yueCangGanMap = new HashMap<>();
    private HashMap<String, String> shiCangGanMap = new HashMap<>();
    private HashMap<String, String> riCangGanMap = new HashMap<>();

    public HashMap<String, String> getNianCangGanMap() {
        return nianCangGanMap;
    }

    public void setNianCangGanMap(HashMap<String, String> nianCangGanMap) {
        this.nianCangGanMap = nianCangGanMap;
    }

    public HashMap<String, String> getYueCangGanMap() {
        return yueCangGanMap;
    }

    public void setYueCangGanMap(HashMap<String, String> yueCangGanMap) {
        this.yueCangGanMap = yueCangGanMap;
    }

    public HashMap<String, String> getShiCangGanMap() {
        return shiCangGanMap;
    }

    public void setShiCangGanMap(HashMap<String, String> shiCangGanMap) {
        this.shiCangGanMap = shiCangGanMap;
    }

    public String getRiTianGan() {
        return riTianGan;
    }

    public void setRiTianGan(String riTianGan) {
        this.riTianGan = riTianGan;
    }

    public HashMap<String, String> getRiCangGanMap() {
        return riCangGanMap;
    }

    public void setRiCangGanMap(HashMap<String, String> riCangGanMap) {
        this.riCangGanMap = riCangGanMap;
    }

    public String getNianTianGan() {
        return nianTianGan;
    }

    public void setNianTianGan(String nianTianGan) {
        this.nianTianGan = nianTianGan;
    }

    public String getYueTianGan() {
        return yueTianGan;
    }

    public void setYueTianGan(String yueTianGan) {
        this.yueTianGan = yueTianGan;
    }

    public String getShiTianGan() {
        return shiTianGan;
    }

    public void setShiTianGan(String shiTianGan) {
        this.shiTianGan = shiTianGan;
    }

    public CangGanShiShenBean(SiZhuData siZhuData) {
        String nianGan = siZhuData.getNianZhu().substring(1, 2);
        String yueGan = siZhuData.getYueZhu().substring(1, 2);
        String riGan = siZhuData.getRiZhu().substring(0, 1);
        String riDiZhi = siZhuData.getRiZhu().substring(1, 2);
        String shiGan = siZhuData.getShiZhu().substring(1, 2);
        this.nianTianGan = nianGan;
        this.yueTianGan = yueGan;
        this.shiTianGan = shiGan;
        this.riTianGan = riDiZhi;
        int yearIndex = getDizhiPosition(nianGan);
        int monthIndex = getDizhiPosition(yueGan);
        int dayIndex = getTianGanPosition(riGan);
        int dayDizhiIndex=getDizhiPosition(riTianGan);
        int hourIndex = getDizhiPosition(shiGan);
        nianCangGanList = diZhiXunCang[yearIndex];
        yueCangGanList = diZhiXunCang[monthIndex];
        shiCangGanList = diZhiXunCang[hourIndex];
        riCangGanList = diZhiXunCang[dayDizhiIndex];
        if (nianCangGanList != null && nianCangGanList.length > 0) {
            for (int i = 0; i < nianCangGanList.length; i++) {
                int tempPostion = getTianGanPosition(nianCangGanList[i]);
                nianCangGanMap.put(nianCangGanList[i], shiShens[dayIndex][tempPostion]);
            }
        }
        if (yueCangGanList != null && yueCangGanList.length > 0) {
            for (int i = 0; i < yueCangGanList.length; i++) {
                int tempPostion = getTianGanPosition(yueCangGanList[i]);
                yueCangGanMap.put(yueCangGanList[i], shiShens[dayIndex][tempPostion]);
            }
        }
        if (shiCangGanList != null && shiCangGanList.length > 0) {
            for (int i = 0; i < shiCangGanList.length; i++) {
                int tempPostion = getTianGanPosition(shiCangGanList[i]);
                shiCangGanMap.put(shiCangGanList[i], shiShens[dayIndex][tempPostion]);
            }
        }
        if (riCangGanList != null && riCangGanList.length > 0) {
            for (int i = 0; i < riCangGanList.length; i++) {
                int tempPostion = getTianGanPosition(riCangGanList[i]);
                riCangGanMap.put(riCangGanList[i], shiShens[dayIndex][tempPostion]);
            }
        }
    }

}
