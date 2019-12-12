package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClassSerialization {
    private String fileName;
    ArrayList<StringWork> objects;

    public ClassSerialization(String str){
        fileName = str;
        objects = new ArrayList<StringWork>();
    }
    public void writeOneObject(StringWork obj)throws Exception{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(obj);
        out.close();
    }
    public StringWork readObeObject()throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        StringWork x = (StringWork) in.readObject();
        in.close();
        return x;
    }
    public void majeCollection() throws Exception{
        Scanner in = new Scanner(System.in);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName, true));
        StringWork o;
        System.out.println("Введите количество объектов: ");
        int count = in.nextInt();
        in.nextLine();
        for (int i = 0; i < count; i++){
            System.out.println("Введите строку для объекта №" + (i + 1));
            o = new StringWork(in.nextLine());
            objects.add(o);
            out.writeObject(o);
        }
        out.close();
    }
    public void readCollection() throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        ArrayList<StringWork> arr = new ArrayList<StringWork>();
        try{
            while(true){
                arr.add((StringWork)in.readObject());
            }
        }
        catch (Exception e){}
        objects = arr;
    }
    public ArrayList<StringWork> getCollection(){return objects;}
}
