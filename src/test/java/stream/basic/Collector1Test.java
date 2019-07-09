package stream.basic;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Collector1Test {

    @Test
    public void testCollector() {
        List<String> givenList0 = Arrays.asList("a", "bb", "ccc", "dd", "dd");
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd", "bb", null, "");

        final List<String> stringList = givenList.stream().collect(Collectors.toList());
        System.out.println(stringList);

        Set<String> stringSet = givenList0.stream().collect(toSet());
        System.out.println(stringSet);
        assertThat(stringSet.size(), is(4));
//        assertThat(stringSet.size(), is(6));

        //toCollection
        final LinkedList<String> stringLinkedList = givenList.stream().collect(Collectors.toCollection(LinkedList::new));
        System.out.println(stringLinkedList);

        // toMap(Func keyMaper, Func valueMapper)
        final Map<String, Integer> mapResult = givenList0.stream()
//                .collect(Collectors.toMap(Function.identity(), String::length));
//                .collect(Collectors.toMap(Function.identity(), s -> s.length()));
                .collect(Collectors.toMap(s -> s, s -> s.length(), (item, identicalItem) -> item)); //to handle duplicate keys
//                .collect(HashMap::new, (m,v)->m.put(v, v.length()), HashMap::putAll);
        System.out.println(mapResult);

        List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");
        assertThatThrownBy(() -> {
            listWithDuplicates.stream().collect(Collectors.toMap(s -> s, String::length));
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testCollector2() {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");

        assertThat("abbcccdd", is(givenList.stream().collect(Collectors.joining())));
        assertThat("a bb ccc dd", is(givenList.stream().collect(Collectors.joining(" "))));
        assertThat("[a bb ccc dd]", is(givenList.stream().collect(Collectors.joining(" ", "[", "]"))));
        assertThat(4L, is(givenList.stream().collect(Collectors.counting())));
    }

    @Test
    public void testSummarizing() {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
        final DoubleSummaryStatistics statistics = givenList.stream()
                .collect(Collectors.summarizingDouble(s -> s.length()));

//        System.out.println(statistics);
        assertThat(4L, is(statistics.getCount()));
        assertThat(3.0, is(statistics.getMax()));
        assertThat(1.0, is(statistics.getMin()));

        Double result = givenList.stream().collect(averagingDouble(String::length));
        System.out.println(result);

        Double result2 = givenList.stream().collect(summingDouble(String::length));
        System.out.println(result2);

//        Optional<String> result3 = givenList.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));
//        Optional<String> result3 = givenList.stream().collect(Collectors.maxBy(Comparator.reverseOrder()));
        Optional<String> result3 = givenList.stream().collect(Collectors.maxBy(Comparator.comparing(s -> s.length())));
//        Optional<String> result3 = givenList.stream().collect(Collectors.maxBy((a,b) -> a.length()-b.length()));
//        Optional<String> result3 = givenList.stream().collect(Collectors.maxBy(String::compareTo));
        System.out.println(result3);
    }

    @Test
    public void testGroupingBy() {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
        Map<Integer, Set<String>> result = givenList.stream().collect(groupingBy(String::length, toSet()));
        System.out.println(result);
    }

    @Test
    public void testPartioningBy() {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd", "dd");

        Map<Boolean, List<String>> result = givenList.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 2));
        System.out.println(result);

        Map<Boolean, Set<String>> result2 = givenList.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 2, Collectors.toSet()));
        System.out.println(result2);
    }

}