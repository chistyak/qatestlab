package com.company;

public class Alcohol extends Drink{
    private float alc;

    public Alcohol(String name, float price, String type, float volume, float alc, int count) {
        super(name, price, type, volume, count);
        this.alc = alc;
    }

    @Override
    public String save() {
        String price = String.format("%.2f", this.price).replace(',', '.');
        String volume = String.format("%.2f", this.volume).replace(',', '.');
        String alc = String.format("%.1f%%", this.alc).replace(',', '.');
        return String.format("\"%s\", %s, \"%s\", %s, %s, %s\n", name, price, type, volume, alc, count);
    }
}
