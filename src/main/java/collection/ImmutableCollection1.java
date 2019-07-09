package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableCollection1 {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        List<String> unmodifiable = Collections.unmodifiableList(strings);
//        unmodifiable.add("New string"); // will fail at runtime
        strings.add("Aha!"); // will succeed
        System.out.println(unmodifiable);
    }
}
