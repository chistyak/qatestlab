package com.company;

public class NonAlcohol extends Drink{
    private String cont;

    public NonAlcohol(String name, float price, String type, float volume, String cont, int count) {
        super(name, price, type, volume, count);
        this.cont = cont;
    }

    @Override
    public String save() {
        String price = String.format("%.2f", this.price).replace(',', '.');
        String volume = String.format("%.2f", this.volume).replace(',', '.');
        return String.format("\"%s\", %s, \"%s\", %s, \"%s\", %s\n", name, price, type, volume, cont, count);
    }
}
