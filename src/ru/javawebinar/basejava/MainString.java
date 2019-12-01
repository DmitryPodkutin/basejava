package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class MainString {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("Один");
        list.add("Два");
        list.add("Три");
        list.add("Четыре");
        System.out.println(list.toString());

        Map<Integer, String> map = new HashMap<>();
        map.put(1,"Один");
        map.put(2,"Два");
        map.put(3,"Три");
        map.put(4,"Четыре");
        System.out.println(map.values());
        }



    }

//        String x7 ="str";
//        String x8 ="str";
//        System.out.println(x7==x8);
//
//        String x9 ="str2";
//        String x10 ="str2";
//        System.out.println(x9==x10);

