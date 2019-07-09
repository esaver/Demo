package com.example.demo;

import stream.basic.Collector1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {

        String str = "boo:and:foo";
        final String result = Stream.of(str.split("o"))
                .collect(Collectors.joining(","));
        System.out.println(result);

        Map<String, Integer> list = Stream.of(str.split(":")).
                peek(System.out::println).
                collect(Collectors.toMap(Function.identity(), String::length));

        System.out.println(list);

//        final Map<String, Integer> collect =
        final Map<String, Integer> collect = Stream.of(str.split(":"))
                .peek(System.out::println)
//                .collect(Collectors.toCollection(Hash))
                .collect(Collectors.toMap(Function.identity(), s -> s.length()));
        System.out.println(collect);

    }
}
