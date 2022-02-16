package com.example.twitterclone;

import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {
        Hashtable<String, int[]> table = new Hashtable<>();

        String[] items = {"Select Item","Betta Fish","Snail","Supplies","Food"};

        table.put(items[0], new int[]{1,2,3});
    }
}
