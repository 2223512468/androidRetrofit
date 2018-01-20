package com.jaja.home.retrofit;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/2.
 */
public class PhoneModel implements Serializable {


    /**
     * mts : 1526718
     * province : 浙江
     * catName : 中国移动
     * telString : 15267185984
     * areaVid : 30510
     * ispVid : 3236139
     * carrier : 浙江移动
     */

    private String mts;
    private String province;
    private String catName;
    private String telString;
    private String areaVid;
    private String ispVid;
    private String carrier;

    public void setMts(String mts) {
        this.mts = mts;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public void setTelString(String telString) {
        this.telString = telString;
    }

    public void setAreaVid(String areaVid) {
        this.areaVid = areaVid;
    }

    public void setIspVid(String ispVid) {
        this.ispVid = ispVid;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getMts() {
        return mts;
    }

    public String getProvince() {
        return province;
    }

    public String getCatName() {
        return catName;
    }

    public String getTelString() {
        return telString;
    }

    public String getAreaVid() {
        return areaVid;
    }

    public String getIspVid() {
        return ispVid;
    }

    public String getCarrier() {
        return carrier;
    }
}
