package com.example.lukyn.meteoritesapp;

import java.text.DecimalFormat;

public class ModifyData {


    public static String modifyYear(String year) {

        String[] arr = year.split("-");
        return arr[0];
    }

    public static String modifyMass(float mass) {
        if (mass / 1000 < 1) {
            return String.valueOf((Math.round(mass))) + " g";
        }
        mass = Float.valueOf(new DecimalFormat("##.##").format(mass / 1000));
        return String.valueOf(mass) + " kg";
    }
}
