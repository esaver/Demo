package mapFlatMap;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapEx1 {

    public static void main(String[] args) {
        Parcel amazon = new Parcel("amazon", "Laptop", "Phone");
        Parcel ebay = new Parcel("ebay", "Mouse", "Keyboard");
        List<Parcel> parcels = Arrays.asList(amazon, ebay);

        System.out.println("-------- Without flatMap() ---------------------------");
        List<List<String>> mapReturn = parcels.stream()
                .map(Parcel::getItems)
                .collect(Collectors.toList());
        System.out.println("\t collect() returns: " + mapReturn);

        System.out.println("\n-------- With flatMap() ------------------------------");
        List<String> flatMapReturn = parcels.stream()
                .map(Parcel::getItems)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println("\t collect() returns: " + flatMapReturn);
    }

    public static class Parcel {
        String name;
        List<String> items;

        public Parcel(String name, String... items) {
            this.name = name;
            this.items = Arrays.asList(items);
        }

        public List<String> getItems() {
            return items;
        }
    }
}
