package com.company;

import java.io.Serializable;
import java.util.Scanner;

public class StringWork implements Serializable {
    private String text = "";
    private int register = 0;//Нужна для корректной работы функции поиска слов с префиксами, потому что у пользователя есть выбор
    public StringWork(String str){text = str;}
    public StringWork(){text = "";}
    public void setText(String str){text = str;}
    public String getText() {return text;}

    public void allTo(Scanner in){
        System.out.print("[0] - все строчные\n" +
                "[1] - Все Прописные\n" +
                "Выберите вариант: ");
        register = in.nextInt();
        switch (register){
            case 0:
                text = text.toLowerCase();
                break;
            case 1:
                text = text.toUpperCase();
                break;
        }
    }
    public String []getArr(){
        String buf[] = text.split(" ");
        return buf;
    }
    public int[] getArrInd(){
        String buf[] = getArr();
        int arr[] = new int[buf.length];
        arr[0] = text.indexOf(buf[0]);
        for (int i = 1; i < buf.length; i++){
            arr[i] = text.indexOf(buf[i], arr[i-1] + 1);
        }
        return arr;
    }
    public Integer wordCount(String prefix){
        int count  = 0;
        switch (register){
            case 0:
                prefix = prefix.toLowerCase();
                break;
            case 1:
                prefix = prefix.toUpperCase();
                break;
        }
        for (int i = 0; i < text.length(); i++){
            i = text.indexOf(prefix, i);
            if (i > -1) {
                if (i == 0)
                    count++;
                else if (text.charAt(i - 1) == ' ') {
                    count++;
                }
            }
            else
                return count;
        }
        return count;
}
}
