package com.rtrk.complexlist;

public class Planet {
    private String mName;
    private String mVolume;

    public Planet(String name, String volume) {
        this.mName = name;
        this.mVolume = volume;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getVolume() {
        return mVolume;
    }

    public void setVolume(String volume) {
        this.mVolume = volume;
    }
}
