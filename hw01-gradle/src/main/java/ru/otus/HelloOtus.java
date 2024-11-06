package ru.otus;

import com.google.common.base.Strings;

public class HelloOtus {
    public static void main(String... args) {
        String emptyString = "";
        System.out.println(Strings.isNullOrEmpty(emptyString));
    }
}
