package com.example.myapplication.utils;

public class StringFormatter {

    public static String capitalize (String name) {
        return name.substring(0, 1).toUpperCase().concat(name.substring(1));
    }

    public static String priceFormatter (Integer price) {
        String s = String.valueOf(price);
        return "£ ".concat(s.substring(0, 1)).concat(".").concat(s.substring(1).concat("p"));
    }

    public static String weightFormatter (Integer price) {
        String s = String.valueOf(price);
        return "Kg ".concat(s.substring(0, 1)).concat(".").concat(s.substring(1));
    }
}
