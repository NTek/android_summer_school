package com.rtrk.weather.beans;

public class Weather {
    private int mHi_temperature_c = 0;
    private int mLow_temerature_c = 0;
    private String mConditions = "";
    private String mIcon_url = "";
    private int mPeriod = 0;

    public Weather() {
        super();
    }

    public Weather(int hiTemperatureC, int lowTemeratureC, String conditions,
            String iconUrl) {
        super();
        mHi_temperature_c = hiTemperatureC;
        mLow_temerature_c = lowTemeratureC;
        this.mConditions = conditions;
        mIcon_url = iconUrl;
    }

    public int getPeriod() {
        return mPeriod;
    }

    public void setPeriod(int period) {
        this.mPeriod = period;
    }

    public int getHiTemperatureC() {
        return mHi_temperature_c;
    }

    public void setHiTemperatureC(int hiTemperatureC) {
        mHi_temperature_c = hiTemperatureC;
    }

    public int getLowTemeratureC() {
        return mLow_temerature_c;
    }

    public void setLowTemeratureC(int lowTemeratureC) {
        mLow_temerature_c = lowTemeratureC;
    }

    public String getConditions() {
        return mConditions;
    }

    public void setConditions(String conditions) {
        this.mConditions = conditions;
    }

    public String getIconUrl() {
        return mIcon_url;
    }

    public void setIconUrl(String iconUrl) {
        mIcon_url = iconUrl;
    }
}
