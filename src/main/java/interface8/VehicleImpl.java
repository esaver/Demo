package interface8;

public class VehicleImpl implements Vehicle {

    public static void main(String[] args) {

        // implementation of static interface method
        System.out.println(Vehicle.producer());

        Vehicle vehicle = new VehicleImpl();
        System.out.println(vehicle.getOverview());
    }
}
