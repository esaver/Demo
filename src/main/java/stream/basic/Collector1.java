package stream.basic;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collector1 {

    public static void main(String[] args) {
        //Stream.collect(); terminal operation
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd", "bb", null, "");

        final List<String> stringList = givenList.stream().collect(Collectors.toList());
        System.out.println(stringList);

        final Set<String> stringSet = givenList.stream().collect(Collectors.toSet());
//        assertThat();
    }
}
