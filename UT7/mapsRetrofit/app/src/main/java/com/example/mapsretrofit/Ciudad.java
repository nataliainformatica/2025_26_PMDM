package com.example.mapsretrofit;

public class Ciudad {


    private Name name;
    private String region;
    private CoatOfArms coatOfArms;
    private double[] latlng;
//"capital":["Rome"]
    private String[] capital;

    public double[] getLatlng() {
        return latlng;
    }

    public double getLatitud(){
        return latlng[0];
    }

    public String[] getCapital() {
        return capital;
    }

    public void setCapital(String[] capital) {
        this.capital = capital;
    }

    public double getLongitud(){
        return latlng[1];
    }
    public void setLatlng(double[] latlng) {
        this.latlng = latlng;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public CoatOfArms getCoatOfArms() {
        return coatOfArms;
    }

    public void setCoatOfArms(CoatOfArms coatOfArms) {
        this.coatOfArms = coatOfArms;
    }
}
//*****

