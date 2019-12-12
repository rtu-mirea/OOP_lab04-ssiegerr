package com.company;

import javax.xml.namespace.QName;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Task2 {
    public static void save(String fileName)throws Exception{
        Scanner in = new Scanner(System.in);
        int count, homeNumber, flatNumber;
        String surname, name, city, street;

        File file = new File(fileName);
        if(file.exists())
            System.out.println("Файл " + fileName + " уже сущкствует");
        else {
            file.createNewFile();
            System.out.println("Файл " + fileName + " был создан");
        }

        DataOutputStream out = new DataOutputStream((new FileOutputStream(file, true)));
        System.out.println("Введите количество жителей:");
        count = in.nextInt();
        in.nextLine();
        for(int i = 0; i < count; i++){
            System.out.println("Введите фамилию жителя №" + (i + 1));
            surname = in.nextLine();
            System.out.println("Введите имя жителя №" + (i + 1));
            name = in.nextLine();
            System.out.println("Введите город жителя №" + (i + 1));
            city = in.nextLine();
            System.out.println("Введите улицу жителя №" + (i + 1));
            street = in.nextLine();
            System.out.println("Введите номер дома жителя №" + (i + 1));
            homeNumber = in.nextInt();
            System.out.println("Введите номер квартиры жителя №" + (i + 1));
            flatNumber = in.nextInt();
            in.nextLine();
            out.writeUTF(surname);
            out.writeUTF(name);
            out.writeUTF(city);
            out.writeUTF(street);
            out.writeInt(homeNumber);
            out.writeInt(flatNumber);
        }
        out.close();
    }
    public static LinkedList<HomeLiver> load(String filename){
        LinkedList<HomeLiver> livers = new LinkedList<HomeLiver>();
        try{
            DataInputStream in = new DataInputStream(new FileInputStream(filename));
            while (true)
                livers.add(new HomeLiver(in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readInt(), in.readInt()));
        }
        catch (Exception e){}

        return HomeLiver.getOneCity(livers);
    }
    public static LinkedList<HomeLiver> randomAccess(LinkedList<HomeLiver> livers)throws Exception{
        RandomAccessFile rf = new RandomAccessFile("FileForRandomAccess.txt", "rw");
        int buf = livers.size();
        for(HomeLiver liver: livers){
            liver.increase();
//            System.out.println(rf.getFilePointer());
            rf.writeUTF(liver.getSurname());
//            System.out.println(rf.getFilePointer());
            rf.writeUTF(liver.getName());
//            System.out.println(rf.getFilePointer());
            rf.writeUTF(liver.getCity());
//            System.out.println(rf.getFilePointer());
            rf.writeUTF(liver.getStreet());
//            System.out.println(rf.getFilePointer());
            rf.writeInt(liver.getHomeNumber());
//            System.out.println(rf.getFilePointer());
            rf.writeInt(liver.getFlatNumber());
//            System.out.println(rf.getFilePointer());
//            System.out.println();
        }
        livers.clear();
//        int homeNumber, flatNumber;
//        String surname, name, city, street;
        rf.seek(0);
        for(int i = 0; i < buf; i++){
            //rf.seek(i*((10 + 3)*4 + 4*2));
            livers.add(new HomeLiver(rf.readUTF(), rf.readUTF(), rf.readUTF(), rf.readUTF(), rf.readInt(), rf.readInt()));
//            System.out.println(rf.getFilePointer());
//            surname = rf.readUTF();
//            System.out.println(rf.getFilePointer());
//            name =  rf.readUTF();
//            System.out.println(rf.getFilePointer());
//            city =  rf.readUTF();
//            System.out.println(rf.getFilePointer());
//            street =  rf.readUTF();
//            System.out.println(rf.getFilePointer());
//            homeNumber =  rf.readInt();
//            System.out.println(rf.getFilePointer());
//            flatNumber = rf.readInt();
//            System.out.println(rf.getFilePointer());
//            livers.add(new HomeLiver(surname, name, city, street, homeNumber, flatNumber));
//            System.out.println(rf.getFilePointer());
//            System.out.println();
        }
        System.out.println("Введите номер записи которую вы хотите удалить 0 <= i <= " + (livers.size() - 1));
        return HomeLiver.deleteRecord(livers, new Scanner(System.in).nextInt());
    }
}
