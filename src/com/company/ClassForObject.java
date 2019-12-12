package com.company;

import java.util.Scanner;

public class ClassForObject {
    private StringWork str = new StringWork();
    public void consoleInput(){
        System.out.println("Введите данные ");
        str.setText(new Scanner(System.in).nextLine());
    }
    public void dataStream(String fileName)throws Exception{
        ClassTextFile obj = new ClassTextFile(fileName);
        str = obj.getText();
    }
    public StringWork getObject(){
        return str;
    }
}
