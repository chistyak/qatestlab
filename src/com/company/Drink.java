package com.company;

public abstract class Drink {
    protected String name;
    protected float price;
    protected float volume;
    protected int count;
    protected String type;
    protected int sold = 0;
    protected int bought = 0;

    public Drink(String name, float price, String type, float volume, int count) {
        this.name = name;
        this.price = price;
        this.volume = volume;
        this.count = count;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public float getVolume() {
        return volume;
    }

    public float getCount() {
        return count;
    }

    /**
     * do a sale
     * @return is sale successful
     */
    public boolean sellOne(){
        if(count != 0){
            sold++;
            count--;
            return true;
        }else return false;
    }

    /**
     * buy products
     * @param i count to buy
     */
    public void buy(int i) {
        bought += i;
        count += i;
    }

    public int getSold() {
        return sold;
    }

    public int getBought() {
        return bought;
    }

    public abstract String save();
}
