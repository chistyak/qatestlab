package com.company;

import java.util.List;

public abstract class Drink {
    protected String name;
    protected float price;
    protected float volume;
    protected float count;
    protected String type;

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    protected int sold = 0;
    protected int bought = 0;


    public Drink(String name, float price, String type, float volume, float count) {
        this.name = name;
        this.price = price;
        this.volume = volume;
        this.count = count;
        this.type = type;
    }

    public Drink(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public boolean sellOne(){
        if(count != 0){
            sold++;
            count--;
            return true;
        }else return false;
    };

    public void buy(int i) {
        bought += i;
        count += i;
    }

    public abstract String[] save();
}
