package com.company;

import java.util.Locale;

public class Alcohol extends Drink{

    private float alc;

    public Alcohol(String name, float price, String type, float volume, float alc, int count) {
        super(name, price, type, volume, count);
        this.alc = alc;
    }


    @Override
    public String[] save() {
        return new String[]{name, String.format(Locale.ROOT,"%.2f", price), type, String.format(Locale.ROOT,"%.2f", volume), String.format(Locale.ROOT,"%.1f", alc), String.format("%.0f", count)};
    }

}
