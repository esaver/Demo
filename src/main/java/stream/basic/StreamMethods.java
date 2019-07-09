package stream.basic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamMethods {

    public static void main(String[] args) throws IOException {
        //Create/generate infinite stream using generate() method. use limit() method to limit the generation.
        Stream<String> stringStream = Stream.generate(() -> "element").limit(10);
        stringStream.forEach(System.out::println);

        // Another way to create infinite stream
        Stream<String> stringStream1 = Stream.iterate("A", s -> s + "a").limit(10);
        stringStream1.forEach(System.out::println);

        Stream<Integer> integerStream = Stream.iterate(1, n -> n + 1).limit(10);
        integerStream.forEach(System.out::println);

        // Stream of File
        final Path path = Paths.get("C:\\AmitProject\\Myfile.txt");
        final Stream<String> streamOfStrings = Files.lines(path);
        streamOfStrings.forEach(System.out::println);

        //Stream of String
        final IntStream intStream = "abc".chars();
        intStream.forEach(n -> System.out.println((char) n));

        IntStream intStream2 = IntStream.range(1, 3);
        LongStream longStream2 = LongStream.rangeClosed(1, 3);


        //Referencing a Stream
        List<String> elements =
                Stream.of("a", "b", "c").filter(element -> element.contains("b")).collect(Collectors.toList());
        //stream 1
        Optional<String> anyElement = elements.stream().findAny();
        System.out.println("Referencing any Element " + anyElement.get()); // b
        //stream 2
        Optional<String> firstElement = elements.stream().findFirst();
        System.out.println("Referencing first Element " + firstElement.get()); // b

        //IllegalStateException. An attempt to reuse the same reference after calling the terminal operation will
        // trigger the IllegalStateException:
        Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
        Optional<String> anyElement2 = stream.findAny();
//        IllegalStateException here, stream already closed
        stream.findFirst();
    }
}
