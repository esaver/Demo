package com.example.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorEx {

    public static void main(String[] args) {

        final List<String> strings = Arrays.asList("as", "bs", "ds", "aa", "bb");

        System.out.println(strings);
        Comparator<String> c = (String s1, String s2) -> s1.compareTo(s2);
//        strings.sort(c);
        strings.sort(Comparator.naturalOrder());
//        strings.sort(c.reversed());
//        strings.sort( (s1, s2) -> s1.compareTo(s2));
//        strings.sort(Comparator.reverseOrder());
        System.out.println(strings);
    }

    public void compareMethod() {

        final List<String> strings = Arrays.asList("as", "bs", "ds", "aa", "bb");

        System.out.println(strings);
        Comparator<String> c = (String s1, String s2) -> s1.compareTo(s2);
//        strings.sort(c);
        strings.sort(c.reversed());
//        strings.sort( (s1, s2) -> s1.compareTo(s2));
//        strings.sort(Comparator.reverseOrder());
        System.out.println(strings);
    }
}
