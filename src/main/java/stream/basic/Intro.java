package stream.basic;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Intro {

    public static void main(String[] args) {
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> stream = Arrays.stream(arr);
        stream = Stream.of("a", "b", "c");

        List<String> list = Arrays.asList("a", "b", "c", "h");

        list.stream().forEach(e -> doWork(e));
//        list.parallelStream().forEach(e -> doWork(e));
        System.out.println(list.stream().distinct().count());

        System.out.println(list.stream().anyMatch(e -> e.contains("z")));

        //filtering
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("One");
        list2.add("OneAndTwo");
        list2.add("Two");
        list2.add("Dear");
        list2.add("");
        list2.add("Thursday");
        list2.add("");
        list2.add("Monday");

        Stream<String> stream2 = list2.stream().filter(element -> element.contains("d"));
        stream2.forEach(System.out::println);
        boolean allMatch = list2.stream().anyMatch(element -> element.contains("d"));
        System.out.println(allMatch);
        Optional<String> any = list2.stream().findAny();
        System.out.println(any.get());

        //map() - intermediate operation, return stream
        List<String> uris = new ArrayList<>();
        uris.add("C:\\My.txt");

        Stream<Path> pathStream = uris.stream().map(uri -> Paths.get(uri));
        pathStream.forEach(System.out::println);

        //flatMap() - intermediate operation, returns stream
        List<Detail> details = new ArrayList<>();
        Detail detail1 = new Detail();
        detail1.setParts(list2);
        details.add(detail1);

        Stream<String> stringStream = details.stream().flatMap(detail -> detail.getParts().stream());

        stringStream.forEach(System.out::println);

        //matching
        boolean isValid = list.stream().anyMatch(element -> element.contains("h")); // true
        System.out.println("isValid: " + isValid);

        boolean isValidOne = list.stream().allMatch(element -> element.contains("h")); // false
        System.out.println("isValidOne: " + isValidOne);

        boolean isValidTwo = list.stream().noneMatch(element -> element.contains("h")); // false
        System.out.println("isValidTwo: " + isValidTwo);

        //Reduction
        List<Integer> integers = Arrays.asList(23, 1, 1, 1);
//        final Integer reduced = integers.stream().reduce(0, (a, b) -> (a + b));
        final Integer reduced = integers.stream().reduce(0, Integer::sum);
        System.out.println("reduced: " + reduced);

        // Collection

        List<String> resultList = list.stream().map(element -> element.toUpperCase()).collect(Collectors.toList());
        resultList.stream().forEach(System.out::println);
    }

    public static void doWork(String string) {
        System.out.println(string);
    }

    static class Detail {
        private List<String> parts;

        public List<String> getParts() {
            return parts;
        }

        public void setParts(List<String> parts) {
            this.parts = parts;
        }
    }
}
