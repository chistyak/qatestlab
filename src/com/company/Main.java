package com.company;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static List<Drink> list = new ArrayList<>();
    public static Random random = new Random();
    public static float income = 0;

    public static void main(String [] args) throws IOException {

        initialize();
        imitate(30);
        report();
        rewrite();
    }


    public static void rewrite() throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter("data.csv"));
        for(Drink drink: list){
            csvWriter.writeNext(drink.save());
        }
        csvWriter.close();
    }

    public static void report() throws IOException {
        StringBuilder sb = new StringBuilder();
        float totalSpent = 0;
        for(Drink drink: list) {
            sb.append(String.format("%s, %.02fl Sold: %s, bought: %s", drink.getName(), drink.getVolume(), drink.getSold(), drink.getBought())).append("\n");
            totalSpent += drink.getBought()*drink.getPrice();
        }
        FileWriter fileWriter = new FileWriter("report.txt");
        fileWriter.write(sb.toString());
        fileWriter.write(String.format("Total income: %.02f\n", income));
        fileWriter.write(String.format("Total spent: %.02f\n", totalSpent));
        fileWriter.close();
    }

    public static void initialize(){
        try {
            List<Drink> tmp = new ArrayList<Drink>();
            CSVReader csvReader = new CSVReader(new FileReader("data.csv"));
            String[] strings;
            do{
                strings = csvReader.readNext();
                for(int i = 0; i < 6; i ++){
                    strings[i] = strings[i].trim();
                };
                list.add(DrinkFactory.getDrink(strings));
            }while(strings != null);
            csvReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void imitate(int days){
        for(int i = 0; i < days; i++){
            if(i%7 < 5)
                imitateDay(Charge.WORKING);
            else
                imitateDay(Charge.FREE);
        }
    }

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

    public static void imitateHour(Charge charge){
        int count = random.nextInt(10) + 1;
        for(int i = 0; i < count; i++){
            serveClient(charge);
        }
    }

    public static void serveClient(Charge charge){
        int count = random.nextInt(11);
        for(int i = 0; i < count; i++) {
            if(i > 1)  sellItem(Charge.DISCOUNT);
            else sellItem(charge);
        }
    }

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
