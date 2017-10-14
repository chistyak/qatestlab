package com.company;

import java.util.Locale;

public class NonAlcohol extends Drink{

    private String cont;

    public NonAlcohol(String name, float price, String type, float volume, String cont, int count) {
        super(name, price, type, volume, count);
        this.cont = cont;
    }


    @Override
    public String[] save() {
        return new String[]{name, String.format(Locale.ROOT,"%.2f", price), type, String.format(Locale.ROOT,"%.2f", volume), cont, String.format(Locale.ROOT,"%.0f", count)};
    }
}
