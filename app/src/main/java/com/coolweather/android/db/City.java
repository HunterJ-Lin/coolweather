package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by hasee on 2017/12/15.
 */

public class City extends DataSupport {
    private int id;
    private String cityName;
    private int provinceId;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
