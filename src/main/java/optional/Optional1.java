package optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Optional1 {

    public static void main(String[] args) {

        //Returns and empty optional
        final Optional<Object> optional = Optional.empty();

        //Returns an optional with a non-null value
        String str = "abcd";
//        String str = "";
//        String str = null;
        final Optional<String> str1 = Optional.of(str);
        System.out.println(str1);

        // Returns an optional with a specific value or an empty Optional if the parameter is null.
//        final Optional<String> str2 = Optional.ofNullable(str);
        final Optional<String> str2 = Optional.ofNullable(null);
        System.out.println(str2);

        final Optional1 obj = new Optional1();
        obj.testFilterOptionalList();
    }

    public void testFilterOptionalList() {
        System.out.println("====== testFilterOptionalList ===========");
        final List<Optional<String>> optionalList = Arrays.asList(Optional.empty(), Optional.of("foo"),
                Optional.empty(), Optional.of("bar"));
        //filter ou empty values

        //using filter and map
        final List<String> stringList = optionalList.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        System.out.println(stringList);

        //using filter and flatMap
        final List<String> objectList = optionalList.stream()
//                .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
//                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .flatMap(o -> o.map(Stream::of).orElse(Stream.empty()))
                .collect(toList());

        System.out.println(objectList);
    }

}
