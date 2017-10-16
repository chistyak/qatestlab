package com.company;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static List<Drink> list = new ArrayList<>();
    public static Random random = new Random();
    public static float income = 0;
    public static String DATA = "data.csv";
    public static String REPORT = "report.txt";

    public static void main(String [] args) throws IOException {
        initialize();
        imitate(30);
        report();
        rewrite();
    }
    /**
     * Rewrite data csv file with current values
     * @throws IOException
     */
    public static void rewrite() throws IOException {
        FileWriter fileWriter = new FileWriter(DATA);
        for(Drink drink: list){
            fileWriter.write(drink.save());
        }
        fileWriter.close();
    }
    /**
     * construct and save the report
     * @throws IOException
     */
    public static void report() throws IOException {
        StringBuilder sb = new StringBuilder();
        float totalSpent = 0;
        for(Drink drink: list) {
            sb.append(String.format("%s, %.02fl Sold: %s, bought: %s", drink.getName(), drink.getVolume(), drink.getSold(), drink.getBought())).append("\n");
            totalSpent += drink.getBought()*drink.getPrice();
        }
        FileWriter fileWriter = new FileWriter(REPORT);
        fileWriter.write(sb.toString());
        fileWriter.write(String.format("Total income: %.02f\n", income));
        fileWriter.write(String.format("Total spent: %.02f\n", totalSpent));
        fileWriter.close();
    }
    /**
     * read csv file and load data into list
     * @throws IOException
     */
    public static void initialize() throws IOException {
            CSVReader csvReader = new CSVReader(new FileReader(DATA));
            String[] strings;
            do{
                strings = csvReader.readNext();
                if(strings != null) {
                    for (int i = 0; i < strings.length; i++) {
                        strings[i] = strings[i].trim();
                    }
                    try {
                        Drink newDrink = DrinkFactory.getDrink(strings);
                        if (newDrink != null)
                            list.add(newDrink);
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage() + strings[0]);
                    }
                }
            }while(strings != null);
            csvReader.close();
    }
    /**
     * imitate work for provided number of days
     * @param days number of days
     */
    public static void imitate(int days){
        for(int i = 0; i < days; i++){
            if(i%7 < 5)
                imitateDay(Charge.WORKING);
            else
                imitateDay(Charge.FREE);
        }
    }
    /**
     * imitate one working day
     * @param charge working or free day
     */
    public static void imitateDay(Charge charge){
        for(int i = 0; i < 10; i++)
            imitateHour(charge);
        for(int i = 0; i < 2; i++)
            imitateHour(Charge.EVENING);
        imitateHour(charge);
        for(Drink drink : list){
            if (drink.getCount() < 10) drink.buy(150);
        }
    }
    /**
     * imitate one working hour
     * @param charge charge type for selected hour
     */
    public static void imitateHour(Charge charge){
        int count = random.nextInt(10) + 1;
        for(int i = 0; i < count; i++){
            serveClient(charge);
        }
    }
    /**
     * imitate serving one client
     * @param charge charge type depends on when client came
     */
    public static void serveClient(Charge charge){
        int count = random.nextInt(11);
        for(int i = 0; i < count; i++) {
            if(i > 1)  sellItem(Charge.DISCOUNT);
            else sellItem(charge);
        }
    }
    /**
     * imitate one product sale
     * @param charge charge type depends on when client came and how many products bought
     */
    public static void sellItem(Charge charge){
        int index = random.nextInt(list.size());
        Drink drink = list.get(index);
        if(drink.sellOne()) {
            float mp;
            switch (charge){
                case WORKING:{
                    mp = (float) 1.1;
                    break;
                }
                case FREE:{
                    mp = (float) 1.15;
                    break;
                }
                case DISCOUNT:{
                    mp = (float) 1.07;
                    break;
                }
                default:{
                    mp = (float) 1.08;
                    break;
                }
            }
            float price = drink.getPrice()*mp;
            float in = drink.getPrice()*(mp-1);
            income += in;
            String name = drink.getName();
            System.out.println(String.format("%s sold. Price: %.02f. Charge: %.0f%%", name, price, (int)100*(mp-1)));
        }
    }
}
