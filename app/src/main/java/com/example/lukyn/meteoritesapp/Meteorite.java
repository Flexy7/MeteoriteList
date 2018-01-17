package com.example.lukyn.meteoritesapp;

public class Meteorite {
    private String name;
    private String reclat;
    private String reclong;
    private String mass;
    private String fall;
    private String year;

    public Meteorite() {

    }


    public String getMeteoriteName() {
        return name;
    }

    public String getMeteoriteYear() {
        return year;
    }

    public String getMeteoriteLongitude() {
        return reclong;
    }

    public String getMeteoriteLatitude() {
        return reclat;
    }

    public String getMeteoriteMass() {
        return mass;
    }

    public String getMeteoriteFall() {
        return fall;
    }

}
