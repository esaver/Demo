package stream.basic;

import java.util.HashMap;
import java.util.Map;

public class FindFirstAny {

    public static void main(String[] args) {

        Map<String, Integer> salaries = new HashMap<>();
        salaries.put("John", 40000);
        salaries.put("Freddy", 30000);
        salaries.put("Samuel", 50000);

        salaries.replaceAll((name, oldValue) ->
                name.equals("Freddy") ? oldValue : oldValue + 10000);

        System.out.println(salaries);

        // Bi Consumer
        Map<String, Integer> ages = new HashMap<>();
        ages.put("John", 25);
        ages.put("Freddy", 24);
        ages.put("Samuel", 30);

        ages.forEach((name, age) -> System.out.println(name + " is " + age + " years old"));

    }
}
