package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hasee on 2017/12/15.
 */

public class Utility {

    public static Weather handleWeatherResponse(String response)
    {
        try
        {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean handleProvinceRequest(String response)
    {
        if(!TextUtils.isEmpty(response))
        {
            try{
                JSONArray allprovince=new JSONArray(response);
                for(int i=0;i<allprovince.length();++i) {
                    JSONObject provinceObject=allprovince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response,int provinceId)
    {
        if(!TextUtils.isEmpty(response))
        {
            try {
                JSONArray allCities=new JSONArray(response);
                for(int i=0;i<allCities.length();++i)
                {
                    JSONObject cityObjece=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObjece.getString("name"));
                    city.setCityCode(cityObjece.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyRequest(String response,int cityId)
    {
        if(!TextUtils.isEmpty(response))
        {
            try {
                JSONArray allCounties=new JSONArray(response);
                for(int i=0;i<allCounties.length();++i)
                {
                    JSONObject countyObjece=allCounties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObjece.getString("name"));
                    county.setWeatherId(countyObjece.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
