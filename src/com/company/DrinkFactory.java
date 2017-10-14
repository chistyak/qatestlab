package com.company;

import java.util.List;

public class DrinkFactory {
    public static Drink getDrink(String[] args) throws Exception {
        if(args.length != 6) throw new Exception();
        Drink drink;
        try{
            float alc =  Float.parseFloat(args[4]);
            String name = args[0];
            float price = Float.parseFloat(args[1]);
            String type = args[2];
            float volume = Float.parseFloat(args[3]);
            int count = Integer.parseInt(args[5]);
            drink = new Alcohol(name, price, type, volume, alc, count);
        }catch (NumberFormatException e){
            String name = args[0];
            float price = Float.parseFloat(args[1]);
            String type = args[2];
            float volume = Float.parseFloat(args[3]);
            String cont = args[4];
            int count = Integer.parseInt(args[5]);
            drink = new NonAlcohol(name, price, type, volume, cont, count);
        }
        return drink;
    }
}
