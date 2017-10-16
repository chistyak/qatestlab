package com.company;


public class DrinkFactory {
    /**
     * create new Alcohol/NonAlcohol object depending on input data
     * @param args configurations from data file
     * @return new Alcohol/NonAlcohol object
     */
    public static Drink getDrink(String[] args){
        if(args.length != 6) throw new IllegalArgumentException("args.length != 6: ");
        Drink drink;
        try{
            args[4] = args[4].replace("%", "");
            float alc =  Float.parseFloat(args[4]);
            String name = args[0];
            float price = Float.parseFloat(args[1]);
            String type = args[2];
            float volume = Float.parseFloat(args[3]);
            int count = Integer.parseInt(args[5]);
            drink = new Alcohol(name, price, type, volume, alc, count);
        } catch (NumberFormatException e){
            try {
                String name = args[0];
                float price = Float.parseFloat(args[1]);
                String type = args[2];
                float volume = Float.parseFloat(args[3]);
                String cont = args[4];
                int count = Integer.parseInt(args[5]);
                drink = new NonAlcohol(name, price, type, volume, cont, count);
            }catch (NumberFormatException ex){
                throw new IllegalArgumentException("some arg doesn't match requirements: ");
            }
        }
        return drink;
    }
}
