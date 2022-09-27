package com.example.myapplication.utils;

public class ValuesFormatter {

    public static String capitalize (String name) {
        return name.substring(0, 1).toUpperCase().concat(name.substring(1));
    }

    public static String priceFormatter (Integer price) {
        String p = String.valueOf(price);
        int length = p.toCharArray().length;
        if (length > 2) {
            return "£ ".concat(p.substring(0, 1)).concat(".").concat(p.substring(1).concat("p"));
        }
        return p.concat("p");
    }

    public static String weightFormatter (Integer weight) {
        String w = String.valueOf(weight);
        int size = w.toCharArray().length;
        if (size > 3) {
            return "Kg ".concat(w.substring(0, 1)).concat(".").concat(w.substring(1));
        }
        return "Kg 0.".concat(w.substring(0, 1)).concat(w.substring(1));
    }
}
