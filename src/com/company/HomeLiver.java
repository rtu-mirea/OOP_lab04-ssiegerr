package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class HomeLiver {
    private String surname;
    private String name;
    private String city;
    private String street;
    private int homeNumber;
    private int flatNumber;

    public HomeLiver(){
        this.surname = "";
        this.name = "";
        this.city = "";
        this.street = "";
        this.homeNumber = 0;
        this.flatNumber = 0;
    }
    public HomeLiver(String surname, String name, String city, String street, int homeNumber, int flatNumber){
        this.surname = surname;
        this.name = name;
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
        this.flatNumber = flatNumber;
    }

    public String getName(){return name;}
    public String getSurname(){return surname;}
    public String getCity(){return city;}
    public String getStreet(){return street;}
    public int getHomeNumber(){return homeNumber;}
    public int getFlatNumber(){return flatNumber;}

    public String getSurOfAdress(String city, String street, int homeNumber, int flatNumber){
        if (this.city.compareTo(city) == 0 && this.street.compareTo(street) == 0 &&
        this.homeNumber == homeNumber && this.flatNumber == flatNumber)
            return this.surname;
        else
            return null;
    }
    public static boolean checkMoreLiver(ArrayList<HomeLiver> homeLivers, String city, String street, int homeNumber, int flatNumber){
        for(int i = 0; i < homeLivers.size(); i++){
            if(homeLivers.get(i).getSurOfAdress(city, street, homeNumber, flatNumber) != null)
                for (int n = i+1; n < homeLivers.size(); n++)
                    if(homeLivers.get(i).getSurOfAdress(city, street, homeNumber, flatNumber) != null)
                        return true;
        }
        return false;
    }
    public static LinkedList<HomeLiver> getOneCity(LinkedList<HomeLiver> livers){
        Scanner in = new Scanner(System.in);
        LinkedList<String> cities = new LinkedList<String>();
        boolean isNew = true;
        LinkedList<HomeLiver> result = new LinkedList<HomeLiver>();
        for(HomeLiver liver: livers){
            for(String city: cities){
                if (city.compareTo(liver.city) == 0)
                    isNew = false;
            }
            if(isNew) cities.add(liver.city);
            isNew = true;
        }
        System.out.println("Список городов, в которых проживают жители:");
        for(String city: cities)
            System.out.println(city);
        System.out.println("Выберите нужный вам город");
        String city = in.nextLine();
        //String city = "Moscow";
        for (HomeLiver liver: livers){
            if (liver.city.compareTo(city) == 0)
                result.add(liver);
        }
        return result;
    }
    public static LinkedList<HomeLiver> deleteRecord(LinkedList<HomeLiver> livers, int i)throws Exception{
        if (i < 0 || i >= livers.size()) throw new Exception("Вы вышли за границы записей");
        livers.remove(i);
        return livers;
    }
    public void increase(){
        surname = grow(surname);
        name = grow(name);
        city = grow(city);
        street = grow(street);
    }
    private static String grow(String str){
        for(int i = str.length(); i <= 10; i++)
            str+=" ";
        return str;
    }
}
