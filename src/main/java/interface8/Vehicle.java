package interface8;

public interface Vehicle {

    static String producer() {
        return "Nissan";
    }

    default String getOverview() {

        return "Note made by " + producer();
    }

}
