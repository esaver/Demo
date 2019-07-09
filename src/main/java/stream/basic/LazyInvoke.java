package stream.basic;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class LazyInvoke {

    private static long counter;

    private static void wasCalled() {
        counter++;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        counter = 0;
        Stream<String> stream = list.stream()
                .filter(element -> {
                    wasCalled();
                    return element.contains("2");
                });
        // wasCalled() will not be called since there is no terminal operation on the stream
        System.out.println(counter); // counter = 0
        // now call terminal operation
        stream.count();
        // wasCalled() WILL NOW be called since there is a TERMINAL operation on the stream
        System.out.println(counter); // counter = 3
        //////////////

        Optional<String> stream1 = list.stream()
                .filter(element -> {
                    System.out.println("filter() was called"); // called 2 times
                    return element.contains("2");
                })
                .map(element -> {
                    System.out.println("map() was called"); // called 1 time
                    return element.toUpperCase();
                })
                .findFirst();

    }
}
