package stream.basic;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamReduce {

    public static void main(String[] args) {
        // 1 param reduce method
        final OptionalInt reduce = IntStream.range(1, 4)
                .reduce((a, b) -> a + b);

        System.out.println(reduce.getAsInt());

        // 2 param reduce method. identity and accummulator
        final int reduce1 = IntStream.range(1, 4)
                .reduce(10, (left, right) -> left + right);

        System.out.println(reduce1);

        // 3 param reduce method with normal sequential Strem. identity, accumulator, combiner
        final Integer reduceParams = Stream.of(1, 2, 3)
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    System.out.println("combiner was called");
                    return a + b;
                });
        System.out.println(reduceParams);

        // 3 param reduce method. identity, accumulator, combiner
//        final Integer reduceParallel = Stream.of(1, 2, 3)
//                .parallel()
        final Integer reduceParallel = Arrays.asList(1, 2, 3)
                .parallelStream() // only used for Collection or Array stream
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    System.out.println("combiner was called");
                    return a + b;
                });
        System.out.println(reduceParallel);
    }
}
